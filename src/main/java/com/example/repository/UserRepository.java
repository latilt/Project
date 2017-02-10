package com.example.repository;

import com.example.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hokyeong on 2017. 2. 10..
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUserName(String string);
}
