# 图片加载器

封装统一的图片加载架构，随意切换加载框架

* Fresco
* Picasso
* Glide
* Universal-ImageLoader
* Volley

* 多个图片加载库切换
* 图片加载进度回调
* 自定义缓存目录、大小
* 清除缓存

权限

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

## [Fresco](https://github.com/facebook/fresco/)

```
implementation 'com.facebook.fresco:fresco:1.9.0'

// 在 API < 14 上的机器支持 WebP 时，需要添加
compile 'com.facebook.fresco:animated-base-support:0.12.0'

// 支持 GIF 动图，需要添加
compile 'com.facebook.fresco:animated-gif:0.12.0'

// 支持 WebP （静态图+动图），需要添加
compile 'com.facebook.fresco:animated-webp:0.12.0'
compile 'com.facebook.fresco:webpsupport:0.12.0'

// 仅支持 WebP 静态图，需要添加
compile 'com.facebook.fresco:webpsupport:0.12.0'
```

```
Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
draweeView.setImageURI(uri);
```


## [Picasso](https://github.com/square/picasso)

```
implementation 'com.squareup.picasso:picasso:2.71828'
```

```
Picasso.get()
  .load(url)
  .resize(50, 50)
  .centerCrop()
  .into(imageView)
```

缓存路径:`data/data/your package name/cache/picasso-cache/(默认路径)`

## [Glide](https://github.com/bumptech/glide/)

```
dependencies {
  implementation 'com.github.bumptech.glide:glide:4.7.1'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
}
```

```
Glide.with(getContext())
                .load(url)
                .skipMemoryCache(true)
                .placeholder(drawable)
                .centerCrop()
                .animate(animator)
                .into(img);
```

## [Universal-ImageLoader](https://github.com/nostra13/Android-Universal-Image-Loader)



## [Volley](https://github.com/google/volley/)


