package edu.scse.tracehub.ui.notifications;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import edu.scse.tracehub.R;

import static android.os.Build.VERSION_CODES.R;

public class SettingActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.scse.tracehub.R.layout.appconfig);
    }
}
