package com.zhgd.utile;

import com.cetc28.rtp.cfg.Environment;
import com.zhgd.ucc.UCCalThriftInterface;
import com.zhgd.ucc.UCCalThriftInterfaceClient;
import com.zhgd.ucc.UCCalThriftInterfaceClientFactory;
import com.zhgd.zxc.DMIThriftInterfaceClient;
import com.zhgd.zxc.DMIThriftInterfaceClientFactory;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public  class DMIClient {
    public static DMIThriftInterfaceClient getDMIclient()throws Exception {
        DMIThriftInterfaceClient client;
        Environment.getInstance().init();
        //String stringIP = "s217753d07.iok.la"; 9132
        String stringIP = "192.168.0.144";
        if ("" == stringIP)
        {
            client = DMIThriftInterfaceClientFactory.getInstance()
                    .createDMIThriftInterfaceClient();
        }
        else {
            client = DMIThriftInterfaceClientFactory.getInstance()
                    .createDMIThriftInterfaceClient(stringIP, 9132);//
        }
        return client;
    }

    public static UCCalThriftInterfaceClient getUcc()throws Exception {
        UCCalThriftInterfaceClient ucCalThriftInterfaceClient;
        Environment.getInstance().init();
        //String stringIP = "s217753d07.iok.la";
        String stringIP = "localhost";  //port :9133
        ucCalThriftInterfaceClient = UCCalThriftInterfaceClientFactory.getInstance().createUCCalThriftInterfaceClient(stringIP,9133);
        return ucCalThriftInterfaceClient;
    }
}
