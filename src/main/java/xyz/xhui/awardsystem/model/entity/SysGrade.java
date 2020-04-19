package xyz.xhui.awardsystem.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_grade")
public class SysGrade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
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
