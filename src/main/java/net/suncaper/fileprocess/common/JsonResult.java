package net.suncaper.fileprocess.common;

/**
 * @author zyq
 * @date 2020/11/16 - 20:05
 */
public class JsonResult<T> {
    private Integer code;
    private T data;

    public JsonResult(T data) {
        this.data = data;
    }

    public JsonResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static JsonResult ok(Object data){
        return new JsonResult(200,data);
    }
    public static JsonResult fail(Object data){
        return new JsonResult(500,data);
    }


}
