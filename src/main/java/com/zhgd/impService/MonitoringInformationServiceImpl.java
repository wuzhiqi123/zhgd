package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.parma.JcxxParma;
import com.zhgd.pojo.Jcxx;
import com.zhgd.service.MonitoringInformationService;
import com.zhgd.utile.DMIClient;
import com.zhgd.utile.DateUtile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class MonitoringInformationServiceImpl implements MonitoringInformationService {
    @Override
    public Result getJcxx(JcxxParma jcxxParma){
        List<String> list = new ArrayList<String>();
        List<Jcxx> JcxxList = new ArrayList<Jcxx>();
        list.add("NM");
        list.add("GDNM");
        list.add("SBBH");
        list.add("PM25");
        list.add("PM10");
        list.add("TSP");
        list.add("ZS");
        list.add("WD");
        list.add("SD");
        list.add("FL");
        list.add("FS");
        list.add("FXFW");
        list.add("FXDS");
        list.add("QY");
        list.add("GZ");
        list.add("SJSJ");
        try {
            Result result = new Result(ResultEnum.OK);
            String sql = "SJSJ > to_date( '"+ DateUtile.getDateString(new Date(jcxxParma.getStart())) +"','yyyy/mm/dd hh24:mi:ss') and to_date( '"+ DateUtile.getDateString(new Date(jcxxParma.getEnd())) +"','yyyy/mm/dd hh24:mi:ss') > SJSJ ";
            Map<Integer, Map<String, String>> map = DMIClient.getDMIclient().DMI_FilterParam("ZHGD_HJJC_JCXX", list, null, sql, "");
            for (Map.Entry<Integer, Map<String, String>> jcxx : map.entrySet()) {
                Jcxx jcxx1 = new Jcxx();
                for (Map.Entry<String, String> jcxxMap : jcxx.getValue().entrySet()) {
                    if ("NM".equals(jcxxMap.getKey())) {
                        jcxx1.setNm(jcxxMap.getValue());
                    }
                    if ("GDNM".equals(jcxxMap.getKey())) {
                        jcxx1.setGdnm(jcxxMap.getValue());
                    }
                    if ("SBBH".equals(jcxxMap.getKey())) {
                        jcxx1.setSbbh(jcxxMap.getValue());
                    }
                    if ("PM25".equals(jcxxMap.getKey())) {
                        jcxx1.setPm25(jcxxMap.getValue());
                    }
                    if ("PM10".equals(jcxxMap.getKey())) {
                        jcxx1.setPm10(jcxxMap.getValue());
                    }
                    if ("TSP".equals(jcxxMap.getKey())) {
                        jcxx1.setTsp(jcxxMap.getValue());
                    }
                    if ("ZS".equals(jcxxMap.getKey())) {
                        jcxx1.setZs(jcxxMap.getValue());
                    }
                    if ("WD".equals(jcxxMap.getKey())) {
                        jcxx1.setWd(jcxxMap.getValue());
                    }
                    if ("SD".equals(jcxxMap.getKey())) {
                        jcxx1.setSd(jcxxMap.getValue());
                    }
                    if ("FL".equals(jcxxMap.getKey())) {
                        jcxx1.setFl(jcxxMap.getValue());
                    }
                    if ("FS".equals(jcxxMap.getKey())) {
                        jcxx1.setFs(jcxxMap.getValue());
                    }
                    if ("FXFW".equals(jcxxMap.getKey())) {
                        jcxx1.setFxfw(jcxxMap.getValue());
                    }
                    if ("FXDS".equals(jcxxMap.getKey())) {
                        jcxx1.setFxds(jcxxMap.getValue());
                    }
                    if ("QY".equals(jcxxMap.getKey())) {
                        jcxx1.setQy(jcxxMap.getValue());
                    }
                    if ("GZ".equals(jcxxMap.getKey())) {
                        jcxx1.setGz(jcxxMap.getValue());
                    }
                    if ("SJSJ".equals(jcxxMap.getKey())) {
                        jcxx1.setSjsj(jcxxMap.getValue());
                    }
                    JcxxList.add(jcxx1);
                }
            }
            result.setDataa(JcxxList);
            result.setTotal(JcxxList.size());
            return result;
        }catch(Exception e){
            Result result = new Result(ResultEnum.ERROR);
            result.setDataa(e.getMessage());
            return result;
        }
    }
}
