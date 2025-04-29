package com.example.menumanager.repository;

import com.example.menumanager.entity.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends MongoRepository<Menu, String> {
}
