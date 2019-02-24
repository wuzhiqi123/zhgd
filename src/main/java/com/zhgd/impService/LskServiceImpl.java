package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.UUIDUtile;
import com.zhgd.controller.controllerUtile.parma.LskParma;
import com.zhgd.pojo.Lsk;
import com.zhgd.pojo.addPojo.LskAdd;
import com.zhgd.service.LskService;
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
public class LskServiceImpl implements LskService {
    public Result getLskxx(LskParma lskParma){
        List<String> list = new ArrayList<String>();
        List<Lsk> lskList = new ArrayList<Lsk>();
        list.add("NM");
        list.add("KH");
        list.add("CKRSFZH");
        list.add("CKRXM");
        list.add("ZKRQ");
        list.add("RZLXNM");
        list.add("RZLX");
        list.add("YXQ");
        list.add("ZT");
        list.add("BZ");
        list.add("SJSJ");
        list.add("GDNM");
        Map<String, List<String>> sqlMap = new HashMap<>();
        String stringQY = null;
        Map<String,String> stringMap = new HashMap<>() ;

        if ("0".equals(lskParma.getZt())) {
            List<String> sql = new ArrayList<String>();
            sql.add("1");
            sqlMap.put("1", sql);
        } else {
            List<String> sql = new ArrayList<String>();
            sql.add(lskParma.getZt());
            sqlMap.put("ZT", sql);
        }
        if(!StringUtils.isEmpty(lskParma.getParma())){
            stringQY = "(CKRXM like '%"+lskParma.getParma()+"%' or KH like '%" + lskParma.getParma()+"%')";
        }else{
            stringQY = "1=1";
        }
        try{
            Result result = new Result(ResultEnum.OK);
            stringMap.put("SQL",stringQY);
            JSONArray jsonObject = JSONArray.fromObject(stringMap);
            Map<Integer, Map<String, String>> map = DMIClient.getDMIclient().DMI_FilterParam("RYGL_LSKGL_LSKXX", list, sqlMap, null, jsonObject.toString());
            for(Map.Entry<Integer,Map<String ,String>> lskMaps:map.entrySet()){
                Lsk lsk = new Lsk();
                for(Map.Entry<String,String> lskMap :lskMaps.getValue().entrySet()){
                    if ("NM".equals(lskMap.getKey())) {
                        lsk.setNm(lskMap.getValue());
                    }
                    if ("KH".equals(lskMap.getKey())) {
                        lsk.setKh(lskMap.getValue());
                    }
                    if ("CKRSFZH".equals(lskMap.getKey())) {
                        lsk.setCkrsfzh(lskMap.getValue());
                    }
                    if ("CKRXM".equals(lskMap.getKey())) {
                        lsk.setCkrxm(lskMap.getValue());
                    }
                    if ("ZKRQ".equals(lskMap.getKey())) {
                        lsk.setZkrq(lskMap.getValue());
                    }
                    if ("RZLXNM".equals(lskMap.getKey())) {
                        lsk.setRzlxnm(lskMap.getValue());
                    }
                    if ("RZLX".equals(lskMap.getKey())) {
                        lsk.setRzlx(lskMap.getValue());
                    }
                    if ("YXQ".equals(lskMap.getKey())) {
                        lsk.setYxq(lskMap.getValue());
                    }
                    if ("ZT".equals(lskMap.getKey())) {
                        lsk.setZt(lskMap.getValue());
                    }
                    if ("BZ".equals(lskMap.getKey())) {
                        lsk.setBz(lskMap.getValue());
                    }
                    if ("SJSJ".equals(lskMap.getKey())) {
                        lsk.setSjsj(lskMap.getValue());
                    }
                    if ("GDNM".equals(lskMap.getKey())) {
                        lsk.setGdnm(lskMap.getValue());
                    }
                }
                lskList.add(lsk);
            }
            result.setDataa(lskList);
            return result;
        }catch(Exception e){
            Result result = new Result(ResultEnum.ERROR);
            return  result;
        }
    }
    public Result addLskxx(Lsk lsk){
        Map<String, String> sslAddmap ;
        String str = UUIDUtile.uuid();
        if (StringUtils.isEmpty(lsk.getNm())) {
            lsk.setNm(str);
            LskAdd lskAdd = lsk(lsk);
            JSONObject jsonObject = JSONObject.fromObject(lskAdd);
            sslAddmap = (Map) jsonObject;
            sslAddmap.remove("SJSJ");
        }else{
            if("1".equals(lsk.getZt())){
                lsk.setZt("2");
            }else if("2".equals(lsk.getZt())){
                lsk.setZt("1");
            }
            LskAdd lskAdd = lsk(lsk);
            JSONObject jsonObject = JSONObject.fromObject(lskAdd);
            sslAddmap = (Map) jsonObject;
        }
        Map<String, Map<String, String>> userMap = new HashMap<>();
        userMap.put("1", sslAddmap);
        try {
            String TRANSID = DMIClient.getDMIclient().DMI_TransBegin("RYGL_LSKGL_LSKXX",null);
            Map<String,String> TRANSMap = new HashMap<>() ;
            TRANSMap.put("TRANSID",TRANSID);
            JSONArray jsonArray = JSONArray.fromObject(TRANSMap);
            int sslInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_LSKGL_LSKXX",userMap,jsonArray.toString());
            if(sslInt == 0){
                int comit =  DMIClient.getDMIclient().DMI_TransCommit("RYGL_LSKGL_LSKXX",jsonArray.toString());
                if(comit == 0){
                    Result result = new Result(ResultEnum.OK);
                    return result;
                }else{
                    DMIClient.getDMIclient().DMI_TransRollback("RYGL_LSKGL_LSKXX",jsonArray.toString());
                    Result resultError = new Result(ResultEnum.ERROR);
                    return resultError;
                }

            }else{
                DMIClient.getDMIclient().DMI_TransRollback("RYGL_LSKGL_LSKXX",jsonArray.toString());
                Result resultError = new Result(ResultEnum.ERROR);
                return resultError;
            }

        } catch (Exception e) {
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
    };

    public LskAdd lsk(Lsk lsk){
        LskAdd lskAdd = new LskAdd();
        lskAdd.setNM(lsk.getNm());
        lskAdd.setKH(lsk.getKh());
        lskAdd.setCKRSFZH(lsk.getCkrsfzh());
        lskAdd.setCKRXM(lsk.getCkrxm());
        lskAdd.setZKRQ(lsk.getZkrq());
        lskAdd.setRZLXNM("1");
        lskAdd.setRZLX("1");
        lskAdd.setSJSJ(lsk.getSjsj());
        lskAdd.setYXQ(lsk.getYxq());
        lskAdd.setZT(lsk.getZt());
        lskAdd.setBZ(lsk.getBz());
        lskAdd.setGDNM("09E1F168F37043B1BB97ADA4089665DF");
        return lskAdd;
    }
}
