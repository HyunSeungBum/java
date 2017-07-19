package com.sbhyun.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sbhyun.model.Person;

@Repository
public interface IPersonRepository extends MongoRepository<Person, String>{

}
