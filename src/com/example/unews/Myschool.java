package com.example.unews;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.List;

import android.os.Bundle;
public class Myschool extends Activity {
	 private LocationManager locationManager;
	    private String provider;
	    MapView mapView;
	    BaiduMap baiduMap;
	    boolean ifFrist = true;
	    private static final String TAG = "Myschool";
	    private MyLocationData.Builder builder;
	    private MyLocationData data;

protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	SDKInitializer.initialize(getApplicationContext());
	setContentView(R.layout.myschool);
	mapView = (MapView) findViewById(R.id.bmapView);
    // 获取baiduMap对象
    baiduMap = mapView.getMap();
    // 设置可改变地图位置
    baiduMap.setMyLocationEnabled(true);
    //当地图被点击后调用
    baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            //更新图标位置
            builder = new MyLocationData.Builder();
            builder.latitude(latLng.latitude);
            builder.longitude(latLng.longitude);
            data = builder.build(); 
            baiduMap.setMyLocationData(data);

            /**从服务器获取数据，得到大学的具体位置数据和头条信息，
             * 用循环为各个大学添加图片标注，然后绑定点击事件
             *下面为一个实例
             */
            
//            显示marker有点问题，我在网上找了一段代码，还没试成功，嘿嘿
//          //获取头像布局View
//            View personLocView = ViewUtils.getPersonLoView(mContext,
//             account);
//            //通过View去找到ImageView控件,这是我自定义圆形头像ImageView
//            CircleImageView headAvatar = (CircleImageView)
//             personLocView.findViewById(R.drawable.ic_launcher);
//            CiatWildHelper.getInstance().getUserProfileManager().asyncGetUserInfo(account,
//            		new
//            		EMValueCallBack<EaseUser>() {
//            		@Override
//            		public voidonSuccess(EaseUser user) {
//            		if(user!=null){
//            		if(!TextUtils.isEmpty(user.getAvatar())){//用户头像地址不为空,设置完头像
//            		//把请求到的图片设置到ImageView上,这里设置Glide去加载,这个开源库使用很方便
//            		Glide.with(mContext).load(user.getAvatar())
//            		.placeholder(R.mipmap.em_default_avatar).into(headAvatar);
//
//
//            		//设置成功后把View转换成Bitmap
//            		Bitmap viewBitmap = ViewUtils.getViewBitmap(personLocView);
//
//
//            		//调用百度地图提供的api获取刚转换的Bitmap
//            		BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromBitmap(viewBitmap);
//            		//构建Marker对象和指定经纬度
//            		MarkerOptions marker=newMarkerOptions()
//            		.icon(bitmapDescriptor)
//            		.position(latLng);
//            		//添加到地图上
//            		baiduMap.addOverlay(marker);
//            		}
//            		}
//            		}
//
//
//            		@Override
//            		public voidonError(interror,
//            		 String errorMsg) {
//            		Log.e("asyncFetchUserInfo","地图加载头像失败");
//            		}
//            		});
//            
//            
            LatLng latLng1 = new LatLng(114.515067,38.089527);//114.515067经度，38.089527为纬度
            //创建标注图片
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_launcher);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(latLng1)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            final Marker marker1 = (Marker) baiduMap.addOverlay(option);

            baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if(marker==marker1){
                        //传入数据，打开头条activity
            Intent intent=new Intent();
     		intent.setClass(Myschool.this,News.class);//要启动的窗体
     		startActivity(intent);
                    }

                    return false;
                }
            });

            // 创建地理编码检索实例
            GeoCoder geoCoder = GeoCoder.newInstance();
            //
            OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
                // 反地理编码查询结果回调函数
                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                    if (result == null
                            || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        // 没有检测到结果
                        Toast.makeText(Myschool.this, "抱歉，未能找到结果",
                                Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(Myschool.this,
                            "位置：" + result.getAddress(), Toast.LENGTH_LONG)
                            .show();
                }

                // 地理编码查询结果回调函数
                @Override
                public void onGetGeoCodeResult(GeoCodeResult result) {
                    if (result == null
                            || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        // 没有检测到结果
                    }
                }
            };
            // 设置地理编码检索监听者
            geoCoder.setOnGetGeoCodeResultListener(listener);
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
            // 释放地理编码检索实例
            // geoCoder.destroy();


        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {

            return false;
        }
    });
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    List<String> list = locationManager.getProviders(true);
    if (list.contains(LocationManager.GPS_PROVIDER)) {
        provider = LocationManager.GPS_PROVIDER;
    } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
        provider = LocationManager.NETWORK_PROVIDER;

    } else {
        Toast.makeText(this, "当前不能提供位置信息", Toast.LENGTH_LONG).show();
        return;
    }
    Location location = locationManager.getLastKnownLocation(provider);
    if (location != null) {
        navigateTo(location);
    }
//    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//        // TODO: Consider calling
//        //    ActivityCompat#requestPermissions
//        // here to request the missing permissions, and then overriding
//        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//        //                                          int[] grantResults)
//        // to handle the case where the user grants the permission. See the documentation
//        // for ActivityCompat#requestPermissions for more details.
//    	
//        return;
//    }
    locationManager.requestLocationUpdates(provider, 5000, 1,
            locationListener);

}

private void navigateTo(Location location) {
    // 按照经纬度确定地图位置
    if (ifFrist) {
        LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        // 移动到某经纬度
        baiduMap.animateMapStatus(update);
        update = MapStatusUpdateFactory.zoomBy(5f);
        // 放大
        baiduMap.animateMapStatus(update);

        ifFrist = false;
    }
    // 显示个人位置图标
    builder = new MyLocationData.Builder();
    builder.latitude(location.getLatitude());
    builder.longitude(location.getLongitude());
    data = builder.build();
    baiduMap.setMyLocationData(data);
}

LocationListener locationListener = new LocationListener() {

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub


    }

    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationChanged(Location arg0) {
        // TODO Auto-generated method stub
        // 位置改变则重新定位并显示地图
        //navigateTo(arg0);
    }
};

@Override
protected void onDestroy() {
    // 释放资源
    super.onDestroy();
    if (locationManager != null) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        locationManager.removeUpdates(locationListener);
    }

    mapView.onDestroy();

}

class LocationListenner implements BDLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        Log.d(TAG, "地址" + location.getAddrStr().toString());

    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    public void onReceivePoi(BDLocation poiLocation) {
        if (poiLocation == null) {
            return;
        }

    }
}

}
