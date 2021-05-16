package edu.scse.tracehub.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import edu.scse.tracehub.MainActivity;
import edu.scse.tracehub.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.navigation_notifications, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView nameText = (TextView) getActivity().findViewById(R.id.name);
        String name = getActivity().getIntent().getStringExtra("name");
        nameText.setText(name);
        Button feedback = (Button) getActivity().findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给 反馈按钮  添加点击响应事件
                Intent intent =new Intent(getActivity(),FeedbackActivity.class);
                startActivity(intent);
            }
        });
        Button setting = (Button) getActivity().findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给 反馈按钮  添加点击响应事件
                Intent intent =new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        Button questions = (Button) getActivity().findViewById(R.id.questions);
        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),QuestionsActivity.class);
                startActivity(intent);
            }
        });
    }
}