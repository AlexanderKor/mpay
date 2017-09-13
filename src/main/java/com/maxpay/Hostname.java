package com.maxpay;

/**
 * Created by Olexander.Korniev on 04.09.2017.
 */

public class Hostname {
    public static final String MAXPAY_HOST_NAME = "my-sandbox.maxpay.com/";


    public static String maxpayLoginPage(){
        String internal = "https://" + MAXPAY_HOST_NAME;
        return internal;
    }

}
