package com.example.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hokyeong on 2017. 2. 10..
 */
@Entity
public class User {

    @Id @GeneratedValue
    private int id;

    private String userName;

    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
