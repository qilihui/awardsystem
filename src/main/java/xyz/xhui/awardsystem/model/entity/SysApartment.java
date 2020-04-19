package xyz.xhui.awardsystem.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_apartment")
public class SysApartment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
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
