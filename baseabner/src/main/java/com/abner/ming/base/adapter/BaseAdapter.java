package com.abner.ming.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:AbnerMing
 * date:2019/6/8
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder>{
    private Context context;
    private List<T> list = new ArrayList<>();

    public BaseAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, getLayoutId(), null);
        BaseAdapter.BaseViewHolder baseViewHolder = new BaseAdapter.BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder baseViewHolder, int i) {
        bindViewData(baseViewHolder, list.get(i));
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        private SparseArray<View> sparseArray = new SparseArray<>();

        //用于获取控件的方法
        public View get(int id) {
            View view = sparseArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                sparseArray.put(id, view);
            }
            return view;
        }

        //给TextView 赋值
        public void setText(int id, String content) {
            TextView textView = (TextView) get(id);
            textView.setText(content);
        }

        public void setPic(int id,String url){
            //这里写加载图片的逻辑
            //Glide  Fresco  pissca
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //传递数据
    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    //子类向父类传递的layout
    public abstract int getLayoutId();

    //子类初始化数据
    protected abstract void bindViewData(BaseAdapter.BaseViewHolder baseViewHolder, T t);
}
