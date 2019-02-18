package com.zhgd.service;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.SsxxParma;
import com.zhgd.pojo.Sslxx;
import com.zhgd.pojo.Ssxx;

public interface SslxxService {
    Result getSslxx ();
    Result getSsxx (SsxxParma ssxxParma);
    Result addSsLxx(Sslxx sslxx);

    Result addLcxx(Ssxx ssxx);
}
