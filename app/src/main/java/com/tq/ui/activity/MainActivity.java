package com.tq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tq.R;
import com.tq.app.AppManager;
import com.tq.ui.widget.DoubleClickExitHelper;
import com.tq.ui.widget.MyFragmentTabHost;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Bind(R.id.quick_option_iv)
    ImageView quickOptionIv;
    @Bind(R.id.tabhost)
    MyFragmentTabHost mTabHost;
    @Bind(R.id.realtabcontent)
    FrameLayout realtabcontent;

    private DoubleClickExitHelper mDoubleClickExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        AppManager.getAppManager().addActivity(this);

        handleIntent(getIntent());
    }

    private void initView() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    /**
     * 处理传递过来的Internet
     * @param intent
     */
    private void handleIntent(Intent intent) {
        if(intent==null){
            return;
        }
        String action=intent.getAction();
        if(action!=null&&action.equals(Intent.ACTION_VIEW)){
            //TODO
            UIHelper.showUrlRedirect(this,intent.getDataString());

        }else if(intent.getBooleanExtra("NOTICE",false)){
            //TODO
            notificationBarClick(intent);
        }
    }

    /**
     * 从通知栏点击的时候相应
     * @param fromWhich
     */
    private void notificationBarClick(Intent fromWhich) {
        if(fromWhich!=null){
            boolean fromNoticeBar=fromWhich.getBooleanExtra("NOTICE",false);
            if(fromNoticeBar){
                Intent toMyInfor=new Intent(this,SimpleBackActivity.class);
                toMyInfor.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE,SimpleBackActivity.MY_MES.getValue());
                startActivity(toMyInfor);

            }
        }

    }
}
