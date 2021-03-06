package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoViewManager;


public class MainActivity extends AppCompatActivity {


    private HomeKeyBroadCastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 注册广播，监听用户点击了Home键
        registerHomeKeyReceiver();

        //单个Activity页面，单个播放器，点击到下一集播放
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingleActivity.class);
                startActivity(intent);
            }
        });

        //单个Fragment页面，单个播放器，点击到下一集播放
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingleFragmentActivity.class);
                startActivity(intent);
            }
        });
        //点击事件
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    // 注册广播，监听用户点击了Home键
    private void registerHomeKeyReceiver() {
        mReceiver = new HomeKeyBroadCastReceiver();
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    // 广播接收器
    public class HomeKeyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //这里调用播放器管理类，告诉播放器，app处于后台
            XVideoViewManager.getInstance().onBackground();

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);//整个app退出的时候，取消home广播
        XVideoViewManager.getInstance().onExitApp();//调用退出整个App的代码
    }


}
