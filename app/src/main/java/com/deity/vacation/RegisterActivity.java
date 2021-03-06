package com.deity.vacation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.deity.vacation.base.ParentWithNaviActivity;
import com.deity.vacation.entity.BaseModel;
import com.deity.vacation.entity.UserModel;
import com.deity.vacation.event.FinishEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**注册界面
 * @author :smile
 * @project:RegisterActivity
 * @date :2016-01-15-18:23
 */
public class RegisterActivity extends ParentWithNaviActivity {

    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.btn_register)
    Button btn_register;

    @Bind(R.id.et_password_again)
    EditText et_password_again;

    @Override
    protected String title() {
        return "注册";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initNaviView();
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClick(View view){
        UserModel.getInstance().register(et_username.getText().toString(), et_password.getText().toString(),et_password_again.getText().toString(),new LogInListener() {
            @Override
            public void done(Object o, BmobException e) {
                if(e==null){
                    EventBus.getDefault().post(new FinishEvent());
                    startActivity(MainActivity.class, null, true);
                }else{
                    if(e.getErrorCode()== BaseModel.CODE_NOT_EQUAL){
                        et_password_again.setText("");
                    }
                    toast(e.getMessage()+"("+e.getErrorCode()+")");
                }
            }
        });
    }

}
