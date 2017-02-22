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

    public Board getBoardOne(String title) {
        //return boardRepository.findByTitle(title);
        return boardRepository.findByTitle(title);
    }

    public List<String> getBoardbyUser(Integer integer) {
        return boardRepository.findTitleByUserId(integer);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

}
