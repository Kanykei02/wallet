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
public class Orders {
    private int id;
    private Categories category_id;
    private int amount;
    private String description;
    private Wallet wallet_id;
    private Boolean is_expense;
    private Date created_date;

    public Orders(Categories category_id, int amount, String description, Wallet wallet_id, Boolean is_expense, Date created_date) {
        this.category_id = category_id;
        this.amount = amount;
        this.description = description;
        this.wallet_id = wallet_id;
        this.is_expense = is_expense;
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        String msg = "";
        return msg;
    }
}
