package com.clientUI;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.dustbin.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.model.OrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * AMapV2地图中介绍自定义定位小蓝点
 * 修改内置定位小蓝点的样式
 */
public class OrderInforActivity extends BaseActivity  {
    private AMap aMap;
    private MapView mapView;

    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);

    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private Button btnTime;
    List<BitmapDescriptor> texTuresList = new ArrayList<BitmapDescriptor>();
    PolylineOptions options;
    List<Integer> texIndexList = new ArrayList<Integer>();
    private OrderBean stationModel;

    private Marker marker;
    private Polyline polyline;
    private boolean isFirstLoacl = true;
    List<LatLng> latLngs = new ArrayList<LatLng>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        initWidget();
        initData();
    }


    @Override
    public void initWidget() {

        btnTime = (Button) findViewById(R.id.btnTime);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("骑行线路轨迹信息");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        btnTime.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //用一个数组来存放纹理

        stationModel = (OrderBean)this.getIntent().getSerializableExtra("msg");

        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_alr));
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.custtexture));
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_alr_night));

        //指定某一段用某个纹理，对应texTuresList的index即可, 四个点对应三段颜色

        texIndexList.add(0);//对应上面的第0个纹理
        texIndexList.add(2);
        texIndexList.add(1);



        String latlng = stationModel.getOrderLatLng();
        String[] latArray=latlng.split("-");

        // 读取轨迹点
        List<LatLng> points = new ArrayList<LatLng>();


        View loadingViewStart = LayoutInflater.from(this).inflate(R.layout.markerviewstart, null);
        View loadingViewEnd = LayoutInflater.from(this).inflate(R.layout.markerviewend, null);
        aMap.addMarker(new MarkerOptions().draggable(true)
                .icon(BitmapDescriptorFactory.fromView(loadingViewStart))
                .position(new LatLng(Double.valueOf(latArray[0].split(",")[0]), Double.valueOf(latArray[0].split(",")[1]))));

        aMap.addMarker(new MarkerOptions().draggable(true)
                .icon(BitmapDescriptorFactory.fromView(loadingViewEnd))
                .position(new LatLng(Double.valueOf(latArray[latArray.length-1].split(",")[0]), Double.valueOf(latArray[latArray.length-1].split(",")[1]))));


        options = new PolylineOptions();
        options.width(8);//设置宽度
        //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
        options.setCustomTextureList(texTuresList);
        //设置纹理对应的Index
        options.setCustomTextureIndex(texIndexList);
        //加入四个点
        for(int i=0;i<latArray.length;i++){
            options.add(new LatLng(Double.valueOf(latArray[i].split(",")[0]), Double.valueOf(latArray[i].split(",")[1])));
            points.add(new LatLng(Double.valueOf(latArray[i].split(",")[0]), Double.valueOf(latArray[i].split(",")[1])));
        }
        aMap.addPolyline(options);
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(Double.valueOf(latArray[0].split(",")[0]), Double.valueOf(latArray[0].split(",")[1]))));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));





        //轨迹移动


        // 实例 MovingPointOverlay 对象
        if(smoothMarker == null) {
            // 设置 平滑移动的 图标
            marker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bininfoer)).anchor(0.5f,0.5f));
            smoothMarker = new MovingPointOverlay(aMap, marker);
        }

        // 取轨迹点的第一个点 作为 平滑移动的启动
        LatLng drivePoint = points.get(0);
        Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());

        // 设置轨迹点
        smoothMarker.setPoints(subList);
        // 设置平滑移动的总时间  单位  秒
        smoothMarker.setTotalDuration(40);

        // 显示 infowindow
        marker.showInfoWindow();

        // 设置移动的监听事件  返回 距终点的距离  单位 米
        smoothMarker.setMoveListener(new MovingPointOverlay.MoveListener() {
            @Override
            public void move(final double distance) {

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnTime.setText("距离终点还有："+(int) distance+"米");
                        }
                    });

                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        // 开始移动
        smoothMarker.startSmoothMove();

    }

    /**
     * 初始化
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    private MovingPointOverlay smoothMarker;


    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(false); //显示默认的定位按钮



    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        // 销毁平滑移动marker
        if(smoothMarker != null) {
            smoothMarker.setMoveListener(null);
            smoothMarker.destroy();
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mIvBack:
                finish();
                break;
        }
    }




}
