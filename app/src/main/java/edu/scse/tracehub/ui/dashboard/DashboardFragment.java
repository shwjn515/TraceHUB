package edu.scse.tracehub.ui.dashboard;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.JacksonMsgConvertor;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.scse.tracehub.R;
import edu.scse.tracehub.ui.home.photo;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 动态交互协议
 * 三步握手
 * 发布
 * 1.客户端向/active端口发送用户,和内容
 * 2.服务端生成动态id,保存动态,返回动态id
 * 3.客服端接收动态id,如果有图片,则上传图片,参数里带动态id
 *
 * 获取动态
 * 1.向/actives端口申请动态列表,服务端返回动态(用户,id,内容)和预览图id(id)
 * 2.前端保存动态列表依据图id向/img获取图
 *
 *
 * 详细动态
 * 1.依据动态id向/imgs申请需要的图id列表 List<String>
 * 2.向/img获取所有图
 * 3.布置到页面上
 */

public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private Button upphoto;

    public View onCreateView(@NotNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        //View root = inflater.inflate(R.layout.navigation_dashboard, container, false);
        //列表，上传切换
        final View root = inflater.inflate(R.layout.navigation_dashboard, container, false);
        upphoto = root.findViewById(R.id.btn_shangchuan2);
        upphoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), sharing.class);
                startActivity(intent);
            }
        });
        return root;
    }
    static ActivesAdapter adapter;
    public static void flush()
    {
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //请求预览图
        //getActivesList();
        //布置Recycle
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerview);
        int span = 2;
        GridLayoutManager manager = new GridLayoutManager(getContext(),span);
        recyclerView.setLayoutManager(manager);
        adapter = new ActivesAdapter(getContext(),
                activeList,imgs);
        adapter.setOnItemClickListener(new ActivesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //根据position获取相应的active id,然后将id传入详情页
                String active_id = activeList.get(position).getA_id();
                Toast.makeText(getContext(),"动态编号:" + active_id, Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    // 放入列表的数据集
    static List<Active> activeList = new ArrayList<>();
    static List<Bitmap> imgs = new ArrayList<>();
    public static void addData(Active active,Bitmap img)
    {
        activeList.add(active);
        imgs.add(img);
    }
    private void getActivesList()
    {
        String url = "http://117.78.3.88:8080";
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
                .baseUrl(url)
                .addMsgConvertor(new JacksonMsgConvertor())
                .build();
        //请求动态列表
        HashMap<String,Object> ret =
                http.async("/actives")
                        .bind(this.getLifecycle())
                        .get()
                        .getResult()
                        .getBody()
                        .toBean(new HashMap<String,Object>().getClass());
        //动态仍然是一个键值对数组
        ArrayList<HashMap<String,Object>> actives = (ArrayList<HashMap<String,Object>>) ret.get("actives");
        for (int i = 0; i < actives.size(); i++)
        {
        HashMap<String,Object> active = actives.get(i);
        activeList.add(new Active((String) active.get("a_id"),
                (String) active.get("user"),
                (String) active.get("text")));
        }
        ArrayList<HashMap<String,Object>> img_id = (ArrayList<HashMap<String,Object>>) ret.get("foreview");
        //请求图片
        //请求详细图片也是类似的,只是迭代的列表不一样,这个迭代预览列表,那个迭代详细列表
        //详细列表是List<String>
        for (int i = 0; i < img_id.size();i++)
        {
        String img = (String) img_id.get(i).get("pid");
        byte[] bit =
                http.async("/img?img_id={img_id}")
                        .bind(this.getLifecycle())
                        .addPathPara("img_id",img)
                        .get()
                        .getResult()
                        .getBody()
                        .toBytes();
        //接收二进制字符流,转换为位图
        Bitmap bitmap = BitmapFactory.decodeByteArray(bit,0,bit.length);
        imgs.add(bitmap);
        }
    }
}