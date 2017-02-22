package com.example.controller;

import com.example.domain.Board;
import com.example.domain.Card;
import com.example.domain.Lists;
import com.example.domain.User;
import com.example.service.BoardService;
import com.example.service.CardService;
import com.example.service.ListsService;
import com.example.service.UserService;
import com.example.storage.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hokyeong on 2017. 2. 7..
 */
@RestController
public class apiController {

    private UserService userService;
    private BoardService boardService;
    private ListsService listsService;
    private CardService cardService;
    private StorageService storageService;

    @Autowired
    public apiController(UserService userService, BoardService boardService, ListsService listsService, CardService cardService, StorageService storageService) {
        this.userService = userService;
        this.boardService = boardService;
        this.listsService = listsService;
        this.cardService = cardService;
        this.storageService = storageService;
    }

    @RequestMapping(value ="/{id}/con", method = RequestMethod.POST)
    public Board construct(@PathVariable String id) {
        Board board = boardService.getBoardOne(id);
        //board.setLists(listsService.setAll(board.getId()));
//        MyPojo p = new MyPojo();
//        p.name = board.getTitle()
        return board;
    }

    @RequestMapping(value ="/next", method = RequestMethod.POST)
    public String con() {
        //HashMap<String >
        return "ok";
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

    @RequestMapping(value="/file/send", method = RequestMethod.POST)
    public @ResponseBody String sendFile(@RequestParam("file") MultipartFile file) {
        storageService.store(file);

        Map<String, String> filePath = new HashMap<>();
        filePath.put("url", String.valueOf(storageService.load(file.getOriginalFilename())));
        try {
            filePath.put("bytes", String.valueOf(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        filePath.put("size", String.valueOf(file.getSize()));

        filePath.put("name", file.getName());
        filePath.put("content-type", file.getContentType());
        try {
            filePath.put("inputStream", String.valueOf(file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(filePath);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(value="/board/create", method = RequestMethod.POST)
    public String createBoard(Board board, @RequestParam("username") String username) {
        board.setUser(userService.getUserBoard(username));
        boardService.createBoard(board);
        return "create Board";
    }
}
