package com.peterservice.pstest.common;

/**
 * Created by Andrey.Shilov on 17.11.2015.
 */
public class Subscriber extends Object {
    private String msisdn;
    private String ratePlan;

    public Subscriber(){}
    public Subscriber(String msisdn, String ratePlan) {
        this.msisdn = msisdn;
        this.ratePlan = ratePlan;
    }

    public String getMsisdn(){
        return this.msisdn;
    }

    public void setMsisdn(String msisdn){
        this.msisdn = msisdn;
    }

    public String getRatePlan(){
        return this.ratePlan;
    }

    public void setRatePlan(String ratePlan){
        this.ratePlan = ratePlan;
    }

    @Override
    public boolean equals(Object obj) {
        return this.msisdn.equals(((Subscriber)obj).msisdn);
    }
}
