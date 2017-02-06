package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Entity
public class Card {

    @Id @GeneratedValue
    private int id;
    private String title;

    @ManyToOne
    private Lists lists;


    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
