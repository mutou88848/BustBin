package com.clientBase.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.dustbin.R;
import com.clientBase.app.PonyApplication;
import com.clientBase.base.BaseFragment;
import com.clientBase.config.Consts;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.StationModel;
import com.clientBase.util.ToastUtil;
import com.clientBase.view.SensorEventHelper;
import com.clientUI.BinCodeMessageActivity;
import com.google.gson.reflect.TypeToken;
import com.zxing.activity.CaptureActivity;
import com.zxing.util.Constant;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.app.Activity.RESULT_OK;


public class StationFragment extends BaseFragment implements LocationSource, AMapLocationListener, OnGeocodeSearchListener, OnMarkerClickListener {
    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private boolean mFirstFix = false;
    private Marker mLocMarker;
    private SensorEventHelper mSensorHelper;
    private Circle mCircle;
    public static final String LOCATION_MARKER_FLAG = "mylocation";
    // 获取view
    private View rootView;
    private List<StationModel> list_result = new ArrayList<StationModel>();
    private RelativeLayout mllTop;
    private TextView mtvName;
    private TextView mtvType;
    private ImageView mivDelete;
    private UiSettings mUiSettings;
    private Button saomaCode;
    //经度 维度
    private String log;
    private String lat;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hotel, null);
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        initWidget();
        initData();
        return rootView;
    }

    @Override
    public void initWidget() {
        saomaCode = (Button) rootView.findViewById(R.id.saomaCode);
        saomaCode.setOnClickListener(this);
        mllTop = (RelativeLayout) rootView.findViewById(R.id.mllTop);
        mllTop.setVisibility(View.GONE);
        mivDelete = (ImageView) rootView.findViewById(R.id.mivDelete);
        mtvType = (TextView) rootView.findViewById(R.id.mtvType);
        mtvName = (TextView) rootView.findViewById(R.id.mtvName);
        mivDelete.setOnClickListener(this);
        mllTop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mivDelete:
                mllTop.setVisibility(View.GONE);
                break;
            case R.id.saomaCode:
                startQrCode();
                break;
            case R.id.mllTop:

//				if(list_result.get(numberPos).isStationState()){
//
//				}
//
//				if(list_result.get(numberPos).isStationState()){
//					CustomToast.showToast(getActivity(), "此停车位已经被人使用");
//					return;
//				}

//                Intent intent = new Intent(getActivity(), CustomLocationActivity.class);
//                intent.putExtra("msg", list_result.get(numberPos));
//                getActivity().startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void initData() {
//		LostAction(false);
    }

    private void LostAction(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listSceneryPhoneMessage");
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<StationModel>>() {
                        }.getType());

                        getAddresses();
                    }
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(getActivity(), strMsg);

    }

    /**
     * 初始化
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
//			mUiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
            setUpMap();
        }
        mSensorHelper = new SensorEventHelper(getActivity());
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }

        geocoderSearch = new GeocodeSearch(getActivity());
        geocoderSearch.setOnGeocodeSearchListener(this);

        LostAction(false);
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.setOnMarkerClickListener(this);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();

        mapView.onResume();
        initWidget();
        initData();
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        } else {
            mSensorHelper = new SensorEventHelper(getActivity());
            if (mSensorHelper != null) {
                mSensorHelper.registerSensorListener();

                if (mSensorHelper.getCurrentMarker() == null && mLocMarker != null) {
                    mSensorHelper.setCurrentMarker(mLocMarker);
                }
            }
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
            mSensorHelper.setCurrentMarker(null);
            mSensorHelper = null;
        }
        mapView.onPause();
        deactivate();
        mFirstFix = false;
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }

        if (mExecutorService != null) {
            mExecutorService.shutdownNow();
        }
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                LatLng location = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                if (!mFirstFix) {
                    mFirstFix = true;
                    addCircle(location, amapLocation.getAccuracy());// 添加定位精度圆
                    addMarker(location);// 添加定位图标
                    mSensorHelper.setCurrentMarker(mLocMarker);// 定位图标旋转
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
                    Log.i("pony_log", amapLocation.getAddress());
                    lat= String.valueOf(amapLocation.getLatitude());
                    log=String.valueOf(amapLocation.getLongitude());
                    Log.i("222", "onLocationChanged: 维度"+amapLocation.getLatitude()+"-----经度"+amapLocation.getLongitude());
                    PonyApplication.getInstance().aMapLocation = amapLocation;
                }
                // else {
                // mCircle.setCenter(location);
                // mCircle.setRadius(amapLocation.getAccuracy());
                // mLocMarker.setPosition(location);
                // }


            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mlocationClient.setLocationListener(this);
            // 设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            // 设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 添加Circle
     *
     * @param latlng 坐标
     * @param radius 半径
     */
    private void addCircle(LatLng latlng, double radius) {
        CircleOptions options = new CircleOptions();
        options.strokeWidth(1f);
        options.fillColor(FILL_COLOR);
        options.strokeColor(STROKE_COLOR);
        options.center(latlng);
        options.radius(radius);
        mCircle = aMap.addCircle(options);
    }

    /**
     * 添加Marker
     */
    private void addMarker(LatLng latlng) {
        if (mLocMarker != null) {
            return;
        }
        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.common_map_my_location);
        BitmapDescriptor des = BitmapDescriptorFactory.fromBitmap(bMap);
        MarkerOptions options = new MarkerOptions();
        options.icon(des);
        options.anchor(0.5f, 0.5f);
        options.position(latlng);
        mLocMarker = aMap.addMarker(options);
        mLocMarker.setTitle(LOCATION_MARKER_FLAG);
    }

    private GeocodeSearch geocoderSearch;
    private ExecutorService mExecutorService;
    private Marker regeoMarker;

    /**
     * 响应逆地理编码的批量请求
     */
    private void getAddresses() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newSingleThreadExecutor();
        }
        aMap.clear();
        List<LatLonPoint> geopointlist = readLatLonPoints();
        for (final LatLonPoint point : geopointlist) {

            aMap.addMarker(new MarkerOptions().draggable(true)
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.qqq)))
                    .position(new LatLng(point.getLatitude(), point.getLongitude())));
