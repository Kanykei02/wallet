package com.wallet.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "wallet")
public class Wallet {
    private int id;
    private String name;
    private User user;
    private boolean is_active;
    private Date createdDate;

    public Wallet(String name, User user, boolean is_active, Date createdDate) {
        this.name = name;
        this.user = user;
        this.is_active = is_active;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        String msg = "";
        return msg;
    }
}
