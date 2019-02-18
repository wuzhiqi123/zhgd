package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.JcxxParma;
import com.zhgd.service.MonitoringInformationService;
import com.zhgd.utile.UdpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/monitoringInformation")
public class MonitoringInformationController {
    @Autowired
    MonitoringInformationService monitoringInformation;

    @RequestMapping(value = "/getJcxx")
    @ResponseBody
    public Result getJcxx(@RequestBody JcxxParma jcxxParma){
        return  monitoringInformation.getJcxx(jcxxParma);
    }
/*    @RequestMapping(value = "/getJcxxCurve")
    @ResponseBody
    public Result getJcxxCurve(){
        Result result = monitoringInformation.getJcxx();
        return result ;
    }*/


    @RequestMapping(value = "/getJcxxSh")
    @ResponseBody
    public void getJcxxSh(){
        Thread thread = new Thread(new UdpUtils());
        thread.start();
    }
}
