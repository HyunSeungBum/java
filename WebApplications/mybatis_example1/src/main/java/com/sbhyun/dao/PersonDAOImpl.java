package com.sbhyun.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sbhyun.vo.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	
	private static final String NS = "sbhyun.person.";
	
	@Override
	public List<Person> getPersons() throws SQLException {
		logger.info("dao select all");
		return sqlSession.selectList(NS+"getPersons");
	}

	@Override
	public Person getPerson(int id) {
		logger.info("dao select one id is " + id);
		return sqlSession.selectOne(NS+"getPerson");
	}

	@Override
	public Person savePerson(Person person) {
		logger.info("dao save person is " + person);
		sqlSession.insert(NS+"savePerson", person);
		return person;
	}

	@Override
	public Person updatePerson(Person person) {
		logger.info("dao update person is " + person);
		sqlSession.update(NS+"updatePerson", person);
		return person;
	}

	@Override
	public void deletePerson(int id) {
		logger.info("dao delete id is " + id);
		sqlSession.delete(NS+"deletePerson", id);
	}



}
