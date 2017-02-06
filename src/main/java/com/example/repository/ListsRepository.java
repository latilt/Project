package com.example.repository;

import com.example.domain.Lists;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Repository
public interface ListsRepository extends CrudRepository<Lists, Integer>{
    List<Lists> findAll();

    Lists save(Lists lists);
}
