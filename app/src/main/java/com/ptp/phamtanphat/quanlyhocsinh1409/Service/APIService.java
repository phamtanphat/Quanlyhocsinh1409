package com.ptp.phamtanphat.quanlyhocsinh1409.Service;

/**
 * Created by KhoaPhamPC on 11/11/2017.
 */

public class APIService {
    public APIService(){
    }
    public static final String Base_URL ="http://192.168.1.181/quanlyhocsinh1409/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(Base_URL).create(DataService.class);
    }
}
