package com.example.service;

import com.example.domain.Lists;
import com.example.repository.ListsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Service
public class ListsService {

    ListsRepository listsRepository;

    @Autowired
    public ListsService(ListsRepository listsRepository) {
        this.listsRepository = listsRepository;
    }

    public List<Lists> setAll(Integer integer) {
        return listsRepository.findAllByBoardId(integer);
    }

    public Lists add(Lists lists) {
        return listsRepository.save(lists);
    }

    public Lists find(Integer integer) {
        return listsRepository.findOne(integer);
    }

    public Lists findbytitle(String string) {
        return listsRepository.findByTitle(string);
    }

    public Lists findbyposition(Integer integer) {
        return listsRepository.findByPosition(integer);
    }

    public void delete(String string) {
        listsRepository.deleteByTitle(string);
    }
}
