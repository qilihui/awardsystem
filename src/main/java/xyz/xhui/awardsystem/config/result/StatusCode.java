package xyz.xhui.awardsystem.config.result;

public enum StatusCode {
    SUCCESS(0, "请求成功"),
    TABLE_SUCCESS(0, "请求成功"),

    FAIL(500, "请求失败，请联系管理员");

    private Integer code;
    private final String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
