package Kruchkov.Task4.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="fio")
    private String fio;

    public User() {

    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setFio(String fio)   {
        this.fio = fio;

    }

    public String getFio()   {
        return this.fio ;

    }

    public String getUserName() {
        return this.username;
    }

    public long getId() {
        return this.id;
    }
    /*
    public User(String username, String fio)   {
        this.fio = fio;
        this.username = username;
    }

     */

}
