package com.zhgd.impService;

import com.zhgd.controller.controllerUtile.Result;
import com.zhgd.controller.controllerUtile.ResultEnum;
import com.zhgd.controller.controllerUtile.parma.BzPaarma;
import com.zhgd.controller.controllerUtile.parma.DwParma;
import com.zhgd.pojo.Dictionary;
import com.zhgd.pojo.Jzgs;
import com.zhgd.pojo.TreeResult;
import com.zhgd.service.DictionaryService;
import com.zhgd.utile.DMIClient;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Override
   public  Result getWhcd() {
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("WHCDNM");
        list.add("XH");
        list.add("WHCDMC");
      try{
           Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("SJGX_S_TY_WHCD",list,null,"","");
           for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
               Dictionary dictionary = new Dictionary();
               for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                   if("WHCDNM".equals(resource.getKey())){
                       dictionary.setNm(resource.getValue());
                   }
                   if("XH".equals(resource.getKey())){
                       dictionary.setXh(resource.getValue());
                   }
                   if("WHCDMC".equals(resource.getKey())){
                       dictionary.setMc(resource.getValue());
                   }
               }
               dictionaries.add(dictionary);
           }
          result.setDataa(dictionaries);
           System.out.println(map);
      }catch(Exception e){
          Result resultError = new Result(ResultEnum.ERROR);
          return resultError;
      }
        return result;
    }
    @Override
    public Result getXb(){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("XBNM");
        list.add("XH");
        list.add("XB");
        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("SG_RESDATA_DICT_XB",list,null,"","");
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("XBNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("XB".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    }
    @Override
    public Result getGrssdw(DwParma dwParma){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("DWNM");
        list.add("XH");
        list.add("DWMC");
        String stringQY = null;
        Map<String,String> stringMap = new HashMap<>() ;
        if(!StringUtils.isEmpty(dwParma.getDwmc())){
            stringQY = "DWMC like '%"+dwParma.getDwmc()+"%'";
        }
        try{
            stringMap.put("SQL",stringQY);
            JSONArray jsonObject = JSONArray.fromObject(stringMap);
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("JBXX_S_LW_GCDW",list,null,"",jsonObject.toString());
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("DWNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("DWMC".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    }
    @Override
   public  Result getGrssbz(BzPaarma bzPaarma){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("BZNM");
        list.add("XH");
        list.add("BZMC");
        Map<String,List<String>> sqlMap = new HashMap<>();
        if(StringUtils.isEmpty(bzPaarma.getDwmc())&&StringUtils.isEmpty(bzPaarma.getDwnm())&& StringUtils.isEmpty(bzPaarma.getBznm()) && StringUtils.isEmpty(bzPaarma.getBzmc())){
            List<String> sql = new ArrayList<String>();
            sql.add("1");
            sqlMap.put("1",sql);
        }else if(!StringUtils.isEmpty(bzPaarma.getDwnm())){
            List<String> sql = new ArrayList<String>();
            sql.add(bzPaarma.getDwnm());
            sqlMap.put("SSGCDWNM",sql);
        }else if(!StringUtils.isEmpty(bzPaarma.getBzmc())){
            List<String> sql = new ArrayList<String>();
            sql.add(bzPaarma.getBzmc());
            sqlMap.put("BZMC",sql);
        }else if(!StringUtils.isEmpty(bzPaarma.getBznm())){
            List<String> sql = new ArrayList<String>();
            sql.add(bzPaarma.getBzmc());
            sqlMap.put("BZNM",sql);
        }
        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("JBXX_S_LW_GCBZ",list,sqlMap,null,null);
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("BZNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("BZMC".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    }


    @Override
    public  Result getGgrgz(){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("GZLBNM");
        list.add("XH");
        list.add("GZLBMC");
        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("JBXX_S_LW_GZLB",list,null,"","");
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("GZLBNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("GZLBMC".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    }

    @Override
    public  Result getMz(){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("MZNM");
        list.add("XH");
        list.add("MZMC");
        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("SJGX_S_TY_MZ",list,null,"","");
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("MZNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("MZMC".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    }

    @Override
    public  Result getZzmm(){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("ZZMMNM");
        list.add("XH");
        list.add("ZZMMMC");
        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("SJGX_S_TY_ZZMM",list,null,"","");
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("ZZMMNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("ZZMMMC".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    }


    public Result getGd(){
        Result result = new Result(ResultEnum.OK);
        List<Jzgs>  jzgsList = new ArrayList<Jzgs>();
        List<String> list = new ArrayList<String>();
        list.add("NM");
        list.add("XH");
        list.add("GSMC");
        list.add("SZD");
        list.add("JD");
        list.add("WD");
        list.add("GC");
        list.add("GSJJ");
        list.add("LXR");
        list.add("LXDH");
        list.add("BZ");
        list.add("SJSJ");

        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("ZHGD_JZGS_JBXX",list,null,"","");
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Jzgs jzgs = new Jzgs();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("NM".equals(resource.getKey())){
                        jzgs.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        jzgs.setXh(resource.getValue());
                    }
                    if("GSMC".equals(resource.getKey())){
                        jzgs.setGsmc(resource.getValue());
                    }
                    if("SZD".equals(resource.getKey())){
                        jzgs.setSzd(resource.getValue());
                    }
                    if("JD".equals(resource.getKey())){
                        jzgs.setJd(resource.getValue());
                    }
                    if("WD".equals(resource.getKey())){
                        jzgs.setWd(resource.getValue());
                    }
                    if("GC".equals(resource.getKey())){
                        jzgs.setGc(resource.getValue());
                    }
                    if("GSJJ".equals(resource.getKey())){
                        jzgs.setGsjj(resource.getValue());
                    }
                    if("LXR".equals(resource.getKey())){
                        jzgs.setLxr(resource.getValue());
                    }
                    if("LXDH".equals(resource.getKey())){
                        jzgs.setLxdh(resource.getValue());
                    }
                    if("BZ".equals(resource.getKey())){
                        jzgs.setBz(resource.getValue());
                    }
                    if("SJSJ".equals(resource.getKey())){
                        jzgs.setSjsj(resource.getValue());
                    }
                }
                jzgsList.add(jzgs);
            }
            result.setDataa(jzgsList);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            resultError.setDataa(e.getMessage());
            return resultError;
        }
        return result;
    }

    public Result getPxlx(){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("PXLXNM");
        list.add("XH");
        list.add("PXLXMC");
        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("JBXX_S_LW_PXLX",list,null,"","");
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("PXLXNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("PXLXMC".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    };


    public Result getJllx(){
        Result result = new Result(ResultEnum.OK);
        List<Dictionary> dictionaries = new ArrayList<Dictionary>();
        List<String> list = new ArrayList<String>();
        list.add("JLLXNM");
        list.add("XH");
        list.add("JLLXMC");
        try{
            Map<Integer, Map<String,String>> map = DMIClient.getDMIclient().DMI_FilterParam("JBXX_S_LW_JLLX",list,null,"","");
            for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
                Dictionary dictionary = new Dictionary();
                for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
                    if("JLLXNM".equals(resource.getKey())){
                        dictionary.setNm(resource.getValue());
                    }
                    if("XH".equals(resource.getKey())){
                        dictionary.setXh(resource.getValue());
                    }
                    if("JLLXMC".equals(resource.getKey())){
                        dictionary.setMc(resource.getValue());
                    }
                }
                dictionaries.add(dictionary);
            }
            result.setDataa(dictionaries);
        }catch(Exception e){
            Result resultError = new Result(ResultEnum.ERROR);
            return resultError;
        }
        return result;
    };

}
