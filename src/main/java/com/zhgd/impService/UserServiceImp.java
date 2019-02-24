package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.MapSort;
import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.parma.ZpParma;
import com.zhgd.pojo.Workers;
import com.zhgd.pojo.Zp;
import com.zhgd.service.UserService;
import com.zhgd.utile.DMIClient;
import com.zhgd.utile.Pzdq;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImp implements UserService {


    @Override
    public Result getUser(Workers worker){
        List<String> list = new ArrayList<String>();
        List<Workers> workersList = new ArrayList<Workers>();
        list.add("RYNM");
        list.add("SFZH");
        list.add("XM");
        list.add("CSRQ");
        list.add("JG");
        list.add("JTZZ");
        list.add("WHCD");
        list.add("WHCDNM");
        list.add("JJLXR");
        list.add("XB");
        list.add("XBNM");
        list.add("MZ");
        list.add("MZNM");
        list.add("ZZMM");
        list.add("ZZMMNM");
        list.add("JJLXDH");
        list.add("SJSJ");
        list.add("TC");
        list.add("LXDH");
        list.add("GRGZLBNM");
        list.add("GRGZ");
        list.add("DWNM");
        list.add("GRSSDW");
        list.add("BZNM");
        list.add("GRSSBZ");
        list.add("GRJCRQ");
        list.add("GRTCRQ");
        list.add("YHNM");
        list.add("KHH");
        list.add("YHKH");
        list.add("NM");
        list.add("SSNM");
        list.add("SFZZP");
        list.add("ZDYZP");
/*        String stringQyCondition = null;
        if(!StringUtils.isEmpty(worker.getDwnm())){
            stringQyCondition = "DWNM = '"+worker.getDwnm()+"'";
        }*/
        Map<String,List<String>> sqlMap = new HashMap<>();
        String stringQY = null;
        Map<String,String> stringMap = new HashMap<>() ;
        if(StringUtils.isEmpty(worker.getDwnm())){
            List<String> sql = new ArrayList<String>();
            sql.add("1");
            sqlMap.put("1",sql);
        }else{
            List<String> sql = new ArrayList<String>();
            sql.add(worker.getDwnm());
            sqlMap.put("DWNM",sql);
        }
        if(StringUtils.isEmpty(worker.getBznm())){
            List<String> sql = new ArrayList<String>();
            sql.add("1");
            sqlMap.put("1",sql);
        }else{
            List<String> sql = new ArrayList<String>();
            sql.add(worker.getBznm());
            sqlMap.put("BZNM",sql);
        }
        if(!StringUtils.isEmpty(worker.getGrkh())){
            stringQY = "(XM like '%"+worker.getGrkh()+"%'  OR   LXDH like '%"+worker.getGrkh()+"%')";
        }
        stringMap.put("SQL",stringQY);
        JSONArray jsonObject = JSONArray.fromObject(stringMap);
        try {
            Result result = new Result(ResultEnum.OK);
            Map<Integer, Map<String, String>> map = DMIClient.getDMIclient().DMI_FilterParam("RYGL_RYXX_VIEW", list, sqlMap, null, jsonObject.toString());
            for (Map.Entry<Integer, Map<String, String>> users : map.entrySet()) {
                Workers workers = new Workers();
                for (Map.Entry<String, String> user : users.getValue().entrySet()) {
                    if ("RYNM".equals(user.getKey())) {
                        workers.setRynm(user.getValue());
                    }
                    if ("XM".equals(user.getKey())) {
                        workers.setXm(user.getValue());
                    }
                    if ("XBNM".equals(user.getKey())) {
                        workers.setXbnm(user.getValue());
                    }
                    if ("XB".equals(user.getKey())) {
                        workers.setXb(user.getValue());
                    }
                    if ("CSRQ".equals(user.getKey())) {
                        workers.setCsrq(user.getValue().split(" ")[0]);
                    }
                    if ("SFZH".equals(user.getKey())) {
                        workers.setSfzh(user.getValue());
                    }
                    if ("MZNM".equals(user.getKey())) {
                        workers.setMznm(user.getValue());
                    }
                    if ("MZ".equals(user.getKey())) {
                        workers.setMz(user.getValue());
                    }
                    if ("ZZMMNM".equals(user.getKey())) {
                        workers.setZzmmnm(user.getValue());
                    }
                    if ("ZZMM".equals(user.getKey())) {
                        workers.setZzmm(user.getValue());
                    }
                    if ("JG".equals(user.getKey())) {
                        workers.setJg(user.getValue());
                    }
                    if ("JTZZ".equals(user.getKey())) {
                        workers.setJtzz(user.getValue());
                    }
                    if ("LXDH".equals(user.getKey())) {
                        workers.setLxdh(user.getValue());
                    }
                    if ("WHCDNM".equals(user.getKey())) {
                        workers.setWhcdnm(user.getValue());
                    }
                    if ("WHCD".equals(user.getKey())) {
                        workers.setWhcd(user.getValue());
                    }
                    if ("TC".equals(user.getKey())) {
                        workers.setTc(user.getValue());
                    }
                    if ("JJLXR".equals(user.getKey())) {
                        workers.setJjlxr(user.getValue());
                    }
                    if ("JJLXDH".equals(user.getKey())) {
                        workers.setJjlxdh(user.getValue());
                    }
                    if ("SJSJ".equals(user.getKey())) {
                        workers.setSjsj(user.getValue());
                    }
                    if("GRGZLBNM".equals(user.getKey())){
                        workers.setGrgzlbnm(user.getValue());
                    }
                    if("GRGZ".equals(user.getKey())){
                        workers.setGrgz(user.getValue());
                    }
                    if("DWNM".equals(user.getKey())){
                        workers.setDwnm(user.getValue());
                    }
                    if("GRSSDW".equals(user.getKey())){
                        workers.setGrssdw(user.getValue());
                    }
                    if("BZNM".equals(user.getKey())){
                        workers.setBznm(user.getValue());
                    }
                    if("GRSSBZ".equals(user.getKey())){
                        workers.setGrssbz(user.getValue());
                    }
                    if("GRJCRQ".equals(user.getKey())){
                        workers.setGrjcrq(user.getValue());
                    }
                    if("GRTCRQ".equals(user.getKey())){
                        workers.setGrtcrq(user.getValue());
                    }
                    if("YHNM".equals(user.getKey())){
                        workers.setYhnm(user.getValue());
                    }
                    if("KHH".equals(user.getKey())){
                        workers.setKhh(user.getValue());
                    }
                    if("YHKH".equals(user.getKey())){
                        workers.setYhkh(user.getValue());
                    }
                    if("NM".equals(user.getKey())){
                        workers.setNm(user.getValue());
                    }
                    if("SSNM".equals(user.getKey())){
                        workers.setSsnm(user.getValue());
                    }
                    if("SFZZP".equals(user.getKey()) && !"".equals(user.getValue())){
                        String img = "data:image/jpg;base64,";
                        String sfzzp =  img+user.getValue();
                        workers.setSfzzp(sfzzp);
                        if(!StringUtils.isEmpty(user.getValue())){
                            workers.setImage(sfzzp);
                        }
                    }
                    if("ZDYZP".equals(user.getKey())){
                        String zdyzp = "data:image/jpg;base64,"+user.getValue();
                        workers.setZdyzp(zdyzp);
                        if(!StringUtils.isEmpty(user.getValue()) && !"".equals(user.getValue())){
                            workers.setImage(zdyzp);
                        }
                    }
                }
                workersList.add(workers);
            }
            Map<String, Integer> workersMap = new HashMap<String,Integer>();
            for (Workers a : workersList) {
                if (workersMap.containsKey(a.getGrjcrq())) {
                    workersMap.put(a.getGrjcrq(),workersMap.get(a.getGrjcrq())+1)   ;
                } else {
                    workersMap.put(a.getGrjcrq(), 1);
                }
            }
            //map排序
            Map<String, Integer> resultMap = MapSort.sortMapByKey(workersMap);
            Map<String, Object> ObjectMap = new HashMap<String,Object>();
            ObjectMap.put("workersList",workersList);
            ObjectMap.put("workersMap",resultMap);
            result.setDataa(ObjectMap);
            return result;
        }catch(Exception e){
            Result result = new Result(ResultEnum.ERROR);
            result.setDataa(e.getMessage());
            return result;
        }
    }
    public Result getZp(ZpParma zpParma){
        List<String> list = new ArrayList<String>();
        List<Zp> zpList = new ArrayList<>();
        Map<String,List<String>> sqlMap = new HashMap<>();
        list.add("RYNM");
        list.add("ZP");
        list.add("SJSJ");
        list.add("NM");
        list.add("ZPLX");
        if(zpParma.getNm() ==null && zpParma.getNm()=="" && zpParma.getRynm()==null && zpParma.getRynm()=="" && zpParma.getRynmList().size() == 0 ){
            List<String> sql = new ArrayList<String>();
            sql.add("1");
            sqlMap.put("1",sql);
        }else if(zpParma.getNm() !=null && zpParma.getNm() !=""){
            List<String> sql = new ArrayList<String>();
            sql.add(zpParma.getNm());
            sqlMap.put("NM",sql);
        }else if(zpParma.getRynm()!=null && zpParma.getRynm()!=""){
            List<String> sql = new ArrayList<String>();
            sql.add(zpParma.getRynm());
            sqlMap.put("RYNM",sql);
        }else if(zpParma.getRynmList().size()>0){
            sqlMap.put("RYNM",zpParma.getRynmList());
        }
        try{
            Map<Integer, Map<String, String>> zpmap = DMIClient.getDMIclient().DMI_FilterParam("RYGL_RYXX_MTXX", list,sqlMap, null, null);
            for (Map.Entry<Integer, Map<String, String>> zpM : zpmap.entrySet()) {
                Zp zp = new Zp();
                for(Map.Entry<String,String> zpBen: zpM.getValue().entrySet()){
                    if("RYNM".equals(zpBen.getKey())){
                        zp.setRynm(zpBen.getValue());
                    }
                    if("ZP".equals(zpBen.getKey())){
                        zp.setZp(zpBen.getValue());
                    }
                    if("SJSJ".equals(zpBen.getKey())){
                        zp.setSjsj(zpBen.getValue());
                    }
                    if("NM".equals(zpBen.getKey())){
                        zp.setNm(zpBen.getValue());
                    }
                    if("ZPLX".equals(zpBen.getKey())){
                        zp.setZplx(zpBen.getValue());
                    }
                }
                zpList.add(zp);
            }
            Result result = new Result(ResultEnum.OK);
            result.setDataa(zpList);
            return result;
        }catch(Exception e){
            Result result = new Result(ResultEnum.ERROR);
            result.setDataa(e.getMessage());
            return result;
        }
    };

    @Override
    public Result deleteUser(List<String> nms){
        Map<String,List<String>> map = new HashMap<>();
        map.put("RYNM",nms);
        try{
            int userInt = DMIClient.getDMIclient().DMI_DeleteData("RYGL_RYXX_JBXX",map,null);
            if(userInt == 0){
                Result result = new Result(ResultEnum.OK);
                result.setDataa(userInt);
                return result;
            }else{
                Result result = new Result(ResultEnum.ERROR);
                return result;
            }

        }catch(Exception e){
            Result result = new Result(ResultEnum.ERROR);
            result.setDataa(e.getMessage());
            return result;
        }

    }
}
