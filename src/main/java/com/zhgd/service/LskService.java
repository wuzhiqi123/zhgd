package com.zhgd.service;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.LskParma;
import com.zhgd.pojo.Lsk;

public interface LskService {
    Result getLskxx(LskParma lskParma);
    Result addLskxx(Lsk lsk);
}
