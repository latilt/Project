package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by hokyeong on 2017. 2. 5..
 */
@Entity
public class Card {

    @Id @GeneratedValue
    private int id;
    private String title;
    private int position;

    @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore//@JoinColumn(referencedColumnName = "position")
    private Lists lists;

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
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
