# baseabner
## 一个方便大家的库
### 初始化
```
	下载好之后，可以直接依赖这个库，需要注意的是,库里使用到了数据库，所以需要一些配置：
	在跟项目的build里添加以下：
	
	在  repositories 里添加  mavenCentral()
	
	在   dependencies 添加：
	classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add plugin

	这个库里AbnerApplication已经做了初始化和分包处理，所以在使用的时候，需要让你的Application来继承这个
	或者在清单文件里，直接来引入这个
```
### Base依赖

```
	Activity可继承BaseAppCompatActivity
	Fragment可继承 BaseFragment
	RecyclerView的适配器可继承BaseAdapter
	ListView的适配器可继承UniversalAdapter
	
```

### 访问网络：

```
	网络工具类是HttpUtils
	如果你继承了父类，那么访问网络就简单了：
	获取字符串
	//net方法里两个参数，一个是dialog是否显示，一个是缓存是否读取
	//具体参数，看代码里的注释吧
	net(true,true).get(0,"/598254259/FristProject/raw/master/bw_test.txt",null);
	
	获取JavaBean
	//net里多了一个要获取对象的class
	net(true,true, CacheBean.class).get(0,"/598254259/FristProject/raw/master/bw_test.txt",null);

```

### Alert使用（可自行定义或者修改）

```

    1、菊花加载（背景半透明）：
              new DialogLoading.Builder(MainActivity.this)
                                  .AlertBg()
                                  .show();
    2、菊花加载（背景透明）：
              new DialogLoading.Builder(MainActivity.this)
                                  .show();
    3、弹出框
              new AlertUtils.Builder(MainActivity.this)
                           .AlertBg()
                           .title("重要公告")
                           .desc("什么重要公告啊")
                           .setConfimListenr(new AlertUtils.Builder.OnConfimListener() {
                               @Override
                               public void confim() {

                               }
                           }).canceled().show();
                说明：

                    AlertBg：是否显示半透明
                    canceled:点击空白区域不隐藏

```

### 进应用欢迎页显示

```
    xml布局：

       <com.abner.ming.abnerlibrary.viewpage.BGAGuideLinkageLayout
            style="@style/MatchMatch">
            <com.abner.ming.abnerlibrary.viewpage.BGABanner
                android:id="@+id/banner_guide_background"
                style="@style/MatchMatch"
                app:banner_pageChangeDuration="1000"
                app:banner_pointAutoPlayAble="false"
                app:banner_pointContainerBackground="@android:color/transparent"
                app:banner_pointDrawable="@drawable/bga_banner_selector_point_hollow"
                app:banner_pointTopBottomMargin="15dp"
                app:banner_transitionEffect="fade"/>
        </com.abner.ming.abnerlibrary.viewpage.BGAGuideLinkageLayout>

    代码：

         mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
                    @Override
                    public void onClickEnterOrSkip() {
                        startActivity(new Intent(SplashActivity.this, GestureMainActivity.class));
                        finish();
                    }
        });

              // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
               BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
               // 设置数据源
               mBackgroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                       R.drawable.uoko_guide_background_1,
                       R.drawable.uoko_guide_background_2,
                       R.drawable.uoko_guide_background_3);
```
### 主页Tab设置：

```
    xml布局：

      <com.abner.ming.abnerlibrary.tabview.TabView
          android:id="@+id/tabview"
          android:layout_width="match_parent"
          android:layout_height="match_parent"/>

     代码：

                List<TabViewChild> tabViewChildList = new ArrayList<>();
                TabViewChild tabViewChild01 = new TabViewChild(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "首页", new HomeFragment());
                TabViewChild tabViewChild02 = new TabViewChild(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "自选", new HomeFragment());
                TabViewChild tabViewChild03 = new TabViewChild(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "我的", new HomeFragment());
                tabViewChildList.add(tabViewChild01);
                tabViewChildList.add(tabViewChild02);
                tabViewChildList.add(tabViewChild03);
                mTabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
                mTabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
                    @Override
                    public void onTabChildClick(int position, ImageView currentImageIcon, TextView currentTextView) {

                    }
                });
```

### 轮播图展示：

