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

import com.ptp.phamtanphat.quanlyhocsinh1409.R;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.DataService;

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

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.181/quanlyhocsinh1409/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                // Nhan du lieu khi duoc get ve
                DataService dataService = retrofit.create(DataService.class);
                Call<String> call = dataService.Insertdata(ten,namsinh,diachi);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, final Response<String> response) {
                        ketqua = response.body().toString();
                        System.out.print(ketqua);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("BBB",t.getMessage().toString());
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
