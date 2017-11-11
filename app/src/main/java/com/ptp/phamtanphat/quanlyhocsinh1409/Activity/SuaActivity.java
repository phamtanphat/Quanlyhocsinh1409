package com.ptp.phamtanphat.quanlyhocsinh1409.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptp.phamtanphat.quanlyhocsinh1409.Model.Hocsinh;
import com.ptp.phamtanphat.quanlyhocsinh1409.R;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.APIService;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.DataService;

import retrofit2.Callback;
import retrofit2.Response;

public class SuaActivity extends AppCompatActivity {

    EditText edtten,edtnamsinh,edtdiachi;
    Button btncacel,btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        anhxa();
        final Intent intent = getIntent();
        final Hocsinh hocsinh = (Hocsinh) intent.getSerializableExtra("hocsinh");
        edtdiachi.setText(hocsinh.getDiaChi());
        edtnamsinh.setText(hocsinh.getNamSinh());
        edtten.setText(hocsinh.getTenHocSinh());
        btncacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtten.getText().toString();
                String diachi = edtdiachi.getText().toString();
                String namsinh = edtnamsinh.getText().toString();

                DataService dataService = APIService.getService();
                retrofit2.Call<String> callback = dataService.Update(ten,diachi,namsinh,hocsinh.getId());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                        Toast.makeText(SuaActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent1);
                    }

                    @Override
                    public void onFailure(retrofit2.Call<String> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void anhxa() {
        edtdiachi = findViewById(R.id.edittextsuadiachi);
        edtnamsinh = findViewById(R.id.edittextsuanamsinh);
        edtten = findViewById(R.id.edittextsuaten);
        btncacel = findViewById(R.id.buttoncancel);
        btnupdate = findViewById(R.id.buttonupdate);
    }
}
