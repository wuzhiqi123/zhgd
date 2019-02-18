package com.zhgd;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.pojo.addPojo.AddRyrz;
import com.zhgd.service.RyrzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("ryrz")
public class RyrzController {
    @Autowired
    RyrzService ryrzService;
    /**
     * 人员入住
     * @return
     */
    @RequestMapping(value  = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Result addAqjypx(@RequestBody AddRyrz addRyrz){
        return ryrzService.addRyrz(addRyrz);
    }
}
