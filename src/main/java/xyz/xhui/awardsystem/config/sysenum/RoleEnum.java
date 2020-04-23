package xyz.xhui.awardsystem.config.sysenum;

public enum RoleEnum {
    ROLE_ADMIN(0, "系统管理员"),
    ROLE_TUTOR(1, "辅导员"),
    ROLE_HOUSEPARENT(2, "宿舍管理员"),
    ROLE_UNION(3, "学生会"),
    ROLE_STU(4, "学生");

    private Integer id;
    private String name;

    RoleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RoleEnum getValue(Integer id) {
        for (RoleEnum roleEnum : values()) {
            if(roleEnum.getId().equals(id)) {
                return roleEnum;
            }
        }
        return null;
    }
}

