package com.traffic.locationremind.baidu.location.activity;


import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.traffic.locationremind.baidu.location.service.LocationService;
import com.traffic.locationremind.baidu.location.service.RemonderLocationService;
import com.traffic.locationremind.common.util.ReadExcelDataUtil;
import com.traffic.locationremind.manager.database.DataHelper;

/**
 * 主Application，所有百度定位SDK的接口说明请参考线上文档：http://developer.baidu.com/map/loc_refer/index.html
 * <p>
 * 百度定位SDK官方网站：http://developer.baidu.com/map/index.php?title=android-locsdk
 * <p>
 * 直接拷贝com.baidu.location.service包到自己的工程下，简单配置即可获取定位结果，也可以根据demo内容自行封装
 */
public class LocationApplication extends Application {
    public LocationService locationService;
    public Vibrator mVibrator;
    private DataHelper mDataHelper;
    private ReadExcelDataUtil mReadExcelDataUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        mDataHelper = DataHelper.getInstance(this);
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        //if(mDataHelper.getCount(SqliteHelper.TB_LINE) <= 0) {
        mReadExcelDataUtil = ReadExcelDataUtil.getInstance();
        mReadExcelDataUtil.execute(this);
        //}
        Intent startIntent = new Intent(this, RemonderLocationService.class);
        startService(startIntent);

    }

    public DataHelper getDataHelper(){
        return mDataHelper;
    }

    public void onDestory(){
        if(mDataHelper != null){
            mDataHelper.Close();
            Intent stopIntent = new Intent(this, RemonderLocationService.class);
            stopService(stopIntent);
        }
    }
}
