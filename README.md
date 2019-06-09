# baseabner
## 一个方便大家的库
### 初始化
```
	下载好之后，可以直接依赖这个库，需要注意的是,库里使用到了数据库，所以需要一些配置：
	在跟项目的build里添加以下：
	
	在  repositories 里添加  mavenCentral()
	
	在   dependencies 添加：
	lasspath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add plugin

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

