package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;

public class PageDto<T> implements Serializable {
    private Integer count;
    private T obj;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "count=" + count +
                ", obj=" + obj +
                '}';
    }
}
