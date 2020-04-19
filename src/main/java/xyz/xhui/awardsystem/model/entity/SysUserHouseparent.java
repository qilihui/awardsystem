package xyz.xhui.awardsystem.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user_houseparent")
public class SysUserHouseparent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @OneToOne
    @JoinColumn(name = "apartment_id", nullable = false, unique = true)
    private SysApartment apartment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysApartment getApartment() {
        return apartment;
    }

    public void setApartment(SysApartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "SysUserHouseparent{" +
                "id=" + id +
                ", user=" + user +
                ", apartment=" + apartment +
                '}';
    }
}
