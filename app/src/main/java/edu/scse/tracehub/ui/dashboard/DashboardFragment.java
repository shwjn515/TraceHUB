package edu.scse.tracehub.ui.dashboard;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import edu.scse.tracehub.R;


public class DashboardFragment extends Fragment {
    private final Cat[] cats = {new Cat("今天我非常的快乐",R.drawable.t2),
            new Cat("我很开心" +
                    "",R.drawable.t1),
            new Cat("今天去哪玩",R.drawable.t3),
            new Cat("一起出去玩吧哈哈哈哈哈哈",R.drawable.t4),
            new Cat("蠢猫",R.drawable.cat),
            new Cat("真漂亮啊",R.drawable.t5),
            new Cat("再见",R.drawable.t6),
            new Cat("傻猫",R.drawable.cat2)
    };
    //列表需要的数据表
    private List<Cat> catList = new ArrayList<>();
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.navigation_dashboard, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for(int i = 0; i < 100; i++) catList.add(cats[i % 8]);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerview);
        //格式布局
        int span = 2;
        GridLayoutManager manager = new GridLayoutManager(this.getContext(),span);
        recyclerView.setLayoutManager(manager);
        CatAdapter adapter = new CatAdapter(this.getActivity(),catList);
        adapter.setOnItemClickListener(new CatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

}