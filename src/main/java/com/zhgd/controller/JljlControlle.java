package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.JljlParma;
import com.zhgd.pojo.Jljl;
import com.zhgd.service.JljlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("jljl")
public class JljlControlle {
    @Autowired
    JljlService jljlService;
    /**
     * 获取奖励记录信息
     * @return
     */
    @RequestMapping(value  = "/getJljl" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getJljl(@RequestBody JljlParma jljlParma){
        return jljlService.getJljl(jljlParma);
    }

    /**
     * 添加奖励记录
     * @return
     */
    @RequestMapping(value  = "/addJljl" ,method = RequestMethod.POST)
    @ResponseBody
    public Result addJljl(@RequestBody Jljl jljl){
        return jljlService.addJljl(jljl);
    }
}
