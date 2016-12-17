package com.sbhyun.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sbhyun.model.Person;

public interface IPersonRepository extends MongoRepository<Person, String>{

}
