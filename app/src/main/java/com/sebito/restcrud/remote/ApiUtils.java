package com.sebito.restcrud.remote;

public class ApiUtils {

    private ApiUtils(){}

    private static final String API_URL = "http://192.168.116.32/RestSimple/public/index.php/";

    public static ContactoService getContactoService(){
        return RetrofitClient.getClient(API_URL).create(ContactoService.class);
    }
}
