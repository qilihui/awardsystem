package xyz.xhui.awardsystem.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user_stu")
public class SysUserStu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private SysUser user;

    @OneToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private SysDept dept;

    @OneToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private SysGrade grade;

    @OneToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private SysApartment apartment;

    @Column(name = "room", nullable = false)
    private Integer room;

    @Column(name = "bed", nullable = false)
    private Integer bed;

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

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public SysGrade getGrade() {
        return grade;
    }

    public void setGrade(SysGrade grade) {
        this.grade = grade;
    }

    public SysApartment getApartment() {
        return apartment;
    }

    public void setApartment(SysApartment apartment) {
        this.apartment = apartment;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    @Override
    public String toString() {
        return "SysUserStu{" +
                "id=" + id +
                ", user=" + user +
                ", dept=" + dept +
                ", grade=" + grade +
                ", apartment=" + apartment +
                ", room=" + room +
                ", bed=" + bed +
                '}';
    }
}
