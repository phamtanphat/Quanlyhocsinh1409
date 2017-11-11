package com.ptp.phamtanphat.quanlyhocsinh1409.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ptp.phamtanphat.quanlyhocsinh1409.R;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.APIRetrofitClient;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.APIService;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.DataService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ThemHocSinh extends AppCompatActivity {

    EditText edtten,edtnamsinh,edtdiachi;
    Button btnhuy,btnthem;
    String ketqua = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themhochinh);
        anhxa();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ten = edtten.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                String diachi = edtdiachi.getText().toString();

                DataService dataService = APIService.getService();
                Call<String> callback = dataService.Insertdata(ten,namsinh,diachi);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua = response.body();
                        if (ketqua.equals("successfull")){
                            Toast.makeText(ThemHocSinh.this, ketqua, Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ThemHocSinh.this, "Loi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }

        });

    }


    private void anhxa() {
        edtten = findViewById(R.id.edittexttensinhvien);
        edtnamsinh = findViewById(R.id.edittextnamsinh);
        edtdiachi = findViewById(R.id.edittextdiachi);
        btnhuy = findViewById(R.id.buttonhuy);
        btnthem = findViewById(R.id.buttonthem);
    }
}
