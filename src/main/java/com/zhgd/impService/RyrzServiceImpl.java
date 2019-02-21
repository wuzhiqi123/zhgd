package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.UUIDUtile;
import com.zhgd.pojo.Ryrz;
import com.zhgd.pojo.Workers;
import com.zhgd.pojo.addPojo.AddRyrz;
import com.zhgd.service.RyrzService;
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
public class RyrzServiceImpl implements RyrzService {
    public Result addRyrz(AddRyrz addRyrz){

        /*if(StringUtils.isEmpty(addRyrz.getNm()) ){
        }*/
        try{
            String TRANSID = DMIClient.getDMIclient().DMI_TransBegin("RYGL_R_SS_RZ",null);
            Map<String,String> TRANSMap = new HashMap<>() ;
            TRANSMap.put("TRANSID",TRANSID);
            JSONArray jsonArray = JSONArray.fromObject(TRANSMap);
            int RyrzInt = 0;
            List<Ryrz> aqjypx =  ryrzUtile(addRyrz);
            //JSONArray RyrzjsonArray =  JSONArray.fromObject(aqjypx);
            if(aqjypx.size()>0){
                for(int i = 0; i < aqjypx.size(); i++){
                    Map<String,Map<String ,String>> RyrzMap = new HashMap<>();
                    JSONObject jsonObject = JSONObject.fromObject(aqjypx.get(i));
                    Map<String ,String > RyrzRyMap = (Map)jsonObject;
                    RyrzRyMap.remove("SJSJ");
                    RyrzMap.put("1",RyrzRyMap);
                    RyrzInt += DMIClient.getDMIclient().DMI_UpdateData("RYGL_R_SS_RZ",RyrzMap,jsonArray.toString());
                }
            }
            if(RyrzInt == 0){
                int comit =  DMIClient.getDMIclient().DMI_TransCommit("RYGL_R_SS_RZ",jsonArray.toString());
                if(comit == 0){
                    Result result = new Result(ResultEnum.OK);
                    return result;
                }else{
                    DMIClient.getDMIclient().DMI_TransRollback("RYGL_R_SS_RZ",jsonArray.toString());
                    Result resultError = new Result(ResultEnum.ERROR);
                    resultError.setDataa("程序错误,人员入住失败");
                    return resultError;
                }
            }else{
                DMIClient.getDMIclient().DMI_TransRollback("RYGL_R_SS_RZ",jsonArray.toString());
                Result resultError = new Result(ResultEnum.ERROR);
                resultError.setDataa("程序错误,人员入住失败");
                return resultError;
            }
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            resultError.setDataa("程序错误,人员入住失败");
            return resultError;
        }
    }

    public List<Ryrz> ryrzUtile(AddRyrz addRyrz){
        List<Ryrz> ryrzList = new ArrayList<>();
        for(Workers workers :addRyrz.getWorkersList()){
            Ryrz ryrz =  new Ryrz();
            String str = UUIDUtile.uuid();
            addRyrz.setNm(str);
            ryrz.setNM(addRyrz.getNm());
            ryrz.setSSNM(addRyrz.getSsnm());
            ryrz.setRYNM(workers.getRynm());
            ryrzList.add(ryrz);
        }
        return ryrzList;
    }
}
