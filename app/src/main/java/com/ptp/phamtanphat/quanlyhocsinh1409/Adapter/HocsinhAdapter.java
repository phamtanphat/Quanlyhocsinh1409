package com.ptp.phamtanphat.quanlyhocsinh1409.Adapter;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ptp.phamtanphat.quanlyhocsinh1409.Activity.SuaActivity;
import com.ptp.phamtanphat.quanlyhocsinh1409.Model.Hocsinh;
import com.ptp.phamtanphat.quanlyhocsinh1409.R;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.APIService;
import com.ptp.phamtanphat.quanlyhocsinh1409.Service.DataService;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KhoaPhamPC on 7/11/2017.
 */

public class HocsinhAdapter extends BaseAdapter {
    Context context;
    ArrayList<Hocsinh> hocsinhArrayList;

    public void UpdateUI(ArrayList<Hocsinh> arrayList){
        hocsinhArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }
    public HocsinhAdapter(Context context, ArrayList<Hocsinh> hocsinhArrayList) {
        this.context = context;
        this.hocsinhArrayList = hocsinhArrayList;
    }

    @Override
    public int getCount() {
        return hocsinhArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return hocsinhArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    class ViewHolder{
        TextView txtten,txtdiachi,txtnamsinh;
        ImageView imgsua,imgxoa;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.dong_hocsinh,null);
            viewHolder = new ViewHolder();
            viewHolder.txtdiachi = view.findViewById(R.id.textviewdiachi);
            viewHolder.txtten = view.findViewById(R.id.textviewten);
            viewHolder.txtnamsinh = view.findViewById(R.id.textviewnamsinh);
            viewHolder.imgsua = view.findViewById(R.id.imageviewsua);
            viewHolder.imgxoa = view.findViewById(R.id.imageviewxoa);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Hocsinh hocsinh = (Hocsinh) getItem(i);
        viewHolder.txtdiachi.setText(hocsinh.getDiaChi());
        viewHolder.txtnamsinh.setText(hocsinh.getNamSinh());
        viewHolder.txtten.setText(hocsinh.getTenHocSinh());
        viewHolder.imgsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SuaActivity.class);
                intent.putExtra("hocsinh",hocsinh);
                context.startActivity(intent);
            }
        });
        viewHolder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService = APIService.getService();
                retrofit2.Call<String> callback = dataService.Deletedata(hocsinhArrayList.get(i).getId());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                        Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                        hocsinhArrayList.remove(i);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<String> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }
}
