package com.xinyou.dome.template;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/11/914:05
 * @Description:
 */
public class CallResult<T> {
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAILURE = -1;
    private final boolean success;
    private final int code;
    private final String msg;
    private final T resultObject;
    private String stringValue;

    public CallResult(boolean isSuccess, int code, String msg, T resultObject) {
        this.code = code;
        this.success = isSuccess;
        this.msg = msg;
        this.resultObject = resultObject;
    }

    public static <T> CallResult<T> success() {
        return new CallResult<>(true, 1, "success", null);
    }

    public static <T> CallResult<T> success(T resultObject) {
        return new CallResult<>(true, 1, "success", resultObject);
    }

    public static <T> CallResult<T> success(int code, String msg, T resultObject) {
        return new CallResult<>(true, code, msg, resultObject);
    }

    public static <T> CallResult<T> failure() {
        return new CallResult<T>(false, -1, "failure", null);
    }

    public static <T> CallResult<T> failure(T resultObject) {
        return new CallResult<T>(false, -1, "failure", resultObject);
    }

    public static <T> CallResult<T> failure(String msg) {
        return new CallResult<T>(false, -1, msg, null);
    }

    public static <T> CallResult<T> failure(int code, String msg, T resultObject) {
        return new CallResult<T>(true, code, msg, resultObject);
    }

    @Override
    public String toString() {

        String result = this.stringValue;
        if (result != null) {
            return result;
        } else {
            result = '{' + "\"success\":" + this.success + ',' + "\"code\":" + this.code + ',' + "\"msg\":\"" + this.msg + "\"," + "\"resultObject\":" + this.resultObject + '}';
            this.stringValue = result;
            return result;
        }

    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getResultObject() {
        return resultObject;
    }
}
