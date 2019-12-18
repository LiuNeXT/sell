package com.imooc.utils;

import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.vo.ResultVO;

public class ResultVOUtil {


    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success(){
        return success(new Object());
    }

    public static String error(){
        return "error";
    }

}
