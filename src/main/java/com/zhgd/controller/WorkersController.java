package com.zhgd.controller;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.pojo.Workers;
import com.zhgd.service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workers")
public class WorkersController {
    @Autowired
    WorkersService workersService;
    @RequestMapping(value="/add",method = RequestMethod.POST)
    /*@PostMapping("/employee/testpost")*/
    @ResponseBody
    public Result add(@RequestBody  Workers workers){
        return workersService.add(workers);
    }
}
