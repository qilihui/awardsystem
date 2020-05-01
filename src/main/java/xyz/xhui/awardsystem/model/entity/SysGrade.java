package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class SysGrade implements Serializable {
    private Integer id;

    private Integer name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer grade) {
        this.name = grade;
    }

    @Override
    public String toString() {
        return "SysGrade{" +
                "id=" + id +
                ", grade=" + name +
                '}';
    }
}
