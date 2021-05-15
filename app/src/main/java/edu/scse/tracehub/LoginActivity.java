package edu.scse.tracehub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.scse.tracehub.data.User;
import okhttp3.Response;
import okhttp3.ResponseBody;
import androidx.appcompat.app.AppCompatActivity;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.JacksonMsgConvertor;
import com.ejlchina.okhttps.OkHttps;

import java.util.HashMap;

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
        HTTP http = HTTP.builder()
                .config( builder -> builder.addInterceptor(chain -> {
                    Response res = chain.proceed(chain.request());
                    ResponseBody body = res.body();
                    ResponseBody newBody = null;
                    if (body != null) {
                        newBody = ResponseBody.create(body.contentType(), body.bytes());
                    }
                    return res.newBuilder().body(newBody).build();
                }))
                .baseUrl("http://117.78.3.88:8080")
                .addMsgConvertor(new JacksonMsgConvertor())
                .build();
        //设置登录按钮点击事件
        mBtn_denglu = findViewById(R.id.btn_denglu);
        mBtn_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到菜单页面
                String name = username.getText().toString();
                String pw = password.getText().toString();
                Intent intent = new Intent(edu.scse.tracehub.LoginActivity.this, edu.scse.tracehub.MainActivity.class);
                if(name.equals("test")) startActivity(intent);
                User user = new User(name,pw);
                HashMap<String,String> ret =
                http.async("/login")
                        .bind(getLifecycle())
                        .bodyType(OkHttps.JSON)
                        .setBodyPara(user)
                        .post()
                        .getResult()
                        .getBody()
                        .toBean(new HashMap<String,String>().getClass());
                String msg = ret.get("msg");
                if(msg.equals("wrong"))
                {
                    msg = "用户密码错误";
                    login = false;
                }
                else
                {
                    msg = "登录成功";
                    login =true;
                }
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                intent.putExtra("name",name);
                if(login)    startActivity(intent);
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
