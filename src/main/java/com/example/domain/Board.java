package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 7..
 */
@Entity
public class Board {

    @Id @GeneratedValue
    private int id;

    private String title;

    @OneToMany( mappedBy = "board" )
    private List<Lists> lists;

    @OneToMany( mappedBy = "board") @JsonIgnore
    private List<Card> cards;


    public List<Lists> getLists() {
        return lists;
    }

    public void setLists(List<Lists> lists) {
        this.lists = lists;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
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