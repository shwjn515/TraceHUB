package edu.scse.tracehub.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import edu.scse.tracehub.R;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class list extends Fragment {
    private Button mBtnInputFragment3, mBtnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu, container, false);
        mBtnBack = view.findViewById(R.id.back);
         mBtnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(getView()).popBackStack(); //返回上一个碎片
        }
    });
        return view;
     }
    }
