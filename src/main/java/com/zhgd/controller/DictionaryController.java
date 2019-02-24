package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.BzPaarma;
import com.zhgd.controller.controllerUtile.parma.DwParma;
import com.zhgd.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典controlle
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;

    /**
     * 获取文化程度
     * @return
     */
    @RequestMapping(value  = "/getWhcd" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getWhcd(){
        return dictionaryService.getWhcd();
    }

    /**
     *  获取性别
     * @return
     */
    @RequestMapping(value  = "/getXb" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getXb(){
        return dictionaryService.getXb();
    }


    /**
     * 获取工地
     * @return
     */
    @RequestMapping(value  = "/getGd" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getGd(){
        return dictionaryService.getGd();
    }

    /**
     * 获取工人队伍
     * @return
     */
    @RequestMapping(value  = "/getGrssdw" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getGrssdw(@RequestBody DwParma dwParma){
        return dictionaryService.getGrssdw(dwParma);
    }

    /**
     * 获取工人班组
     * @return
     */
    @RequestMapping(value  = "/getGrssbz" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getGrssbz(@RequestBody BzPaarma bzPaarma){
        return dictionaryService.getGrssbz( bzPaarma);
    }
    /**
     * 获取工人工种
     * @return
     */
    @RequestMapping(value  = "/getGgrgz" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getGgrgz(){
        return dictionaryService.getGgrgz();
    }

    /**
     * 获取名族
     * @return
     */
    @RequestMapping(value  = "/getMz" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getMz(){
        return dictionaryService.getMz();
    }

    /**
     * 获取政治面貌
     * @return
     */
    @RequestMapping(value  = "/getZzmm" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getZzmm(){
        return dictionaryService.getZzmm();
    }


    /**
     * 获取培训类型
     * @return
     */
    @RequestMapping(value  = "/getPxlx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getPxlx(){
        return dictionaryService.getPxlx();
    }

    /**
     * 获取奖励类型
     * @return
     */
    @RequestMapping(value  = "/getJllx" ,method = RequestMethod.POST)
    @ResponseBody
    public Result getJllx(){
        return dictionaryService.getJllx();
    }
}
