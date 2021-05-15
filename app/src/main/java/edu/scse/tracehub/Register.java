package edu.scse.tracehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.JacksonMsgConvertor;
import com.ejlchina.okhttps.OkHttps;

import java.util.HashMap;

import edu.scse.tracehub.data.User;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Register extends AppCompatActivity {
    private EditText eT_username;
    private Button mBtn_delete;
    private EditText eT_password;
    private Button mBtn_yincang;
    private Button mBtn_zhuce2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
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
        TextView nameText = (TextView) findViewById(R.id.sign_name);
        TextView pwText = (TextView) findViewById(R.id.sign_pw);
        //设置ZHUCE按钮点击事件
        mBtn_zhuce2 = findViewById(R.id.btn_zhuce2);
        mBtn_zhuce2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到菜单页面
                boolean succ = false;
                User user = new User(nameText.getText().toString(),pwText.getText().toString());
                HashMap<String,String> ret =
                        http.async("/signup")
                                .bind(getLifecycle())
                                .bodyType(OkHttps.JSON)
                                .setBodyPara(user)
                                .post()
                                .getResult()
                                .getBody()
                                .toBean(new HashMap<String,String>().getClass());
                String msg = ret.get("msg");
                if(msg.equals("success"))
                {
                    msg = "注册成功";
                    succ =true;
                }
                else msg = "注册失败";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                if(true)
                {
                    Intent intent = new Intent(edu.scse.tracehub.Register.this, edu.scse.tracehub.LoginActivity.class);
                    startActivity(intent);
                }
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
                    mBtn_yincang.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_yincang));
                } else {
                    eT_password.setInputType(128);//设置为显示密码
                    mBtn_yincang.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_xianshi));
                }
                eT_password.setSelection(eT_password.getText().length());//设置光标的位置到末尾
            }
        });
    }
}
