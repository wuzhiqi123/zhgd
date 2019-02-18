package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.LskParma;
import com.zhgd.pojo.Lsk;
import com.zhgd.service.LskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("lsk")
public class LskController {
    @Autowired
    LskService lskService;
    /**
     * 获取临时卡信息
     * @return
     */
    @RequestMapping(value  = "/getLskxx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getLskxx(@RequestBody LskParma lsk){
        return lskService.getLskxx(lsk);
    }

    /**
     * 添加临时卡信息
     * @return
     */
    @RequestMapping(value  = "/addLskxx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result addLskxx(@RequestBody Lsk lsk){
        return lskService.addLskxx(lsk);
    }
}
