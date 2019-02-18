package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.SsxxParma;
import com.zhgd.pojo.Sslxx;
import com.zhgd.pojo.Ssxx;
import com.zhgd.pojo.addPojo.SsxxAdd;
import com.zhgd.service.SslxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sslxx")
public class SslxxController {
    @Autowired
    SslxxService sslxxService;
    /**
     * 获取宿舍楼信息
     * @return
     */
    @RequestMapping(value  = "/getSslxx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getSslxx(){
        return sslxxService.getSslxx();
    }


    /**
     * 获取宿舍信息
     * @return
     */
    @RequestMapping(value  = "/getSsxx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getSsxx(@RequestBody SsxxParma ssxxParma){
        return sslxxService.getSsxx(ssxxParma);
    }

    /**
     * 添加宿舍楼
     * @return
     */
    @RequestMapping(value  = "/addSsLxx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result addSsLxx(@RequestBody Sslxx sslxx){
        return sslxxService.addSsLxx(sslxx);
    }

    /**
     * 添加宿舍楼层 addLcxx
     * @return
     */
    @RequestMapping(value  = "/addLcxx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result addLcxx(@RequestBody Ssxx ssxx){
        return sslxxService.addLcxx(ssxx);
    }
}