```
    xml布局展示：

            <com.abner.ming.abnerlibrary.viewpage.BGABanner
                android:id="@+id/bgabanner"
                android:layout_width="match_parent"
                android:layout_height="200dp"
                app:banner_transitionEffect="cube"
                />
     代码：

             BGABanner mBGABanner=(BGABanner)v.findViewById(R.id.bgabanner);
                    mBGABanner.setAutoPlayAble(true);

                    mBGABanner.setAdapter(this);

                    ArrayList<String> imageList=new ArrayList<>();
                    imageList.add("http://7xk9dj.com1.z0.glb.clouddn.com/banner/imgs/12.png");
                    imageList.add("http://7xk9dj.com1.z0.glb.clouddn.com/banner/imgs/13.png");
                    imageList.add("http://7xk9dj.com1.z0.glb.clouddn.com/banner/imgs/14.png");
                    ArrayList<String> textList=new ArrayList<>();
                    textList.add("111111111111111");
                    textList.add("222222222222222");
                    textList.add("333333333333333");
                    mBGABanner.setData(imageList, textList);

              @Override
                public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
            //        Glide.with(itemView.getContext())
            //                .load(model)
            //                .apply(new RequestOptions().placeholder(R.drawable.holder).error(R.drawable.holder).dontAnimate().centerCrop())
            //                .into(itemView);
                    Log.i("fillBannerItem",model.toString());
                    Picasso.with(getActivity()).load(model.toString()).into((ImageView)itemView);

                    Picasso.with(getActivity()).load(R.drawable.a_1).fit().into((ImageView) itemView);
                }
```

### 沉浸式状态：

```
            1.自定义颜色的状态栏和导航栏

                    在 onCreate() 方法中：
                                UltimateBar.newColorBuilder()
                                        .statusColor(statusColor)       // 状态栏颜色
                                        .statusDepth(50)                // 状态栏颜色深度
                                        .applyNav(true)                 // 是否应用到导航栏
                                        .navColor(navColor)             // 导航栏颜色
                                        .navDepth(50)                   // 导航栏颜色深度
                                        .build(this)
                                        .apply();

                    如果不需要设置颜色深度：

                                UltimateBar.newColorBuilder()
                                        .statusColor(statusColor)   // 状态栏颜色
                                        .applyNav(true)             // 是否应用到导航栏
                                        .navColor(navColor)         // 导航栏颜色
                                        .build(this)
                                        .apply();


            2.半透明状态栏和导航栏

                    在 onCreate() 方法中：

                                UltimateBar.newTransparentBuilder()
                                        .statusColor(Color.BLUE)        // 状态栏颜色
                                        .statusAlpha(100)               // 状态栏透明度
                                        .applyNav(true)                 // 是否应用到导航栏
                                        .navColor(Color.GREEN)          // 导航栏颜色
                                        .navAlpha(100)                  // 导航栏透明度
                                        .build(this)
                                        .apply();

                    如果仅需要设置状态栏的半透明效果：

                                UltimateBar.newTransparentBuilder()
                                        .statusColor(Color.BLUE)        // 状态栏颜色
                                        .statusAlpha(100)               // 状态栏透明度
                                        .applyNav(false)                // 是否应用到导航栏
                                        .build(this)
                                        .apply();

            3.沉浸式状态栏和导航栏：

                                在 onCreate() 方法中：

                                            UltimateBar.newImmersionBuilder()
                                                    .applyNav(true)         // 是否应用到导航栏
                                                    .build(this)
                                                    .apply();



            4.隐藏状态栏和导航栏：

            在 onWindowFocusChanged() 方法中：

                        @Override
                        public void onWindowFocusChanged(boolean hasFocus) {
                            super.onWindowFocusChanged(hasFocus);
                            if (hasFocus) {
                                UltimateBar.newHideBuilder()
                                        .applyNav(true)     // 是否应用到导航栏
                                        .build(this)
                                        .apply();
                            }
                        }


            5.在 DrawerLayout 中设置自定义颜色的状态栏和导航栏：

                    首先需要设置 DrawerLayout 下面的主局部中添加 android:fitsSystemWindows="true"：

                                <android.support.v4.widget.DrawerLayout
                                    xmlns:android="http://schemas.android.com/apk/res/android"
                                    android:id="@+id/drawer_layout"
                                    android:layout_width="match_parent"
                                    android:layout_height="match_parent"
                                    android:fitsSystemWindows="false">

                                    <LinearLayout
                                        android:layout_width="match_parent"
                                        android:layout_height="match_parent"
                                        android:orientation="vertical"
                                        android:fitsSystemWindows="true">

                                    </LinearLayout>

                                    <FrameLayout
                                        android:layout_width="match_parent"
                                        android:layout_height="match_parent"
                                        android:background="@color/SpringGreen"
                                        android:layout_gravity="left"
                                        android:fitsSystemWindows="false"/>

                                </android.support.v4.widget.DrawerLayout>
                    注意是 DrawerLayout 下面的主布局，DrawerLayout 本身和抽屉布局都不能添加。
                    然后在 onCreate() 方法中：

                                UltimateBar.newDrawerBuilder()
                                        .statusColor(color)     // 状态栏颜色
                                        .statusDepth(0)         // 状态栏颜色深度
                                        .applyNav(true)         // 是否应用到导航栏
                                        .navColor(color)        // 导航栏颜色
                                        .navDepth(0)            // 导航栏颜色深度
                                        .build(this)
                                        .apply();
```
### 手势解锁：

```

            具体代码可运行，打开GestureMainActivity进行查看.
```

