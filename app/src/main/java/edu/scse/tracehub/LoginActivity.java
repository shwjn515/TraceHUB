package edu.scse.tracehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity {

    private Button mBtn_denglu;
    private EditText eT_username;
    private Button mBtn_delete;
    private EditText eT_password;
    private Button mBtn_yincang;
    private Button mBtn_zhuce;
    boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView username = (TextView) findViewById(R.id.usernameText);
        TextView password = (TextView) findViewById(R.id.pwText);
        //设置登录按钮点击事件
        mBtn_denglu = findViewById(R.id.btn_denglu);
        mBtn_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到菜单页面
                if(username.getText().equals("test")) login = true;

                Intent intent = new Intent(edu.scse.tracehub.LoginActivity.this, edu.scse.tracehub.MainActivity.class);
                startActivity(intent);
            }
        });
        //设置注册按钮点击事件
        mBtn_zhuce = findViewById(R.id.btn_zhuce);
        mBtn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到菜单页面
                Intent intent = new Intent(edu.scse.tracehub.LoginActivity.this, edu.scse.tracehub.Register.class);
                startActivity(intent);
            }
        });

        //清除用户名输入框内容
        mBtn_delete =findViewById(R.id.btn_delete);
        eT_username =findViewById(R.id.et_1);
        mBtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eT_username.setText("");
            }
        });

        //设置密码显示模式
        mBtn_yincang = findViewById(R.id.btn_yincang);
        eT_password = findViewById(R.id.et_2);
        mBtn_yincang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eT_password.getInputType() == 128) {
                    //如果现在是显示密码模式
                    eT_password.setInputType(129);//设置为隐藏密码
                    //mBtn_yincang.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_yincang));
                } else {
                    eT_password.setInputType(128);//设置为显示密码
                    //mBtn_yincang.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_xianshi));
                }
                eT_password.setSelection(eT_password.getText().length());//设置光标的位置到末尾
            }
        });
    }
}
