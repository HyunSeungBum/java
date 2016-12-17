package com.sbhyun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.mongodb.WriteResult;
import com.sbhyun.vo.Person;

@Service
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private MongoOperations mongoOps;
	
	private static final String PERSON_COLLECTION="Person";
	
	@Override
	public void create(Person p) {
		this.mongoOps.insert(p, PERSON_COLLECTION);
	}

	@Override
	public Person readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Person.class, PERSON_COLLECTION);
	}

	@Override
	public void update(Person p) {
		this.mongoOps.save(p, PERSON_COLLECTION);
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Person.class, PERSON_COLLECTION);
		return result.getN();
	}

	@Override
	public List<Person> getAll() {
		Query query = new Query(Criteria.where("_id").exists(true));
		List<Person> persons = this.mongoOps.find(query, Person.class);
		return persons;
	}
	
	@Override
	public void clear() {
		mongoOps.dropCollection(PERSON_COLLECTION);
	}

}
