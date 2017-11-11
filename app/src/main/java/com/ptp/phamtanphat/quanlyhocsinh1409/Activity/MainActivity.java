package com.ptp.phamtanphat.quanlyhocsinh1409.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ptp.phamtanphat.quanlyhocsinh1409.Adapter.HocsinhAdapter;
import com.ptp.phamtanphat.quanlyhocsinh1409.Model.Hocsinh;
import com.ptp.phamtanphat.quanlyhocsinh1409.R;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.DataService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView lvhocsinh;
    HocsinhAdapter hocsinhAdapter;
    ArrayList<Hocsinh> manghocsinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvhocsinh = findViewById(R.id.listview);
        manghocsinh = new ArrayList<>();
        hocsinhAdapter = new HocsinhAdapter(MainActivity.this,manghocsinh);
        lvhocsinh.setAdapter(hocsinhAdapter);
        Getdata();
//        Log.d("BBB","Gia tri oncreate" + manghocsinh.size());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuthem){
            Intent intent = new Intent(MainActivity.this,ThemHocSinh.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void Getdata() {
        manghocsinh.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.181/quanlyhocsinh1409/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataService dataService = retrofit.create(DataService.class);
        Call<List<Hocsinh>> listCallhocsinh = dataService.Getdata();
        listCallhocsinh.enqueue(new Callback<List<Hocsinh>>() {
            @Override
            public void onResponse(Call<List<Hocsinh>> call, Response<List<Hocsinh>> response) {
                if (response.isSuccessful()){
                    manghocsinh.clear();
                    Log.d("BBB","Gia tri " + manghocsinh.size());
                    manghocsinh = (ArrayList<Hocsinh>) response.body();
                    hocsinhAdapter.UpdateUI(manghocsinh);
                }
            }

            @Override
            public void onFailure(Call<List<Hocsinh>> call, Throwable t) {

            }
        });
    }
}
