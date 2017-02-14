package com.example.controller;

import com.example.domain.Board;
import com.example.domain.Card;
import com.example.domain.Lists;
import com.example.domain.User;
import com.example.service.BoardService;
import com.example.service.CardService;
import com.example.service.ListsService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 7..
 */
@RestController
public class apiController {

    private UserService userService;
    private BoardService boardService;
    private ListsService listsService;
    private CardService cardService;

    @Autowired
    public apiController(UserService userService, BoardService boardService, ListsService listsService, CardService cardService) {
        this.userService = userService;
        this.boardService = boardService;
        this.listsService = listsService;
        this.cardService = cardService;
    }

    @RequestMapping(value ="/{id}/con", method = RequestMethod.POST)
    public Board construct(@PathVariable String id) {
        Board board = boardService.getBoardOne(id);
        //board.setLists(listsService.setAll(board.getId()));

        return board;
    }

    @RequestMapping(value ="/board/list/save", method= RequestMethod.POST)
    public String addList(Lists lists, @RequestParam("boards") String board) {
        lists.setBoard(boardService.getBoardOne(board));
        listsService.add(lists);
        return "Save Done";
    }

    @RequestMapping(value ="/card/add", method= RequestMethod.POST)
    public String addCard(Card card, @RequestParam("boards") String board, @RequestParam("list") String list) {
        card.setLists(listsService.findbytitle(list));
        card.setBoard(boardService.getBoardOne(board));
        cardService.add(card);
        return "Save Done";
    }

    @RequestMapping(value ="/user/{name}", method= RequestMethod.GET)
    public List<String> userBoard(@PathVariable String name) {
        //유저 유저 객체만
        //


        //return userService.getUserBoard(name);
        User user = userService.getUserBoard(name);
        //Board board = boardService.getBoardbyUser(user.getId());

        return boardService.getBoardbyUser(user.getId());
    }

    @RequestMapping(value="/card/delete", method=RequestMethod.POST)
    public String deleteCard(@RequestParam("cardtitle") String string) {
        cardService.delete(string);
        return "delete Done";
    }

    @RequestMapping(value="/list/delete", method=RequestMethod.POST)
    public String deleteList(@RequestParam("listtitle") String string) {
        listsService.delete(string);
        return "delete Done";
    }

    @RequestMapping(value="/list/move", method = RequestMethod.POST)
    public String moveList(@RequestParam("listtitle") String listtitle, @RequestParam("prevtitle") String prevtitle, @RequestParam("position") Integer position, @RequestParam("prevposition") Integer prevposition) {
        /*Lists list = listsService.findbytitle(listtitle);
        Lists list2 = listsService.findbytitle(prevtitle);

        list.setPosition(position);
        list2.setPosition(prevposition);

        listsService.add(list2);
        listsService.add(list);*/
        return "move Done";
    }
}
