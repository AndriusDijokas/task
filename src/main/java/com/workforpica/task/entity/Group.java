package com.workforpica.task.entity;

import javax.persistence.Id;
import java.math.BigInteger;

public class Group {

    @Id
    BigInteger id;
    String name;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
