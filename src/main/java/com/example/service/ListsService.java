package com.example.service;

import com.example.domain.Lists;
import com.example.repository.ListsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Lists> setAll() {
        return listsRepository.findAll();
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


}
