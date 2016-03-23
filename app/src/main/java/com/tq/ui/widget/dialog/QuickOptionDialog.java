package com.tq.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.tq.R;
import com.tq.util.UIHelper;

/**
 * Created by boobooMX on 2016/3/23 0023.10.18
 * Created 邮箱 ：boobooMX@163.com
 */
public class QuickOptionDialog extends Dialog implements View.OnClickListener {
    private ImageView mClose;
    public interface OnQuickOptionformClick{
        void onQuickOptionClick(int id);
    }
    private OnQuickOptionformClick mListener;
    public QuickOptionDialog(Context context) {
        this(context, R.style.quick_option_dialog);
    }
    public QuickOptionDialog(Context context, int themeResId) {
        super(context, themeResId);
        View contentView=getLayoutInflater().inflate(R.layout.dialog_quick_option,null);
        contentView.findViewById(R.id.tv_take_picture).setOnClickListener(this);
        contentView.findViewById(R.id.tv_select_picture_from_mobile).setOnClickListener(this);
        mClose= (ImageView) contentView.findViewById(R.id.iv_close);

        Animation operatingAnim=
                AnimationUtils.loadAnimation(getContext(),R.anim.quick_option_close);
        LinearInterpolator lin=new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        mClose.startAnimation(operatingAnim);
        mClose.setOnClickListener(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                QuickOptionDialog.this.dismiss();
                return true;
            }
        });
        super.setContentView(contentView);
    }
    public QuickOptionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m=getWindow().getWindowManager();
        Display d=m.getDefaultDisplay();
        WindowManager.LayoutParams p=getWindow().getAttributes();
        p.width=d.getWidth();
        getWindow().setAttributes(p);

    }

    public void setOnQuickOptionformClickListener(OnQuickOptionformClick mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
            switch (id){
                case R.id.iv_close:
                    dismiss();
                    break;
                case R.id.tv_take_picture:
                    //TODO  进入到拍照的界面

                    break;

                case R.id.tv_select_picture_from_mobile:
                    //TODO 进入到选择图片的界面
                    break;
            }
        if(mListener!=null){
            mListener.onQuickOptionClick(id);
        }
        dismiss();
    }


}
