package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.UUIDUtile;
import com.zhgd.pojo.Aqjypx;
import com.zhgd.pojo.AqjypxRy;
import com.zhgd.pojo.Workers;
import com.zhgd.pojo.addPojo.AddAqjyox;
import com.zhgd.service.AqjypxService;
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
public class AqjypxServiceImpl implements AqjypxService {
    public Result addAqjypx(AddAqjyox addAqjyox){
        Result result = new Result(ResultEnum.OK);
        String str = UUIDUtile.uuid();
        if(StringUtils.isEmpty(addAqjyox.getPxnm()) ){
            addAqjyox.setPxnm(str);
        }
        Aqjypx aqjypx =  aqjypxUtile(addAqjyox);
        JSONObject jsonObject =  JSONObject.fromObject(aqjypx);
        Map<String ,String > Aqjypxmap = (Map)jsonObject;
        Aqjypxmap.remove("SJSJ");
        Map<String,Map<String ,String>> aqjypxMap = new HashMap<>();
        aqjypxMap.put("1",Aqjypxmap);
        try{
            /*Map<Integer, Map<String,String>> map =*/
            String TRANSID = DMIClient.getDMIclient().DMI_TransBegin("RYGL_AQJYPX",null);
            Map<String,String> TRANSMap = new HashMap<>() ;
            TRANSMap.put("TRANSID",TRANSID);
            JSONArray jsonArray = JSONArray.fromObject(TRANSMap);
            int aqjypxInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_AQJYPX",aqjypxMap,jsonArray.toString());
            if(aqjypxInt == 0){
                List<AqjypxRy> aqjypxRy = aqjypxRyUtile(addAqjyox);
                JSONArray jsonUserExpand=  JSONArray.fromObject(aqjypxRy);
                Map<String,Map<String ,String>> aqjypxRyMap = new HashMap<>();
                if(!jsonUserExpand.isEmpty()){
                    for(int i = 0; i < jsonUserExpand.size(); i++){
                        Map<String ,String > AqjypxRyMapmap = (Map)jsonUserExpand.get(i);
                        AqjypxRyMapmap.remove("SJSJ");
                        aqjypxRyMap.put(String.valueOf(i),AqjypxRyMapmap);
                    }
                }
                int aqjypxRyInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_R_AQJYPX_RY",aqjypxRyMap,jsonArray.toString());
                if(aqjypxRyInt == 0){
                        int comit =  DMIClient.getDMIclient().DMI_TransCommit("RYGL_AQJYPX",jsonArray.toString());
                        if(comit == 0){
                            return result;
                        }else{
                            DMIClient.getDMIclient().DMI_TransRollback("RYGL_AQJYPX",jsonArray.toString());
                            Result resultError = new Result(ResultEnum.ERROR);
                            resultError.setDataa("程序错误,安全教育添加失败");
                            return resultError;
                        }

                }else{
                    DMIClient.getDMIclient().DMI_TransRollback("RYGL_AQJYPX",jsonArray.toString());
                    Result resultError = new Result(ResultEnum.ERROR);
                    resultError.setDataa("程序错误,安全教育添加失败");
                    return resultError;
                }
            }else{
                DMIClient.getDMIclient().DMI_TransRollback("RYGL_AQJYPX",jsonArray.toString());
                Result resultError = new Result(ResultEnum.ERROR);
                resultError.setDataa("程序错误,安全教育添加失败");
                return resultError;
            }
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            resultError.setDataa("程序错误,安全教育添加失败");
            return resultError;
        }
    }

    public Aqjypx aqjypxUtile(AddAqjyox addAqjyox){
        Aqjypx aqjypx =  new Aqjypx();
        aqjypx.setPXNM(addAqjyox.getPxnm());
        aqjypx.setPXLXNM(addAqjyox.getPxlxnm());
        aqjypx.setPXLX(addAqjyox.getPxlx());
        aqjypx.setPXSJ(addAqjyox.getPxsj());
        aqjypx.setPXZT(addAqjyox.getPxzt());
        aqjypx.setPXR(addAqjyox.getPxr());
        aqjypx.setPXDD(addAqjyox.getPxdd());
        aqjypx.setPXNR(addAqjyox.getPxnr());
        aqjypx.setBZ(addAqjyox.getBz());
        aqjypx.setFJ(addAqjyox.getFj());
        aqjypx.setGDNM("09E1F168F37043B1BB97ADA4089665DF");
        return aqjypx;
    }


    public List<AqjypxRy> aqjypxRyUtile(AddAqjyox addAqjyox){
        List<AqjypxRy> aqjypxRyList = new ArrayList<>();
        for(Workers workers :addAqjyox.getWorkersList()){
            AqjypxRy aqjypxRy =  new AqjypxRy();
            aqjypxRy.setNM(UUIDUtile.uuid());
            aqjypxRy.setPXNM(addAqjyox.getPxnm());
            aqjypxRy.setRYNM(workers.getRynm());
            aqjypxRyList.add(aqjypxRy);
        }

        return aqjypxRyList;
    }
}
