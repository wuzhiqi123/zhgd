package com.zhgd.controller.controllerUtile;

public class Result {
    private String code;

    private String massage;

    private Object dataa;

    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public  Result(ResultEnum resultEnum){
        setCode(resultEnum.getCode()) ;
        setMassage(resultEnum.getMassag());
    }

    public  Result(ResultEnum resultEnum,Object data){
        setCode(resultEnum.getCode()) ;
        setMassage(resultEnum.getMassag());
        setDataa(data);
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Object getData() {
        return dataa;
    }

    public void setDataa(Object data) {
        this.dataa = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", massage='" + massage + '\'' +
                ", dataa=" + dataa +
                '}';
    }

    public Result(){

    }
}
