package com.example.repository;

import com.example.domain.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 7..
 */
@Repository
public interface BoardRepository extends CrudRepository<Board, Integer>{

    List<Board> findAll();

    //List<Board> findByTitle(String title);

    Board findByTitle(String title);
}
