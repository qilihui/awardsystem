package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;
import java.util.List;

public class SysPermision implements Serializable {

    /**
     * id : 1
     * name : 用户管理
     * child : [{"name":"查询","href":"/getPage/findAll"}]
     */

    private int id;
    private String name;
    private List<ChildBean> child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    public static class ChildBean {
        /**
         * name : 查询
         * href : /getPage/findAll
         */

        private String name;
        private String href;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
