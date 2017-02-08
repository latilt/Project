package com.example.controller;

import com.example.domain.Board;
import com.example.domain.Card;
import com.example.domain.Lists;
import com.example.service.BoardService;
import com.example.service.CardService;
import com.example.service.ListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Controller
public class HomeController {

    private BoardService boardService;
    private ListsService listsService;
    private CardService cardService;

   @Autowired
   public HomeController(BoardService boardService, ListsService listsService, CardService cardService) {
       this.boardService = boardService;
       this.listsService = listsService;
       this.cardService = cardService;
   }

    @RequestMapping("/addlist")
    public Lists add(Lists lists) {
        Lists listsData = listsService.add(lists);

        return listsData;
    }

    /*@RequestMapping(value ="/board/list/card", method= RequestMethod.POST)
    public String addCard(@RequestParam("title") String title, @RequestParam("number") String number) {

        Card card = new Card();
        card.setTitle(title);
        card.setLists(listsService.find(Integer.parseInt(number)));
        System.out.println(number);

        cardService.add(card);
        System.out.println(card.getTitle());

        return "Save Done";
    }*/


    @RequestMapping("/{id}")
    public String index() {
        return "index";
    }

    @RequestMapping("/board")
    public String board() {
        return "post/board";
    }

}
