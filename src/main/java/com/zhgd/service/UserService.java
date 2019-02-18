package com.zhgd.service;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.pojo.Workers;

import java.util.List;


public interface UserService {
    Result getUser(Workers workers);

    Result deleteUser(List<String> nms);
}
