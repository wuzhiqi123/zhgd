package com.zhgd.service;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.parma.BzPaarma;

public interface DictionaryService {
    Result getWhcd();

    Result getXb();

    Result getGd();

    Result getGrssdw();

    Result getGrssbz(BzPaarma bzPaarma);

    Result getGgrgz();

    Result getMz();

    Result getZzmm();

    Result getPxlx();

    Result getJllx();

}
