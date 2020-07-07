package com.example.login.utils;

public class ResultUtils {
    /**
     * 返回带数据的信息
     * @param obj
     * @return
     */
    public static Result success(Object obj){
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(obj);
        return result;
    }

    /**
     * 返回成功但不带数据
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 错误信息
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
