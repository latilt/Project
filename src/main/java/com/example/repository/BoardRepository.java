package com.example.repository;

import com.example.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 7..
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findAll();

    //List<Board> findByTitle(String title);

    Board findByTitle(String title);

    //@Query("SELECT title FROM board where user_id = ?1")
    List<String> findTitleByUserId(Integer integer);





}
