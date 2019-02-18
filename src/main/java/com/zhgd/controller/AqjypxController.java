package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.pojo.addPojo.AddAqjyox;
import com.zhgd.service.AqjypxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("aqjypx")
public class AqjypxController {
    @Autowired
    AqjypxService aqjypxService;
    /**
     * 添加安全培训人员
     * @return
     */
    @RequestMapping(value  = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Result addAqjypx(@RequestBody  AddAqjyox addAqjyox){
        return aqjypxService.addAqjypx(addAqjyox);
    }
}