//距离
//            mExecutorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    RegeocodeQuery query = new RegeocodeQuery(point, 200, GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
//                    RegeocodeAddress result = null;
//                    try {
//                        result = geocoderSearch.getFromLocation(query);
//                    } catch (AMapException e) {
//                        e.printStackTrace();
//                    }
//                    if (result != null && result.getFormatAddress() != null) {
//
//                    }
//
//                }
//            });
        }
    }

    private Handler msgHandler = new Handler() {
        public void handleMessage(Message msg) {
        }
    };

    @Override
    public void onGeocodeSearched(GeocodeResult arg0, int arg1) {

    }

    /**
     * 逆地理编码回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
    }

    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }


    private int numberPos = 0;

    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (aMap != null) {
            jumpPoint(marker);
        }


        LatLng markerLatlng = marker.getPosition();


        for (int i = 0; i < list_result.size(); i++) {
            Log.i("pony_log", "markerLatlng.longitude:" + (Double.valueOf(markerLatlng.longitude) + ""));
            Log.i("pony_log", "markerLatlng.longitude:" + list_result.get(i).getStationCoordinate().split(",")[0]);

            if ((markerLatlng.longitude + "").equals(list_result.get(i).getStationCoordinate().split(",")[0])) {
                Log.i("pony_log", "markerLatlng.longitude:" + "ok");
//				Toast.makeText(getActivity(), list_result.get(i).getlName(), Toast.LENGTH_LONG).show();
                mllTop.setVisibility(View.VISIBLE);
                mtvName.setText("编号：" + list_result.get(i).getstationTitle()+"\n状态："+ list_result.get(i).getfullEmpty()
                +"\n运营："+ list_result.get(i).getactionDump()+  list_result.get(i).gettype()
                        +"\n异常：" + list_result.get(i).getstatus() );
                mtvType.setText("" );
//                mtvType.setText("NO：" + list_result.get(i).gettype());
//                mtvType.setText("NO：" + list_result.get(i).getstatus());
                numberPos = i;


            }

        }

//
        return true;
    }

    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        final LatLng markerLatlng = marker.getPosition();
        Point markerPoint = proj.toScreenLocation(markerLatlng);
        markerPoint.offset(0, -100);

        final LatLng startLatLng = proj.fromScreenLocation(markerPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed/ duration);
                double lng = t * markerLatlng.longitude + (1 - t)* startLatLng.longitude;
                double lat = t * markerLatlng.latitude + (1 - t)* startLatLng.latitude;
                marker.setPosition(markerLatlng);
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    private List<LatLonPoint> readLatLonPoints() {
        List<LatLonPoint> points = new ArrayList<LatLonPoint>();
        for (int i = 0; i < list_result.size(); i++) {

            points.add(new LatLonPoint(Double.valueOf(list_result.get(i).getStationCoordinate().split(",")[1]), Double.valueOf(list_result.get(i).getStationCoordinate().split(",")[0])));
        }
        return points;
    }


    // 开始扫码
    private void startQrCode() {
        // 二维码扫码
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        intent.putExtra("log",log);
        intent.putExtra("lat",lat);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            Intent resultIntent = new Intent(getActivity(), BinCodeMessageActivity.class);
            resultIntent.putExtra("msg",scanResult);
            resultIntent.putExtra("log",log);
            resultIntent.putExtra("lat",lat);
            startActivity(resultIntent);
//            Toast.makeText(getActivity(), "111"+scanResult, Toast.LENGTH_SHORT).show();
            Log.i("111", "onActivityResult: "+scanResult);
            //将扫描出的信息显示出来
//            tvResult.setText(scanResult);
        }
    }



}
