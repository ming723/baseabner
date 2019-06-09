package com.abner.ming.base.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.abner.ming.base.R;


/**
 * Created by AbnerMing on 2018/3/7.
 * 菊花加载框
 */

public class DialogLoading extends AlertDialog {
    private RelativeLayout alertLayout;
    protected DialogLoading(Context context){
        super(context);

    }
    protected DialogLoading(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        alertLayout=(RelativeLayout)findViewById(R.id.alert_layout);
        ImageView mImage=(ImageView)findViewById(R.id.iv_loading);
        //播放帧动画
        Animatable animatable = (Animatable) mImage.getDrawable();
        animatable.start();
        setCanceledOnTouchOutside(false);
    }

    /**
     * 设置背景色
     * */
    public void setBg(int color){
        alertLayout.setBackgroundColor(color);
    }

    /**
     *设置点击空白区域是否隐藏
     * true:可点击
     * false  不可点击
     * */
    public void setCanceled(boolean isShow){
        setCanceledOnTouchOutside(isShow);
    }

    public static class Builder {
        private DialogLoading loading;
        private Context context;
        public Builder(Context context) {
            this.context=context;
            loading= new DialogLoading(context, R.style.Dialog_Transparent);
        }
        /**
         * 设置背景半透明
         * */
        public Builder alertBg(){
            loading=null;
            loading=new DialogLoading(context);
            return this;
        }
        public DialogLoading create(){
            return loading;
        }
        /**
         * 设置背景色
         * */
        public Builder setBg(int color){
            loading.setBg(color);
            return this;
        }
        /**
         *设置点击空白区域是否隐藏
         * true:可点击
         * false  不可点击
         * */
        public Builder canceled(){
            loading.setCanceled(true);
            return this;
        }
        /**
         * loading显示
         * */
        public void show(){
            loading.getWindow().setBackgroundDrawableResource(R.color.transparent);
            loading.show();
        }
        /**
         * loading隐藏
         * */
        public void hint(){
            loading.dismiss();
        }

    }
}
