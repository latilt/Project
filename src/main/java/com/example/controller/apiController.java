package com.example.controller;

import com.example.domain.Board;
import com.example.domain.Card;
import com.example.domain.Lists;
import com.example.service.BoardService;
import com.example.service.CardService;
import com.example.service.ListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hokyeong on 2017. 2. 7..
 */
@RestController
public class apiController {

    private BoardService boardService;
    private ListsService listsService;
    private CardService cardService;

    @Autowired
    public apiController(BoardService boardService, ListsService listsService, CardService cardService) {
        this.boardService = boardService;
        this.listsService = listsService;
        this.cardService = cardService;
    }

    @RequestMapping(value ="/{id}/con", method = RequestMethod.POST)
    public Board construct(@PathVariable String id) {
        //return boardService.getBoard();
        return boardService.getBoardOne(id);
    }

    @RequestMapping(value ="/board/list/save", method= RequestMethod.POST)
    public String view(Lists lists, @RequestParam("boards") String board) {
        lists.setBoard(boardService.getBoardOne(board));
        listsService.add(lists);
        return "Save Done";
    }

    @RequestMapping(value ="/board/list/card", method= RequestMethod.POST)
    public String addCard(Card card, @RequestParam("boards") String board, @RequestParam("list") String list) {
        card.setLists(listsService.findbytitle(list));
        card.setBoard(boardService.getBoardOne(board));
        cardService.add(card);
        return "Save Done";
    }
}
