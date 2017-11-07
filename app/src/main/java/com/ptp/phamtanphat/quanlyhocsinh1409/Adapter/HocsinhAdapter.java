package com.ptp.phamtanphat.quanlyhocsinh1409.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ptp.phamtanphat.quanlyhocsinh1409.Model.Hocsinh;
import com.ptp.phamtanphat.quanlyhocsinh1409.R;

import java.util.ArrayList;

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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.dong_hocsinh,null);
            viewHolder = new ViewHolder();
            viewHolder.txtdiachi = view.findViewById(R.id.textviewdiachi);
            viewHolder.txtten = view.findViewById(R.id.textviewten);
            viewHolder.txtnamsinh = view.findViewById(R.id.textviewnamsinh);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Hocsinh hocsinh = (Hocsinh) getItem(i);
        viewHolder.txtdiachi.setText(hocsinh.getDiaChi());
        viewHolder.txtnamsinh.setText(hocsinh.getNamSinh());
        viewHolder.txtten.setText(hocsinh.getTenHocSinh());
        return view;
    }
}
