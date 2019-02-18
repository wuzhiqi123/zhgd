package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.UUIDUtile;
import com.zhgd.controller.controllerUtile.parma.JljlParma;
import com.zhgd.pojo.Jljl;
import com.zhgd.pojo.addPojo.JljlAdd;
import com.zhgd.service.JljlService;
import com.zhgd.utile.DMIClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class JljlServiceImpl implements JljlService {
    public Result getJljl (JljlParma jljlParma){
        List<String> list = new ArrayList<String>();
        List<Jljl> JljlList = new ArrayList<Jljl>();
        Map<String,List<String>> sqlMap = new HashMap<>();
        list.add("JLNM");
        list.add("GDNM");
        list.add("JLLXNM");
        list.add("JLLX");
        list.add("SFBZ");
        list.add("RYNM");
        list.add("SJLGR");
        list.add("BZNM");
        list.add("SJLBZ");
        list.add("DWNM");
        list.add("SSDW");
        list.add("SJ");
        list.add("JLMC");
        list.add("FSSJ");
        list.add("JLYY");
        list.add("ZT");
        list.add("SJSJ");
        if("0".equals(jljlParma.getJlr() )){
            List<String> sql = new ArrayList<String>();
            sql.add("1");
            sqlMap.put("1",sql);
        }else if("1".equals(jljlParma.getJlr() )){
            List<String> sql = new ArrayList<String>();
            sql.add(jljlParma.getJlr());
            sqlMap.put("SFBZ",sql);
        }else if("2".equals(jljlParma.getJlr()) ){
            List<String> sql = new ArrayList<String>();
            sql.add(jljlParma.getJlr());
            sqlMap.put("SFBZ",sql);
        }else {
            List<String> sql = new ArrayList<String>();
            sql.add(jljlParma.getJlr());
            sqlMap.put("RYNM",sql);
        }
        try {
            Result result = new Result(ResultEnum.OK);
            Map<Integer, Map<String, String>> map = DMIClient.getDMIclient().DMI_FilterParam("RYGL_JLJL", list, sqlMap, null, null);
            for (Map.Entry<Integer, Map<String, String>> MapJljls : map.entrySet()) {
                Jljl jljl = new Jljl();
                for (Map.Entry<String, String> Mapsslxxs : MapJljls.getValue().entrySet()) {
                    if ("JLNM".equals(Mapsslxxs.getKey())) {
                        jljl.setJlnm(Mapsslxxs.getValue());
                    }
                    if ("GDNM".equals(Mapsslxxs.getKey())) {
                        jljl.setGdnm(Mapsslxxs.getValue());
                    }
                    if ("JLLXNM".equals(Mapsslxxs.getKey())) {
                        jljl.setJllxnm(Mapsslxxs.getValue());
                    }
                    if ("JLLX".equals(Mapsslxxs.getKey())) {
                        jljl.setJllx(Mapsslxxs.getValue());
                    }
                    if ("SFBZ".equals(Mapsslxxs.getKey())) {
                        jljl.setSfbz(Mapsslxxs.getValue());
                    }
                    if ("RYNM".equals(Mapsslxxs.getKey())) {
                        jljl.setRynm(Mapsslxxs.getValue());
                    }
                    if ("SJLGR".equals(Mapsslxxs.getKey())) {
                        if(!StringUtils.isEmpty(Mapsslxxs.getValue())){
                            jljl.setSjlr(Mapsslxxs.getValue());
                        }
                    }
                    if ("BZNM".equals(Mapsslxxs.getKey())) {
                        jljl.setBznm(Mapsslxxs.getValue());
                    }
                    if ("SJLBZ".equals(Mapsslxxs.getKey())) {
                        if(!StringUtils.isEmpty(Mapsslxxs.getValue())){
                            jljl.setSjlr(Mapsslxxs.getValue());
                        }
                    }
                    if ("DWNM".equals(Mapsslxxs.getKey())) {
                        jljl.setDwnm(Mapsslxxs.getValue());
                    }
                    if ("SSDW".equals(Mapsslxxs.getKey())) {
                        jljl.setSsdw(Mapsslxxs.getValue());
                    }
                    if ("SJ".equals(Mapsslxxs.getKey())) {
                        jljl.setSj(Mapsslxxs.getValue());
                    }
                    if ("JLMC".equals(Mapsslxxs.getKey())) {
                        jljl.setJlmc(Mapsslxxs.getValue());
                    }
                    if ("FSSJ".equals(Mapsslxxs.getKey())) {
                        jljl.setFssj(Mapsslxxs.getValue().split(" ")[0]);
                    }
                    if ("JLYY".equals(Mapsslxxs.getKey())) {
                        jljl.setJlyy(Mapsslxxs.getValue());
                    }
                    if ("ZT".equals(Mapsslxxs.getKey())) {
                        jljl.setZt(Mapsslxxs.getValue());
                    }
                    if ("SJSJ".equals(Mapsslxxs.getKey())) {
                        jljl.setSjsj(Mapsslxxs.getValue());
                    }
                }
                JljlList.add(jljl);
            }
            result.setDataa(JljlList);
            return result;
        }catch(Exception e){
            Result result = new Result(ResultEnum.ERROR);
            result.setDataa(e.getMessage());
            return result;
        }
    };

    public Result addJljl (Jljl jljl){
        String str = UUIDUtile.uuid();
        if (StringUtils.isEmpty(jljl.getJlnm())) {
            jljl.setJlnm(str);
        }
        JljlAdd jljlAdd = JljlUtils(jljl);
        JSONObject jsonObject = JSONObject.fromObject(jljlAdd);
        Map<String, String> sslAddmap = (Map) jsonObject;
        sslAddmap.remove("SJSJ");
        Map<String, Map<String, String>> userMap = new HashMap<>();
        userMap.put("1", sslAddmap);
        try {
            String TRANSID = DMIClient.getDMIclient().DMI_TransBegin("RYGL_JLJL",null);
            Map<String,String> TRANSMap = new HashMap<>() ;
            TRANSMap.put("TRANSID",TRANSID);
            JSONArray jsonArray = JSONArray.fromObject(TRANSMap);
            int sslInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_JLJL",userMap,jsonArray.toString());
            if(sslInt == 0){
                int comit =  DMIClient.getDMIclient().DMI_TransCommit("RYGL_JLJL",jsonArray.toString());
                if(comit == 0){
                    Result result = new Result(ResultEnum.OK);
                    return result;
                }else{
                    DMIClient.getDMIclient().DMI_TransRollback("RYGL_JLJL",jsonArray.toString());
                    Result resultError = new Result(ResultEnum.ERROR);
                    return resultError;
                }

            }else{
                DMIClient.getDMIclient().DMI_TransRollback("RYGL_JLJL",jsonArray.toString());
                Result resultError = new Result(ResultEnum.ERROR);
                return resultError;
            }

        } catch (Exception e) {
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
    };

    public JljlAdd JljlUtils(Jljl jljl){
        JljlAdd jljlAdd = new JljlAdd();
        jljlAdd.setJLNM(jljl.getJlnm());
        //jljl.getJllxnm()
        jljlAdd.setJLLXNM("1");
        //jljl.getJllx()
        jljlAdd.setJLLX("1");
        jljlAdd.setSFBZ(jljl.getSfbz());
        jljlAdd.setRYNM(jljl.getRynm());
        jljlAdd.setSJLGR(jljl.getSjlgr());
        jljlAdd.setBZNM(jljl.getBznm());
        jljlAdd.setSJLBZ(jljl.getSjlbz());
        jljlAdd.setDWNM(jljl.getDwnm());
        jljlAdd.setSSDW(jljl.getSsdw());
        jljlAdd.setSJ(jljl.getSj());
        jljlAdd.setJLMC(jljl.getJlmc());
        jljlAdd.setFSSJ(jljl.getFssj());
        jljlAdd.setJLYY(jljl.getJlyy());
        jljlAdd.setZT(jljl.getZt());
        jljlAdd.setSJSJ(jljl.getSjsj());
        jljlAdd.setGDNM("09E1F168F37043B1BB97ADA4089665DF");



        return jljlAdd;
    }
}
