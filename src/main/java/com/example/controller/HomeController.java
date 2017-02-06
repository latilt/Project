package com.example.controller;

import com.example.domain.Lists;
import com.example.service.ListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@RestController
public class HomeController {


   private ListsService listsService;

   @Autowired
   public HomeController(ListsService listsService) {
       this.listsService = listsService;
   }

    @RequestMapping("/addlist")
    public Lists add(Lists lists) {
        Lists listsData = listsService.add(lists);

        return listsData;
    }

    @RequestMapping(value ="/board/list/save", method= RequestMethod.POST)
    public String view(Lists lists) {
        listsService.add(lists);
        return "Save Done";
    }



    @RequestMapping("/")
    public String index() {
        return "hellowworld!";
    }

}
