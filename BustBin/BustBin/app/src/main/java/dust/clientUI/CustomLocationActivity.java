package dust.clientUI;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.dust.R;
import dust.clientBase.base.BaseActivity;
import dust.clientBase.model.StationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * AMapV2地图中介绍自定义定位小蓝点
 * 修改内置定位小蓝点的样式
 */
public class CustomLocationActivity extends BaseActivity implements AMap.OnMyLocationChangeListener, GeocodeSearch.OnGeocodeSearchListener {
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
    private StationModel stationModel;
    private GeocodeSearch geocoderSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.locationmodesource_activity);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        initWidget();
        initData();
    }



    @Override
    public void initWidget() {
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);


        btnTime = (Button) findViewById(R.id.btnTime);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("垃圾桶信息");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        btnTime.setOnClickListener(this);

    }

    @Override
    public void initData() {
        //用一个数组来存放纹理

        stationModel = (StationModel)this.getIntent().getSerializableExtra("msg");
        startInfor = new LatLng(Double.valueOf( stationModel.getStationCoordinate().split(",")[1]), Double.valueOf(stationModel.getStationCoordinate().split(",")[0]));

        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_alr));
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.custtexture));
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_alr_night));

        //指定某一段用某个纹理，对应texTuresList的index即可, 四个点对应三段颜色

        texIndexList.add(0);//对应上面的第0个纹理
        texIndexList.add(2);
        texIndexList.add(1);



        zuobiaoInfor = startInfor.latitude+","+startInfor.longitude+"-";


        options = new PolylineOptions();
        options.width(8);//设置宽度

        //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
        options.setCustomTextureList(texTuresList);
        //设置纹理对应的Index
        options.setCustomTextureIndex(texIndexList);

        View loadingView = LayoutInflater.from(this).inflate(R.layout.markerviewstart, null);
        aMap.addMarker(new MarkerOptions().draggable(true)
                .icon(BitmapDescriptorFactory.fromView(loadingView))
                .position(startInfor));
        options.add(startInfor);
        aMap.addPolyline(options);



        handler.postDelayed(update_thread, 1000);

        addressFlag = 1;
        LatLonPoint latLonPoint = new LatLonPoint(Double.valueOf( stationModel.getStationCoordinate().split(",")[1]), Double.valueOf(stationModel.getStationCoordinate().split(",")[0]));
        getAddress(latLonPoint);
    }


    private int addressFlag=1;

    /**
     * 初始化
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }
    LatLng startInfor;
    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {




        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象

        mUiSettings.setZoomControlsEnabled(false);
        mUiSettings.setMyLocationButtonEnabled(false); //显示默认的定位按钮

        aMap.setOnMyLocationChangeListener(this);
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.moveCamera(CameraUpdateFactory.zoomTo(13));




        setupLocationStyle();


    }
    MyLocationStyle myLocationStyle;
    /**
     * 设置自定义定位蓝点
     */
    private void setupLocationStyle() {
        // 自定义系统定位蓝点
        myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.common_map_my_location));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(STROKE_COLOR);
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(5);
        myLocationStyle.interval(3000);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(FILL_COLOR);

        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);

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
    }

    private Polyline polyline;
    private boolean isFirstLoacl = true;
    List<LatLng> latLngs = new ArrayList<LatLng>();
    Location mLocation;
    @Override
    public void onMyLocationChange(Location location) {
        mLocation = location;
        Log.i("pony_log", location.getLatitude() + "");

        String lat = location.getLatitude()+"";
        String lon = location.getLongitude()+"";


        if(zuobiaoInfor.indexOf(lat.substring(0,lat.indexOf(".")+7))==-1){
            zuobiaoInfor=zuobiaoInfor+lat.substring(0,lat.indexOf(".")+7)+","+lon.substring(0,lon.indexOf(".")+7)+"-";
        }else{
            Log.i("pony_log","数据判断已存在");
        }
        Log.i("pony_log","zuobiaoInfor:"+zuobiaoInfor);

        if (isFirstLoacl) {
            isFirstLoacl = false;
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
        }

        //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
        options.setCustomTextureList(texTuresList);
        //加入四个点
        options.add(new LatLng(location.getLatitude(), location.getLongitude()));
        aMap.addPolyline(options);
//		//设置纹理对应的Index
//		options.setCustomTextureIndex(texIndexList);
//		latLngs.add(new LatLng(location.getLatitude(), location.getLongitude()));
//		polyline =aMap.addPolyline(new PolylineOptions().
//				addAll(latLngs).width(2).color(Color.argb(255, 1, 1, 1)));


    }


    private String zuobiaoInfor="";


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mIvBack:
                finish();
                break;
            case R.id.btnTime:


                addressFlag = 2;
                LatLonPoint latLonPoint = new LatLonPoint(mLocation.getLatitude(), mLocation.getLongitude());
                getAddress(latLonPoint);


                break;




        }
    }


    long leftTime = 0;
    Handler handler = new Handler();
    Runnable update_thread = new Runnable() {
        @Override
        public void run() {
            leftTime++;
            if (leftTime > 0) {
                // 倒计时效果展示
                String formatLongToTimeStr = formatLongToTimeStr(leftTime);
                btnTime.setText(formatLongToTimeStr);
                // 每一秒执行一次
                handler.postDelayed(this, 1000);


            } else {// 倒计时结束
                // 处理业务流程

                // 发送消息，结束倒计时
                // Message message = new Message();
                // message.what = 1;
                // handlerStop.sendMessage(message);

            }
        }
    };

    @SuppressLint("HandlerLeak")
    final Handler handlerStop = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    leftTime = 0;
                    handler.removeCallbacks(update_thread);
                    break;
            }
            super.handleMessage(msg);
        }

    };

    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60; // 取整
            second = second % 60; // 取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }

        String minutemsg = "";
        String secondmsg = "";
        if (minute < 10) {
            minutemsg = "0" + minute;
        } else {
            minutemsg = minute + "";
        }

        if (second < 10) {
            secondmsg = "0" + second;
        } else {
            secondmsg = "" + second;
        }

        String strtime = minutemsg + ":" + secondmsg;
        return strtime;
    }

//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//		Message message = new Message();
//		message.what = 1;
//		handlerStop.sendMessage(message);
//	}
//
    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
    }


    String startAddress;
    String endAddress;
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {

                if(addressFlag==1){
                    startAddress = result.getRegeocodeAddress().getFormatAddress();
                }else{
                    endAddress = result.getRegeocodeAddress().getFormatAddress();

                    Intent intent = new Intent(this, PayMessageActivity.class);
                    intent.putExtra("msg", stationModel);
                    intent.putExtra("longTime", leftTime+ "");
                    intent.putExtra("TimeInfor", btnTime.getText()+ "");
                    intent.putExtra("options", zuobiaoInfor.substring(0,zuobiaoInfor.length()-1));

                    intent.putExtra("startAddress", startAddress+ "");
                    intent.putExtra("endAddress", endAddress+ "");

                    startActivity(intent);
                    finish();


                }
                Log.i("pony_log",startAddress);
                Log.i("pony_log",endAddress);
            } else {
            }
        } else {
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
