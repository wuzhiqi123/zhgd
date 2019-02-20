package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.UUIDUtile;
import com.zhgd.pojo.User;
import com.zhgd.pojo.UserExpand;
import com.zhgd.pojo.Workers;
import com.zhgd.pojo.addPojo.MtxxAdd;
import com.zhgd.service.WorkersService;
import com.zhgd.utile.DMIClient;
import com.zhgd.utile.DateUtile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkersServiceImpl implements WorkersService {
    public Result add(Workers workers){
        Result result = new Result(ResultEnum.OK);
        String str = UUIDUtile.uuid();
        if(StringUtils.isEmpty(workers.getRynm()) ){
            workers.setRynm(str);
        }
     /*   workers.setXb("男");*/
        workers.setCsrq(null);
        workers.setCsrq(workers.getCsrqaa());
        User user = userUtile(workers);
        JSONObject jsonObject =  JSONObject.fromObject(user);
        Map<String ,String > Usermap = (Map)jsonObject;
        Usermap.remove("SJSJ");
        Map<String,Map<String ,String>> userMap = new HashMap<>();
        userMap.put("1",Usermap);
        try{
            /*Map<Integer, Map<String,String>> map =*/
           String TRANSID = DMIClient.getDMIclient().DMI_TransBegin("RYGL_RYXX_JBXX",null);
           Map<String,String> TRANSMap = new HashMap<>() ;
            TRANSMap.put("TRANSID",TRANSID);
            //JSONObject jsonObject1 = JSONObject.fromObject(TRANSMap);
            JSONArray jsonArray = JSONArray.fromObject(TRANSMap);
           int userInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_RYXX_JBXX",userMap,jsonArray.toString());
           if(userInt == 0){
               UserExpand userExpand = lwxx(workers);
               JSONObject jsonUserExpand=  JSONObject.fromObject(userExpand);
               Map<String ,String > UserExpandmap = (Map)jsonUserExpand;
               UserExpandmap.remove("SJSJ");
               Map<String,Map<String ,String>> UserExpandMap = new HashMap<>();
               UserExpandMap.put("1",UserExpandmap);
               int lwxxInt = DMIClient.getDMIclient().DMI_UpdateData("RYGL_RYXX_LWXX",UserExpandMap,jsonArray.toString());
               if(lwxxInt == 0){
                   int mtxx1 = 0;
 /*                  List<MtxxAdd> mtxxList = mtxx(workers);
                   for(MtxxAdd mtxxAdd :mtxxList){
                       JSONObject jsonmtxxAdd=  JSONObject.fromObject(mtxxAdd);
                       Map<String ,String > mtxxAddmap = (Map)jsonmtxxAdd;
                       Map<String,Map<String ,String>> mtxxAddMap = new HashMap<>();
                       mtxxAddMap.put("1",mtxxAddmap);
                       int mtxx = DMIClient.getDMIclient().DMI_UpdateData("RYGL_RYXX_MTXX",mtxxAddMap,jsonArray.toString());
                       if(mtxx == 0){
                           mtxx1 = 0;
                       }else{
                           DMIClient.getDMIclient().DMI_TransRollback("RYGL_RYXX_JBXX",jsonArray.toString());
                           Result resultError = new Result(ResultEnum.ERROR);
                           resultError.setDataa("程序错误,员工添加失败");
                           return resultError;
                       }
                   }*/
                  if(mtxx1 == 0){
                      int comit =  DMIClient.getDMIclient().DMI_TransCommit("RYGL_RYXX_JBXX",jsonArray.toString());
                      if(comit == 0){
                          return result;
                      }else{
                          DMIClient.getDMIclient().DMI_TransRollback("RYGL_RYXX_JBXX",jsonArray.toString());
                          Result resultError = new Result(ResultEnum.ERROR);
                          resultError.setDataa("程序错误,员工添加失败");
                          return resultError;
                      }

                  }else{
                      DMIClient.getDMIclient().DMI_TransRollback("RYGL_RYXX_JBXX",jsonArray.toString());
                      Result resultError = new Result(ResultEnum.ERROR);
                      resultError.setDataa("程序错误,员工添加失败");
                      return resultError;
                  }
               }else{
                   DMIClient.getDMIclient().DMI_TransRollback("RYGL_RYXX_JBXX",jsonArray.toString());
                   Result resultError = new Result(ResultEnum.ERROR);
                   resultError.setDataa("程序错误,员工添加失败");
                   return resultError;
               }
           }else{
               DMIClient.getDMIclient().DMI_TransRollback("RYGL_RYXX_JBXX",jsonArray.toString());
               Result resultError = new Result(ResultEnum.ERROR);
               resultError.setDataa("程序错误,员工添加失败");
               return resultError;
           }
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            resultError.setDataa("程序错误,员工添加失败");
            return resultError;
        }
    }
    public UserExpand lwxx(Workers w){
        UserExpand userExpand = new UserExpand();
        userExpand.setRYNM(w.getRynm());
        userExpand.setGRGZLBNM(w.getGrgzlbnm());
        userExpand.setGRGZ(w.getGrgz());
        userExpand.setDWNM(w.getDwnm());
        userExpand.setGRSSDW(w.getGrssdw());
        userExpand.setBZNM(w.getBznm());
        userExpand.setGRSSBZ(w.getGrssbz());
        userExpand.setGRJCRQ(DateUtile.getDatexiegang());
        userExpand.setYHNM(w.getYhnm());
        userExpand.setYHKH(w.getYhkh());
        userExpand.setKHH(w.getKhh());
        return userExpand;
    }
    public List<MtxxAdd> mtxx(Workers w){
        List<MtxxAdd> mtxxList = new ArrayList<MtxxAdd>();
            MtxxAdd mtxxAdd1 = new MtxxAdd();
            mtxxAdd1.setRYNM(w.getRynm());
            //
        if(w.getSrc().split(",").length>1){
            mtxxAdd1.setZP(w.getSrc().split(",")[1]);
        }
            MtxxAdd mtxxAdd2 = new MtxxAdd();
            mtxxAdd2.setRYNM(w.getRynm());
            //
            if(w.getPaizhao().split(",").length>1){
                mtxxAdd2.setZP(w.getPaizhao().split(",")[1]);
            }
            mtxxList.add(mtxxAdd1);
            mtxxList.add(mtxxAdd2);

        return mtxxList;
    }
    public User userUtile(Workers w){
        User user = new User();
        user.setRYNM(w.getRynm());
        user.setSFZH(w.getSfzh());
        user.setXM(w.getXm());
        user.setCSRQ(w.getCsrq());
        user.setJG(w.getJg());
        user.setJTZZ(w.getJtzz());
        user.setWHCD(w.getWhcd());
        user.setWHCDNM(w.getWhcdnm());
        user.setJJLXR(w.getJjlxr());
        user.setXB(w.getXb());
        user.setXBNM(w.getXbnm());
        user.setMZ(w.getMz());
        user.setMZNM(w.getMznm());
        user.setZZMM(w.getZzmm());
        user.setZZMMNM(w.getZzmmnm());
        user.setJJLXDH(w.getJjlxdh());
        user.setSJSJ(w.getSjsj());
        user.setLXDH(w.getLxdh());
        user.setTC(w.getTc());
        return user;
    }
}
