package com.sebito.restcrud.remote;

import java.nio.file.attribute.UserPrincipalLookupService;

public class ApiUtils {

    private ApiUtils(){}

    private static final String API_URL = "http://192.168.116.32/RestSimple/public/index.php/contacto/";

    public static ContactoService getContactoService(){
        return RetrofitClient.getClient(API_URL).create(ContactoService.class);
    }
}
