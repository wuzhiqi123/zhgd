package com.zhgd.controller.controllerUtile;

public enum ResultEnum {
    OK ("OK","成功"),
    ERROR("ERROR","失败");
    private String code;

    private String massag;

     private ResultEnum(String code, String massag){
          this.code  = code;
          this.massag = massag;

    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMassag() {
        return massag;
    }

    public void setMassag(String massag) {
        this.massag = massag;
    }
}
