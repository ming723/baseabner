package com.abner.ming.base.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.abner.ming.base.R;
import com.abner.ming.base.utils.Logger;


/**
 * Created by AbnerMing on 2018/3/7.
 * 弹出的选择框
 */

public class AlertUtils extends AlertDialog {
    private TextView mAlertConfim,mAlertTitle,mAlertDesc;
    protected TextView mAlertCancle;
    private View rootView;

    protected AlertUtils(Context context) {
        super(context);
        initView(context);
    }

    protected AlertUtils(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    private void initView(Context context){
        rootView=View.inflate(context, R.layout.dialog_utils,null);
        mAlertCancle=(TextView)rootView.findViewById(R.id.alert_cancle);
        mAlertConfim=(TextView)rootView.findViewById(R.id.alert_confim);
        mAlertTitle=(TextView)rootView.findViewById(R.id.alert_title);
        mAlertDesc=(TextView)rootView.findViewById(R.id.alert_desc);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rootView);
    }

    /**
     * 确定
     * */
    public void setOnConfimClickListener(final Builder.OnConfimListener listener){

        mAlertConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.confim();
            }
        });
    }

    /**
     * 设置Title
     * */
    public void title(String title){
        if(!TextUtils.isEmpty(title)){
            mAlertTitle.setText(title);
            Logger.i("AlertUtils",title);
        }
    }
    /**
     * 设置描述
     * */
    public void setDesc(String desc){
        if(!TextUtils.isEmpty(desc)){
            mAlertDesc.setText(desc);
        }
    }
    /**
     * 设置cancle
     * */
    public void setCancle(String cancle){
        if(!TextUtils.isEmpty(cancle)){
            mAlertCancle.setText(cancle);
        }
    }
    /**
     * 设置confim
     * */
    public void setConfim(String confim){
        if(!TextUtils.isEmpty(confim)){
            mAlertConfim.setText(confim);
        }
    }
    /**
     *设置点击空白区域是否隐藏
     * */
    public void setCanceled(boolean isShow){
        setCanceledOnTouchOutside(isShow);
    }

    public static class Builder {
        private AlertUtils alert;
        private Context context;
        public Builder(Context context){
            this.context=context;
            alert=new AlertUtils(context,R.style.Dialog_Transparent);
            click();
        }

        /**
         * 设置背景半透明
         * */
        public Builder AlertBg(){
            alert=null;
            alert=new AlertUtils(context);
            click();
            return this;
        }

        private void click(){
            alert.mAlertCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hint();
                }
            });
        }
        /**
         *设置点击空白区域是否隐藏
         * */
        public Builder canceled(){
            alert.setCanceled(false);
            return this;
        }
        public AlertUtils create(){
            return alert;
        }

        /**
         * 设置确定
         * */
        public Builder confim(String confim){
            alert.setConfim(confim);
            return this;
        }

        /**
         * 设置取消
         * */
        public Builder cancle(String cancle){
            alert.setCancle(cancle);
            return this;
        }

        /**
         * 设置title
         * */
        public Builder title(String title){
            alert.title(title);
            return this;
        }
        /**
         * 设置描述
         * */
        public Builder desc(String desc){
            alert.setDesc(desc);
            return this;
        }
        /**
         * 确定
         * */
        public Builder setConfimListenr(OnConfimListener listenr){
            alert.setOnConfimClickListener(listenr);
            return this;
        }

        /**
         * loading显示
         * */
        public void show(){
            alert.getWindow().setBackgroundDrawableResource(R.color.transparent);
            alert.show();
        }
        /**
         * loading隐藏
         * */
        public void hint(){
            alert.dismiss();
        }

        public interface OnConfimListener{
            void confim();
        }
    }
}
