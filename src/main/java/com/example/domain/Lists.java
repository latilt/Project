package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Entity
public class Lists {
    @Id @GeneratedValue
    private int id;

    private String title;

    @Column(nullable = false)
    private int position;

    @OneToMany( mappedBy = "lists", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //@JsonIgnore
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
