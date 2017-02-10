package com.example.repository;

import com.example.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Repository
public interface CardRepository extends CrudRepository<Card, Integer>{

    Card save(Card card);

    @Transactional
    void deleteByTitle(String string);


    @Override
    void delete(Integer integer);

    @Override
    void deleteAll();
}
