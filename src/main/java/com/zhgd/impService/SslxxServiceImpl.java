package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.UUIDUtile;
import com.zhgd.controller.controllerUtile.parma.SsxxParma;
import com.zhgd.pojo.Sslxx;
import com.zhgd.pojo.Ssxx;
import com.zhgd.pojo.addPojo.SslAdd;
import com.zhgd.pojo.addPojo.SsxxAdd;
import com.zhgd.service.SslxxService;
import com.zhgd.utile.DMIClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SslxxServiceImpl implements SslxxService {
    public Result getSslxx() {
        List<String> list = new ArrayList<String>();
        List<Sslxx> sslxxeList = new ArrayList<Sslxx>();
        list.add("SSLNM");
        list.add("SSLMC");
        list.add("SSLSX");
        list.add("SSLWZ");
        list.add("BZ");
        list.add("SJSJ");
        list.add("GDNM");
        try {
            Result result = new Result(ResultEnum.OK);
            Map<Integer, Map<String, String>> map = DMIClient.getDMIclient().DMI_FilterParam("RYGL_SSGL_SSLXX", list, null, null, null);
            for (Map.Entry<Integer, Map<String, String>> Mapsslxxss : map.entrySet()) {
                Sslxx sslxx = new Sslxx();
                for (Map.Entry<String, String> Mapsslxxs : Mapsslxxss.getValue().entrySet()) {
                    if ("SSLNM".equals(Mapsslxxs.getKey())) {
                        sslxx.setSslnm(Mapsslxxs.getValue());
                    }
                    if ("SSLMC".equals(Mapsslxxs.getKey())) {
                        sslxx.setSslmc(Mapsslxxs.getValue());
                    }
                    if ("SSLSX".equals(Mapsslxxs.getKey())) {
                        sslxx.setSslsx(Mapsslxxs.getValue());
                    }
                    if ("SSLWZ".equals(Mapsslxxs.getKey())) {
                        sslxx.setSslwz(Mapsslxxs.getValue());
                    }
                    if ("BZ".equals(Mapsslxxs.getKey())) {
                        sslxx.setBz(Mapsslxxs.getValue());
                    }
                    if ("SJSJ".equals(Mapsslxxs.getKey())) {
                        sslxx.setSjsj(Mapsslxxs.getValue());
                    }
                    if ("GDNM".equals(Mapsslxxs.getKey())) {
                        sslxx.setGdnm(Mapsslxxs.getValue());
                    }
                }
                sslxxeList.add(sslxx);
            }
            result.setDataa(sslxxeList);
            return result;
        } catch (Exception e) {
            Result result = new Result(ResultEnum.ERROR);
            result.setDataa(e.getMessage());
            return result;
        }
    }

    public Result getSsxx(SsxxParma ssxxParma) {
        List<String> list = new ArrayList<String>();
        List<Ssxx> sslxxeList = new ArrayList<Ssxx>();
        list.add("SSNM");
        list.add("SSL");
        list.add("LC");
        list.add("FJH");
        list.add("ZDRZRS");
        list.add("SJSJ");
        list.add("GDNM");
        Map<String, List<String>> sqlMap = new HashMap<>();
        if (StringUtils.isEmpty(ssxxParma.getSslxx())) {
            List<String> sql = new ArrayList<String>();
            sql.add("1");
            sqlMap.put("1", sql);
        } else {
            List<String> sql = new ArrayList<String>();
            sql.add(ssxxParma.getSslxx());
            sqlMap.put("SSL", sql);
        }
        try {
            Result result = new Result(ResultEnum.OK);
            Map<Integer, Map<String, String>> map = DMIClient.getDMIclient().DMI_FilterParam("RYGL_SSGL_SSXX", list, sqlMap, null, null);
            for (Map.Entry<Integer, Map<String, String>> Mapsslxxss : map.entrySet()) {
                Ssxx ssxx = new Ssxx();
                for (Map.Entry<String, String> Mapsslxxs : Mapsslxxss.getValue().entrySet()) {
                    if ("SSNM".equals(Mapsslxxs.getKey())) {
                        ssxx.setSsnm(Mapsslxxs.getValue());
                    }
                    if ("SSL".equals(Mapsslxxs.getKey())) {
                        ssxx.setSsl(Mapsslxxs.getValue());
                    }
                    if ("LC".equals(Mapsslxxs.getKey())) {
                        ssxx.setLc(Mapsslxxs.getValue());
                    }
                    if ("FJH".equals(Mapsslxxs.getKey())) {
                        ssxx.setFjh(Mapsslxxs.getValue());
                    }
                    if ("ZDRZRS".equals(Mapsslxxs.getKey())) {
                        ssxx.setZdrzrs(Mapsslxxs.getValue());
                    }
                    if ("SJSJ".equals(Mapsslxxs.getKey())) {
                        ssxx.setSjsj(Mapsslxxs.getValue());
                    }
                    if ("GDNM".equals(Mapsslxxs.getKey())) {
                        ssxx.setGdnm(Mapsslxxs.getValue());
                    }
                }
                sslxxeList.add(ssxx);
            }

            Map<String, List<Ssxx>> ssxxMap = new HashMap<String, List<Ssxx>>();
            for (Ssxx a : sslxxeList) {
                if (ssxxMap.containsKey(a.getLc())) {
                    ssxxMap.get(a.getLc()).add(a);
                } else {
                    List<Ssxx> ss = new ArrayList<Ssxx>();
                    ss.add(a);
                    ssxxMap.put(a.getLc(), ss);
                }
            }

            result.setDataa(ssxxMap);
            return result;
        } catch (Exception e) {
            Result result = new Result(ResultEnum.ERROR);
            result.setDataa(e.getMessage());
            return result;
        }
    }


    public Result addSsLxx(Sslxx sslxx) {
        String str = UUIDUtile.uuid();
        if (StringUtils.isEmpty(sslxx.getSslnm())) {
            sslxx.setSslnm(str);
        }
        SslAdd sslAdd = ssl(sslxx);
        JSONObject jsonObject = JSONObject.fromObject(sslAdd);
        Map<String, String> sslAddmap = (Map) jsonObject;
        sslAddmap.remove("SJSJ");
        Map<String, Map<String, String>> userMap = new HashMap<>();
        userMap.put("1", sslAddmap);
        try {
            String TRANSID = DMIClient.getDMIclient().DMI_TransBegin("RYGL_SSGL_SSLXX",null);
            Map<String,String> TRANSMap = new HashMap<>() ;
            TRANSMap.put("TRANSID",TRANSID);
            JSONArray jsonArray = JSONArray.fromObject(TRANSMap);
            int sslInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_SSGL_SSLXX",userMap,jsonArray.toString());
            if(sslInt == 0){
                int comit =  DMIClient.getDMIclient().DMI_TransCommit("RYGL_SSGL_SSLXX",jsonArray.toString());
                if(comit == 0){
                    Result result = new Result(ResultEnum.OK);
                    return result;
                }else{
                    DMIClient.getDMIclient().DMI_TransRollback("RYGL_SSGL_SSLXX",jsonArray.toString());
                    Result resultError = new Result(ResultEnum.ERROR);
                    return resultError;
                }

            }else{
                DMIClient.getDMIclient().DMI_TransRollback("RYGL_SSGL_SSLXX",jsonArray.toString());
                Result resultError = new Result(ResultEnum.ERROR);
                return resultError;
            }

        } catch (Exception e) {
            Result resultError = new Result(ResultEnum.ERROR);
            resultError.setDataa(e.getMessage());
            return resultError;
        }
    }

    public Result addLcxx(Ssxx ssxx){
        String str = UUIDUtile.uuid();
        if (StringUtils.isEmpty(ssxx.getSsnm())) {
            ssxx.setSsnm(str);
        }
        SsxxAdd ssxxAdd = ssxxAdd(ssxx);
        JSONObject jsonObject = JSONObject.fromObject(ssxxAdd);
        Map<String, String> ssxxAddmap = (Map) jsonObject;
        ssxxAddmap.remove("SJSJ");
        Map<String, Map<String, String>> userMap = new HashMap<>();
        userMap.put("1", ssxxAddmap);
        try {
            String TRANSID = DMIClient.getDMIclient().DMI_TransBegin("RYGL_SSGL_SSXX",null);
            Map<String,String> TRANSMap = new HashMap<>() ;
            TRANSMap.put("TRANSID",TRANSID);
            JSONArray jsonArray = JSONArray.fromObject(TRANSMap);
            int sslInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_SSGL_SSXX",userMap,jsonArray.toString());
            if(sslInt == 0){
                int comit =  DMIClient.getDMIclient().DMI_TransCommit("RYGL_SSGL_SSXX",jsonArray.toString());
                if(comit == 0){
                    Result result = new Result(ResultEnum.OK);
                    return result;
                }else{
                    DMIClient.getDMIclient().DMI_TransRollback("RYGL_SSGL_SSXX",jsonArray.toString());
                    Result resultError = new Result(ResultEnum.ERROR);
                    return resultError;
                }

            }else{
                DMIClient.getDMIclient().DMI_TransRollback("RYGL_SSGL_SSXX",jsonArray.toString());
                Result resultError = new Result(ResultEnum.ERROR);
                return resultError;
            }

        } catch (Exception e) {
            Result resultError = new Result(ResultEnum.ERROR);
            resultError.setDataa(e.getMessage());
            return resultError;
        }
    };

    public SslAdd ssl(Sslxx sslxx){
        SslAdd sslAdd = new SslAdd();
        sslAdd.setSSLNM(sslxx.getSslnm());
        sslAdd.setSSLMC(sslxx.getSslmc());
        sslAdd.setSSLSX(sslxx.getSslsx());
        sslAdd.setSSLWZ(sslxx.getSslwz());
        sslAdd.setBZ(sslxx.getBz());
        sslAdd.setGDNM("09E1F168F37043B1BB97ADA4089665DF");
        return sslAdd;
    }
    public SsxxAdd ssxxAdd(Ssxx ssxx){
        SsxxAdd ssxxAdd = new SsxxAdd();
        ssxxAdd.setSSNM(ssxx.getSsnm());
        ssxxAdd.setSSL(ssxx.getSsl());
        ssxxAdd.setLC(ssxx.getLc());
        ssxxAdd.setFJH(ssxx.getFjh());
        ssxxAdd.setZDRZRS(ssxx.getZdrzrs());
        ssxxAdd.setGDNM("09E1F168F37043B1BB97ADA4089665DF");
        return ssxxAdd;
    }
}
