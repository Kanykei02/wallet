package com.wallet.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "user")
public class User {
    private int id;
    private String name;
    private String password;
    private Date createdDate;

    public User(String name, String password, Timestamp createdDate) {
        this.name = name;
        this.password = password;
        this.createdDate = createdDate;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        String msg = this.name + " " + this.createdDate;
        return msg;
    }
}
