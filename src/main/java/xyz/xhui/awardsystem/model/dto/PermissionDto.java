package xyz.xhui.awardsystem.model.dto;

import java.util.List;

public class PermissionDto {

    /**
     * homePage : /welcome
     * data : [{"id":1,"name":"用户管理","css":"&#xe6b8;","child":[{"name":"用户列表","href":"/getPage?name=user/user-list"},{"name":"修改密码","href":"/getPage?name=user/user-change-password"}]},{"id":2,"name":"系统管理","css":"&#xe6ae;","child":[{"name":"系部列表","href":"/getPage?name=sys/dept-list"},{"name":"年级列表","href":"/getPage?name=sys/grade-list"},{"name":"公寓列表","href":"/getPage?name=sys/apartment-list"}]}]
     */

    private String homePage;
    private List<DataBean> data;

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 用户管理
         * css : &#xe6b8;
         * child : [{"name":"用户列表","href":"/getPage?name=user/user-list"},{"name":"修改密码","href":"/getPage?name=user/user-change-password"}]
         */

        private int id;
        private String name;
        private String css;
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

        public String getCss() {
            return css;
        }

        public void setCss(String css) {
            this.css = css;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * name : 用户列表
             * href : /getPage?name=user/user-list
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
}
