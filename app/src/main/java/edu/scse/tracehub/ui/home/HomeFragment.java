package edu.scse.tracehub.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;

import java.lang.reflect.Field;

import edu.scse.tracehub.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextureMapView textureMapView;
    private AMap aMap;
    public static final LatLng TIANJIN = new LatLng(39.13,117.2);// 天津市经纬度
    protected static CameraPosition cameraPosition;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.navigation_home, container, false);
        return root;
    }
    LatLng getTarget() {
        return TIANJIN;
    }
    CameraPosition getCameraPosition() {
        return cameraPosition;
    }
    void setCameraPosition(CameraPosition cameraPosition) {
        cameraPosition = cameraPosition;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textureMapView = (TextureMapView) getView().findViewById(R.id.map);
        if (textureMapView != null) {
            textureMapView.onCreate(savedInstanceState);
            aMap = textureMapView.getMap();
            if (getCameraPosition() == null) {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(getTarget(), 10, 0, 0)));
            }else {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(getCameraPosition()));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        textureMapView.onResume();
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        textureMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        textureMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        setCameraPosition(aMap.getCameraPosition());
        super.onDestroy();
        textureMapView.onDestroy();
    }
}

