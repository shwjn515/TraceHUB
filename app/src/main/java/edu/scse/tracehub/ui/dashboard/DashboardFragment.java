package edu.scse.tracehub.ui.dashboard;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//
//import edu.scse.tracehub.R;
//
//public class DashboardFragment extends Fragment {
//
//    private DashboardViewModel dashboardViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);
//        View root = inflater.inflate(R.layout.navigation_dashboard, container, false);
//        /*final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });*/
//        return root;
//    }
//}
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final Cat[] cats = {new Cat("蠢猫",R.drawable.cat),
            new Cat("傻猫",R.drawable.cat2)};
    //列表需要的数据表
    private List<Cat> catList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < 100; i++) catList.add(cats[i % 2]);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //格式布局
        int span = 2;
        GridLayoutManager manager = new GridLayoutManager(this,span);
        recyclerView.setLayoutManager(manager);
        CatAdapter adapter = new CatAdapter(this,catList);
        adapter.setOnItemClickListener(new CatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"这是条"+catList.get(position).getType(),Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}