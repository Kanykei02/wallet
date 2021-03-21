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
public class Categories {
    private int id;
    private String name;
    private User user_id;
    private Categories parent_category_id ;
    private Boolean is_active;
    private Date created_date;

    public Categories(String name, User user_id, Categories parent_category_id, Boolean is_active, Date created_date) {
        this.name = name;
        this.user_id = user_id;
        this.parent_category_id = parent_category_id;
        this.is_active = is_active;
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        String msg = "";
        return msg;
    }
}
