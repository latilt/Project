package com.example.controller;

import com.example.domain.Board;
import com.example.domain.Lists;
import com.example.service.BoardService;
import com.example.service.ListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Controller
@RequestMapping("/board")
public class ListsController {

    ListsService listsService;
    BoardService boardService;

    @Autowired
    public ListsController(ListsService listsService, BoardService boardService) {
        this.listsService = listsService;
        this.boardService = boardService;
    }

    @RequestMapping("/list")
    public String setList(Model model) {
        model.addAttribute("boards", boardService.getBoard());

        return "post/board";
    }



    /*@RequestMapping("/list")
    public String setList(Model model) {
        model.addAttribute("lists", listsService.setAll());
        return "post/board";
    }*/


}
