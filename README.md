#dzdp (仿大众点评)

##项目示例


</hr>

## Picasso网络缓存库的使用
//加载网络图片
Picasso.with(context).load(imgUrl).placeholder(R.drawable.test_tuan_img1).into(mHolder.iv_img);


< //TODO: 其他緩存操作在之后填写

## 高德地图的使用
1. 定位：
     (1). 添加官方类库,so包放入{$pro}/src/main/jniLibs 下,在app/build.gradle中添加如下代码       
            
            android{
                sourceSets{
                    main{
                        jniLibs.srcDirs = ['libs']
                    }
                }
            }
            
     (2). 继承并实现 AMapLocationListener
            
            /**
             * AMapLocationListener 定位监听接口实现
             */
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null && location.getLatitude() != 0) {
                    lon = location.getLongitude();
                    lat = location.getLatitude();
                    mListener.onLocationChanged(location);
                    Log.i("zlw", "定位信息  ---lon：" + lon + ";lat: " + lat);
                    loadData(lon, lat, 1000);
                }
             } 
     (3). 控制生命周期       

            /**
             * 激活定位
             */
            @Override
            public void activate(OnLocationChangedListener listener) {
                mListener = listener;
                if (mlocationClient == null) {
                    mlocationClient = new AMapLocationClient(this);
                    mLocationOption = new AMapLocationClientOption();
                    //设置定位监听
                    mlocationClient.setLocationListener(this);
                    //设置为高精度定位模式
                    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                    //设置定位参数
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
                    
### 2. 地图：
####0.导入架包...    
####1.在布局文件中添加视图，代码如下
        
            ```java
                <com.amap.api.maps.MapView
                    android:id="@+id/search_map"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" />
            ```
        #### 2. 初始化地图
        
              mMapView.onCreate(savedInstanceState);
                  //初始化
                  if (aMap == null) {
                      aMap = mMapView.getMap();
                      aMap.setLocationSource(this);
                      aMap.setMyLocationEnabled(true);  //设置可以触发定位；默认flase
                      aMap.setOnMapLoadedListener(this); //设置Amap加载成功的时间监听
                      aMap.setOnMarkerClickListener(this);
                      aMap.setInfoWindowAdapter(this); //设置自定义window样式
                      aMap.setOnInfoWindowClickListener(this);//设置点击windowInfo的事件监听器
                      aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
                      // 设置定位的类型为定位模式，参见类AMap。
                      aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
                      aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
                  }
             
            
        
        
        
    ####   3.控制生命周期     

                //管理生命周期
                @Override
                protected void onDestroy() {
                    super.onDestroy();
                    //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
                    mMapView.onDestroy();
                }
            
                @Override
                protected void onResume() {
                    super.onResume();
                    //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
                    mMapView.onResume();
                }
            
                @Override
                protected void onPause() {
                    super.onPause();
                    //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
                    mMapView.onPause();
                }
            
                @Override
                protected void onSaveInstanceState(Bundle outState) {
                    super.onSaveInstanceState(outState);
                    //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
                    mMapView.onSaveInstanceState(outState);
                }

        
        
### 3. 设置标记：    
        
```java
    private void addMarker(List<Goods> list) {
        MarkerOptions markerOptions = null;
        for (Goods goods : list) {
            Shop shop = goods.getShop();
            markerOptions = new MarkerOptions();
            //进行标记
            markerOptions.position(new LatLng(shop.getLat(), shop.getLon()));  //标记位置
            markerOptions.title("" + shop.getName()).snippet("￥" + goods.getPrice()); //标记标题
            //设置图标
            if ("1".equals(goods.getCategoryId())) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_landmark1));
            } else if ("2".equals(goods.getCategoryId())) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_landmark2));
            } else {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_landmark1));
            }

            aMap.addMarker(markerOptions).setObject(goods);
        }
```        




