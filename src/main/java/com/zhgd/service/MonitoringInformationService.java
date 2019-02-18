package com.zhgd.service;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.JcxxParma;

public interface MonitoringInformationService {

    Result getJcxx(JcxxParma jcxxParma);
}
