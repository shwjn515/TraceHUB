package edu.scse.tracehub.ui.notifications;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import edu.scse.tracehub.R;

import static com.amap.api.maps.model.BitmapDescriptorFactory.getContext;

public class FeedbackActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        Button finish=(Button)findViewById(R.id.back);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button commit=(Button)findViewById(R.id.commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText feedback=findViewById(R.id.feedback);
                String msg=feedback.getText().toString();
                Toast.makeText(getContext(), "感谢您的反馈", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
