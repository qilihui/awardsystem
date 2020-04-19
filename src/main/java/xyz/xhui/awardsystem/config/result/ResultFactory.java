package xyz.xhui.awardsystem.config.result;

public class ResultFactory {

    public static <T> Result<T> buildResult(Integer code, String msg, Integer count, T data) {
        return new Result<T>(code, msg, count, data);
    }

    /**
     * 成功 无返回结果
     * @param <T>
     * @return
     */
    public static <T> Result<T>  buildSuccessResult() {
        return buildResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), 0, null);
    }

    /**
     * 成功 返回单个数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T>  buildSuccessResult(T data) {
        return buildResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), 1, data);
    }

    /**
     * 成功 返回多组数据
     * @param count
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T>  buildSuccessResult(Integer count, T data) {
        return buildResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), count, data);
    }

    /**
     * 失败 无返回结果
     * @param <T>
     * @return
     */
    public static <T> Result<T>  buildFailResult() {
        return buildResult(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), 0, null);
    }


    public static <T> Result<T>  buildFailResult(String msg) {
        return buildResult(StatusCode.FAIL.getCode(), msg, 0, null);
    }

    public static <T> Result<T>  buildFailResult(String msg, Integer count, T data) {
        return buildResult(StatusCode.FAIL.getCode(), msg, count, data);
    }
}
