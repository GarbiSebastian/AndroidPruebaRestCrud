package com.sebito.restcrud.remote;

public class ApiUtils {

    private ApiUtils(){}

    //private static final String ip = "192.168.116.32";
    private static final String ip = "192.168.0.5";
    private static final String API_URL = "http://"+ip+"/RestSimple/public/index.php/";

    public static ContactoService getContactoService(){
        return RetrofitClient.getClient(API_URL).create(ContactoService.class);
    }
}
