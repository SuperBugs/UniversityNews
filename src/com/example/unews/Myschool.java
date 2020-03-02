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
    // ��ȡbaiduMap����
    baiduMap = mapView.getMap();
    // ���ÿɸı��ͼλ��
    baiduMap.setMyLocationEnabled(true);
    //����ͼ����������
    baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            //����ͼ��λ��
            builder = new MyLocationData.Builder();
            builder.latitude(latLng.latitude);
            builder.longitude(latLng.longitude);
            data = builder.build(); 
            baiduMap.setMyLocationData(data);

            /**�ӷ�������ȡ���ݣ��õ���ѧ�ľ���λ�����ݺ�ͷ����Ϣ��
             * ��ѭ��Ϊ������ѧ���ͼƬ��ע��Ȼ��󶨵���¼�
             *����Ϊһ��ʵ��
             */
            
//            ��ʾmarker�е����⣬������������һ�δ��룬��û�Գɹ����ٺ�
//          //��ȡͷ�񲼾�View
//            View personLocView = ViewUtils.getPersonLoView(mContext,
//             account);
//            //ͨ��Viewȥ�ҵ�ImageView�ؼ�,�������Զ���Բ��ͷ��ImageView
//            CircleImageView headAvatar = (CircleImageView)
//             personLocView.findViewById(R.drawable.ic_launcher);
//            CiatWildHelper.getInstance().getUserProfileManager().asyncGetUserInfo(account,
//            		new
//            		EMValueCallBack<EaseUser>() {
//            		@Override
//            		public voidonSuccess(EaseUser user) {
//            		if(user!=null){
//            		if(!TextUtils.isEmpty(user.getAvatar())){//�û�ͷ���ַ��Ϊ��,������ͷ��
//            		//�����󵽵�ͼƬ���õ�ImageView��,��������Glideȥ����,�����Դ��ʹ�úܷ���
//            		Glide.with(mContext).load(user.getAvatar())
//            		.placeholder(R.mipmap.em_default_avatar).into(headAvatar);
//
//
//            		//���óɹ����Viewת����Bitmap
//            		Bitmap viewBitmap = ViewUtils.getViewBitmap(personLocView);
//
//
//            		//���ðٶȵ�ͼ�ṩ��api��ȡ��ת����Bitmap
//            		BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromBitmap(viewBitmap);
//            		//����Marker�����ָ����γ��
//            		MarkerOptions marker=newMarkerOptions()
//            		.icon(bitmapDescriptor)
//            		.position(latLng);
//            		//��ӵ���ͼ��
//            		baiduMap.addOverlay(marker);
//            		}
//            		}
//            		}
//
//
//            		@Override
//            		public voidonError(interror,
//            		 String errorMsg) {
//            		Log.e("asyncFetchUserInfo","��ͼ����ͷ��ʧ��");
//            		}
//            		});
//            
//            
            LatLng latLng1 = new LatLng(114.515067,38.089527);//114.515067���ȣ�38.089527Ϊγ��
            //������עͼƬ
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_launcher);
            //����MarkerOption�������ڵ�ͼ�����Marker
            OverlayOptions option = new MarkerOptions()
                    .position(latLng1)
                    .icon(bitmap);
            //�ڵ�ͼ�����Marker������ʾ
            final Marker marker1 = (Marker) baiduMap.addOverlay(option);

            baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if(marker==marker1){
                        //�������ݣ���ͷ��activity
            Intent intent=new Intent();
     		intent.setClass(Myschool.this,News.class);//Ҫ�����Ĵ���
     		startActivity(intent);
                    }

                    return false;
                }
            });

            // ��������������ʵ��
            GeoCoder geoCoder = GeoCoder.newInstance();
            //
            OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
                // ����������ѯ����ص�����
                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                    if (result == null
                            || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        // û�м�⵽���
                        Toast.makeText(Myschool.this, "��Ǹ��δ���ҵ����",
                                Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(Myschool.this,
                            "λ�ã�" + result.getAddress(), Toast.LENGTH_LONG)
                            .show();
                }

                // ��������ѯ����ص�����
                @Override
                public void onGetGeoCodeResult(GeoCodeResult result) {
                    if (result == null
                            || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        // û�м�⵽���
                    }
                }
            };
            // ���õ���������������
            geoCoder.setOnGetGeoCodeResultListener(listener);
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
            // �ͷŵ���������ʵ��
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
        Toast.makeText(this, "��ǰ�����ṩλ����Ϣ", Toast.LENGTH_LONG).show();
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
    // ���վ�γ��ȷ����ͼλ��
    if (ifFrist) {
        LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        // �ƶ���ĳ��γ��
        baiduMap.animateMapStatus(update);
        update = MapStatusUpdateFactory.zoomBy(5f);
        // �Ŵ�
        baiduMap.animateMapStatus(update);

        ifFrist = false;
    }
    // ��ʾ����λ��ͼ��
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
        // λ�øı������¶�λ����ʾ��ͼ
        //navigateTo(arg0);
    }
};

@Override
protected void onDestroy() {
    // �ͷ���Դ
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
        Log.d(TAG, "��ַ" + location.getAddrStr().toString());

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
