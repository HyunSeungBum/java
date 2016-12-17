package com.sbhyun.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.sbhyun.service.InitService;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

@Configuration
@PropertySource("classpath:config/mongo.properties")
@EnableMongoRepositories(basePackages="com.sbhyun.repositories")
public class MongoConfigurationBeans {
	
	@Value("${mongo.host}")
	private String host;
	
	@Value("${mongo.port}")
	private Integer port;
	
	@Value("${mongo.database}")
	private String encryptedDatabase;
	
	private String database;
	
	@Value("${mongo.connectionsPerHost}")
	private int connectionsPerHost;
	
	@Value("${mongo.threadsAllowedToBlockForConnectionMultiplier}")
	private int threadsAllowedToBlockForConnectionMultiplier;
	
	@Value("${mongo.connectTimeout}")
	private int connectTimeout;
	
	@Value("${mongo.maxWaitTime}")
	private int maxWaitTime;
	
	@Value("${mongo.autoConnectRetry}")
	private boolean autoConnectRetry;
	
	@Value("${mongo.socketKeepAlive}")
	private boolean socketKeepAlive;
	
	@Value("${mongo.socketTimeout}")
	private int socketTimeout;
	
	@Value("${mongo.readPreference}")
	private String readPreference;
	
	@Value("${mongo.seedList}")
	private String seedList;
	
	@Autowired 
	UserCredentialBeans userCredential;

	@Bean
	public MongoClientFactoryBean mongoFactory() {
		MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
		
		Builder mongoClientOptions = new MongoClientOptions.Builder();
		mongoClientOptions.connectionsPerHost(connectionsPerHost);
		mongoClientOptions.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
		mongoClientOptions.connectTimeout(connectTimeout);
		mongoClientOptions.maxWaitTime(maxWaitTime);
		mongoClientOptions.socketKeepAlive(socketKeepAlive);
		mongoClientOptions.socketTimeout(socketTimeout);
		
		database = userCredential.getDecyprt(encryptedDatabase);
		
		//MongoCredential credential = MongoCredential.createScramSha1Credential(userCredential.getUsername(), database, userCredential.getPassword().toCharArray());
		//MongoCredential credential = MongoCredential.createMongoCRCredential(userCredential.getUsername(), database, userCredential.getPassword().toCharArray());
		MongoCredential credential = MongoCredential.createCredential(userCredential.getUsername(), database, userCredential.getPassword().toCharArray());
				
		if (!("").equals(readPreference) && !("").equals(seedList)) {
			List<ServerAddress> seedListArray = new ArrayList<ServerAddress>();
            for (String seed : seedList.split(",")) {
            	String[] conn = seed.split(":");
                seedListArray.add(new ServerAddress(conn[0],Integer.parseInt(conn[1])));
            }
            
            mongoClientFactoryBean.setReplicaSetSeeds(seedListArray.toArray(new ServerAddress[seedListArray.size()]));
            ReadPreference preference = ReadPreference.valueOf(readPreference);
            if (preference != null) {
            	mongoClientOptions.readPreference(preference);
            }
            
		} else {
			mongoClientFactoryBean.setHost(host);
			if (port != null) {
				mongoClientFactoryBean.setPort(port);
			}
		}
		
		mongoClientFactoryBean.setCredentials(new MongoCredential[]{credential});
		mongoClientFactoryBean.setMongoClientOptions(mongoClientOptions.build());
		
        return mongoClientFactoryBean;
	}
	
	@Bean
	public MongoTemplate mongoTemplate(Mongo mongoFactory) {
		return new MongoTemplate(mongoFactory, database);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean(initMethod = "init")
	public InitService initService() {
		return new InitService();
	}
}
