package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class SysApartment implements Serializable {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysApartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
