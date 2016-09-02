package com.zlw.dzdp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.zlw.dzdp.R;
import com.zlw.dzdp.utils.RandomUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by zlw on 2016/8/23 0023.
 */
public class LoginActivity extends Activity implements PlatformActionListener {


    @ViewInject(R.id.login_auth_code_tv)
    private TextView tv_auto_code;


    @ViewInject(R.id.login_bt)
    private Button bt_login;

    @ViewInject(R.id.login_qq)
    private Button bt_login_qq;

    @ViewInject(R.id.login_wechat)
    private Button bt_login_wechat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        ShareSDK.initSDK(this);

        tv_auto_code.setText(RandomUtils.getRandom(4));


    }

    @Override
    protected void onDestroy() {
        ShareSDK.stopSDK();
        super.onDestroy();
    }

    @OnClick({R.id.login_bt, R.id.login_qq, R.id.login_wechat})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_bt:


                break;
            case R.id.login_qq:
                Log.i("zlw", "login_qq:");

                loginByQQ();
                break;
            case R.id.login_wechat:
                break;

            default:
                break;
        }
    }

    private void loginByQQ() {
        //1.得到qq的登录平台
        Platform platform = ShareSDK.getPlatform(this, QQ.NAME);
        //2.增加监听事件
        platform.setPlatformActionListener(this);
        //3.是否正常登陆

        if (platform.isValid()) {
            String username = platform.getDb().getUserName();
            //登陆成功

            Log.i("zlw", "登陆成功:" + username);
        }

    }


    //三方登陆回调接口
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

        Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
        String username = platform.getDb().getUserName();

        Log.i("zlw", "username:" + username);


    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(LoginActivity.this, "授权已取消", Toast.LENGTH_SHORT).show();
    }
}
