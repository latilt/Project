package com.example.repository;

import com.example.domain.Lists;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Repository
public interface ListsRepository extends CrudRepository<Lists, Integer>{
    List<Lists> findAll();

    List<Lists> findAllByBoardId(Integer integer);

    //List<Lists> findAllOrderByPositionDesc(Integer position);

    Lists save(Lists lists);

    Lists findOne(Integer integer);

    Lists findByTitle(String string);

    Lists findByPosition(Integer integer);

    @Transactional
    void deleteByTitle(String string);
}
