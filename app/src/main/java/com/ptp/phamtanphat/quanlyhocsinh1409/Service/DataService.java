package com.ptp.phamtanphat.quanlyhocsinh1409.Service;

import com.ptp.phamtanphat.quanlyhocsinh1409.Model.Hocsinh;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by KhoaPhamPC on 7/11/2017.
 */

public interface DataService {
    @GET("getdata.php")
    Call<List<Hocsinh>> Getdata();

    @FormUrlEncoded
    @POST("insertdata.php")
    Call<String> Insertdata(@Field("tenhocsinh") String ten
                            ,@Field("namsinh") String namsinh
                            ,@Field("diachi") String diachi);
}