### 如何运用百分比进行适配

```
            具体可看percent下四个类：

            <com.abner.ming.myappli.PercentRelativeLayout
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content">
                  <TextView
                      android:layout_width="wrap_content"
                      android:layout_height="wrap_content"
                      android:text="hello"
                      android:textColor="#000"
                      app:layout_marginLeftPercent="10%"
                      />
              </com.abner.ming.myappli.PercentRelativeLayout>

              具体属性有：

                    layout_widthPercent
                    layout_heightPercent
                    layout_marginPercent
                    layout_marginLeftPercent
                    layout_marginTopPercent
                    layout_marginRightPercent
                    layout_marginBottomPercent
                    layout_marginStartPercent
                    layout_marginEndPercent
                    layout_aspectRatio
```

### 各个工具类使用

```

            均在utils包下
```
### 注解使用

```
            在Activity中初始化ViewUtils.inject(this);，最好在父类中
            在Fragment中初始化ViewUtils.inject(getActivity(),view);

            控件注解：
                     @ViewInject(R.id.btn_demo1)
                     Button mButton;
            事件注解：
                    @OnClick(R.id.btn_demo1)
                    public void onClickDemo(View v){

           }
```

### 下拉刷新，上拉加载

```
    1、使用material包下MaterialRefreshLayout(使用于listview,recyclerview等各种layout)：

               <com.ming.abner.abnerming.layout.MaterialRefreshLayout
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    app:overlay="true"
                    app:isLoadMore="false"
                    app:progress_size_type="normal"
                    app:wave_color="#90ffffff"
                    app:wave_show="true"
                    app:progress_colors="@array/material_colors"
                    app:wave_height_type="normal"
                    android:id="@+id/materialrefreshlayout"
                    >

                    </com.ming.abner.abnerming.layout.MaterialRefreshLayout>


          mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                    @Override
                    public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {

                    }

                    @Override
                    public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                    }
                });

    2、使用recy下：

            a、LinearLayoutManager：

               <com.jcodecraeer.xrecyclerview.XRecyclerView
                    android:id="@+id/recyclerview"
                    app:layout_behavior="@string/appbar_scrolling_view_behavior"
                    android:layout_width="fill_parent"
                    android:layout_height="fill_parent" />


              mRecyclerView = (XRecyclerView)this.findViewById(R.id.recyclerview);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(layoutManager);

                    mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
                    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
                    mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

                    //添加头部
                    View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
                    mRecyclerView.addHeaderView(header);

                    mRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
                    mRecyclerView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");

                    final int itemLimit = 5;
                    // When the item number of the screen number is list.size-2,we call the onLoadMore
                    mRecyclerView.setLimitNumberToCallLoadMore(2);

                    mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
                        @Override
                        public void onRefresh() {
                        }

                        @Override
                        public void onLoadMore() {
                    });

               b、GridLayoutManager

                 GridLayoutManager layoutManager = new GridLayoutManager(this,3);
                    mRecyclerView.setLayoutManager(layoutManager);

                    mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
                    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
                    mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

                    mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
                        @Override
                        public void onRefresh() {

                        }

                        @Override
                        public void onLoadMore() {

                        }
                    });

                c.导航栏透明
                  mRecyclerView.setScrollAlphaChangeListener(new XRecyclerView.ScrollAlphaChangeListener() {
                            @Override
                            public void onAlphaChange(int alpha) {
                                alpha_title.getBackground().setAlpha(alpha);
                            }

                            @Override
                            public int setLimitHeight() {
                                return 1300;
                            }
                        });
```

### Retrofit上传头像
```
@Multipart
    @POST
    Observable<ResponseBody> upLoad(@Url String url,
                                    @HeaderMap Map<String, String> map,
                                    @Part MultipartBody.Part part);


 public HttpUtils upload(String url, File file) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.17.8.100")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder requestBuilder = request.newBuilder();
                        requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), request.body().toString()));
                        return chain.proceed(request);
                    }
                }).build())
                .build();

        //Bitmap.createScaledBitmap(Bitmap.CompressFormat.PNG,)
        HttpService service = retrofit.create(HttpService.class);
        MediaType mediaType = MediaType.parse("multipart/form-data;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), body);
        Observable<ResponseBody> ob = service.upLoad(url, headMap, part);
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            mHttpListener.success(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mHttpListener.fail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return this;
    }


```

### OKHTTP上传头像
```
//上传
    private void uoLoad() {
        String urlPath = "http://www.abnerming.cn/cert/upload_image.php";
        File file = new File(imagePath);
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(mediaType);//设置以表单的方式进行提交
        builder.addFormDataPart("file", "aaa.png", RequestBody.create(MediaType.parse("image/png"), file));
        builder.addFormDataPart("user_hidden", "abner59825");
        Request request = new Request.Builder().url(urlPath).post(builder.build()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
```
