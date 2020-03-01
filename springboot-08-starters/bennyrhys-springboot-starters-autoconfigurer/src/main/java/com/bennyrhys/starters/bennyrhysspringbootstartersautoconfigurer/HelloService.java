package com.bennyrhys.starters.bennyrhysspringbootstartersautoconfigurer;

public class HelloService {

    HelloProperties helloProperties;
    //service用到properties方法

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
//原本hello
    public String helloBennyrhys(String name){
        return  helloProperties.getPrefix()+name+"-"+helloProperties.getSuffix();//打招呼信息为可配置的
    }


}
