package com.zhgd.service;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.JljlParma;
import com.zhgd.pojo.Jljl;

public interface JljlService {
    Result getJljl (JljlParma jljlParma);
    Result addJljl (Jljl jljl);
}
