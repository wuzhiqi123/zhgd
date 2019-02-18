package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.pojo.DelectPojo;
import com.zhgd.pojo.Workers;
import com.zhgd.service.UserService;
import com.zhgd.utile.DMIClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller()
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;


    /**
     * 获取工人基本信息
     * @return
     */
    @RequestMapping( value = "/getUser")
    @ResponseBody
    public Result getUser(@RequestBody Workers workers){
        return userService.getUser(workers);
    }
    /**
     * 读取身份证
     * @return
     */
    @RequestMapping( value = "/ucc")
    @ResponseBody
    public Result ucc(){
        try{
            Map<String ,String > map =  DMIClient.getUcc().UCCal_ReadIDCardInfo(null);
            String birth =map.get("BIRTH");
            StringBuffer stringBuffer = new StringBuffer(birth);
            stringBuffer.insert(6,"/");
            stringBuffer.insert(4,"/");
            map.put("BIRTH",stringBuffer.toString());
            Result result = new Result(ResultEnum.OK);
            result.setDataa(map);
            return result;
        }catch(Exception e){
            Result result = new Result(ResultEnum.OK);
            result.setDataa(e.getMessage());
            return result;
        }
    }
    /**
     * 删除工人
     * @return @RequestBody DelectPojo delectPojo
     */
    @RequestMapping( value = "/deleteUser")
    @ResponseBody
    public Result deleteUser( @RequestBody DelectPojo delectPojo){
        String [] str = delectPojo.getNms().split(",");
        /*String [] str = nm.split(",");*/
        List<String > nms =Arrays.asList(str);
        return userService.deleteUser(nms);
    }

    @RequestMapping(value = "/demo")
    @ResponseBody
    public void paizhao()throws IOException {

    }


}
