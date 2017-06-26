package com.example.aman.a11;

/**
 * Created by Aman on 08/06/2017.
 */

public class Mantra {
    String a1,a2,a3,Url;
    public Mantra(){}
    public Mantra(String a1,String a2, String a3){
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        // this.Url=Url;
    }
    public String getA1(){
        return a1;
    }
    public void setA1(String a1){
        this.a1=a1;
    }
    public String getA2(){
        return a2;
    }
    public void setA2(String a2){
        this.a2=a2;
    }
    public String getA3(){
        return a3;
    }
    public void setA3(String a3){
        this.a3=a3;
    }
    /*public String getUrl(){
        return Url;
    }
    public void setUrl(String Url){
        this.Url=Url;
    }*/
}
