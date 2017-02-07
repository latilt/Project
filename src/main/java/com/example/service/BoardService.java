package com.example.service;

import com.example.domain.Board;
import com.example.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 7..
 */
@Service
public class BoardService {

    BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoard() {
        return boardRepository.findAll();
    }

    public List<Board> getBoardOne(String title) {
        return boardRepository.findByTitle(title);
    }

}
