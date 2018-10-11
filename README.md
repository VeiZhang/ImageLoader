# 图片加载器

## 统一管理图片加载库，随意切换图片加载框架

* Fresco
* Picasso
* Glide
* Universal-ImageLoader
* Volley

## 封装

* 多个图片加载库切换
* 图片加载进度回调
* 自定义缓存目录、大小
* 清除缓存

## 使用

[![imageloader][icon_imageloader]][imageloader]

[![imageloader-fresco][icon_imageloader-fresco]][imageloader-fresco]

[![imageloader-picasso][icon_imageloader-picasso]][imageloader-picasso]

[![imageloader-glide][icon_imageloader-glide]][imageloader-glide]


* **独立依赖库**
    ```
    implementation 'com.excellence:imageloader:_latestVersion'
    // 下面图库三选一，减小安装包大小
    implementation 'com.excellence:imageloader-fresco:_latestVersion'
    implementation 'com.excellence:imageloader-picasso:_latestVersion'
    implementation 'com.excellence:imageloader-glide:_latestVersion'
    ```

* 权限
    ```
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    ```

* API
    ```
    // 初始化，不同的加载器，有部分独立的方法
    // 可以自定义实现ImageLoader接口，创建新的图库加载器
    ImageLoaderOptions options = new ImageLoaderOptions.Builder().isLogEnable(true).isCache(false).build();
    mImageLoader = new FrescoImageLoader(this, options);
    mImageLoader = new PicassoImageLoader(this, options);
    mImageLoader = new GlideImageLoader(this, options);

    // 统一的接口
    public interface ImageLoader
    {
        /**
         * 加载资源图片
         *
         * @param view
         * @param resId
         */
        void loadImage(@NonNull ImageView view, @DrawableRes int resId);

        void loadImage(@NonNull ImageView view, @DrawableRes int resId, IListener listener);

        /**
         * 加载资源图片，占位图片，错误图片
         *
         * @param view
         * @param resId
         * @param placeholderResId
         * @param errorResId
         */
        void loadImage(@NonNull ImageView view, @DrawableRes int resId, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

        void loadImage(@NonNull ImageView view, @DrawableRes int resId, @DrawableRes int placeholderResId, @DrawableRes int errorResId, IListener listener);

        /**
         * 加载本地图片
         *
         * @param view
         * @param file
         */
        void loadImage(@NonNull ImageView view, @NonNull File file);

        void loadImage(@NonNull ImageView view, @NonNull File file, IListener listener);

        /**
         * 加载本地图片，占位图片，错误图片
         *
         * @param view
         * @param file
         * @param placeholderResId
         * @param errorResId
         */
        void loadImage(@NonNull ImageView view, @NonNull File file, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

        void loadImage(@NonNull ImageView view, @NonNull File file, @DrawableRes int placeholderResId, @DrawableRes int errorResId, IListener listener);

        /**
         * 加载网络图片
         *
         * @param view
         * @param url
         */
        void loadImage(@NonNull ImageView view, @NonNull String url);

        void loadImage(@NonNull ImageView view, @NonNull String url, IListener listener);

        /**
         * 加载网络图片，占位图片，错误图片
         *
         * @param view
         * @param url
         * @param placeholderResId
         * @param errorResId
         */
        void loadImage(@NonNull ImageView view, @NonNull String url, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

        void loadImage(@NonNull ImageView view, @NonNull String url, @DrawableRes int placeholderResId, @DrawableRes int errorResId, IListener listener);

        void clearCache();
    }
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
implementation 'com.github.bumptech.glide:glide:4.8.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
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



## 感谢

> - [ladingwu][ladingwu]
> - [hpdx][hpdx]
> - [peng8350][peng8350]


<!-- 引用网站链接 -->

[imageloader]:https://bintray.com/veizhang/maven/imageloader/_latestVersion "imageloader"
[imageloader-fresco]:https://bintray.com/veizhang/maven/imageloader-fresco/_latestVersion "imageloader-fresco"
[imageloader-picasso]:https://bintray.com/veizhang/maven/imageloader-picasso/_latestVersion "imageloader-picasso"
[imageloader-glide]:https://bintray.com/veizhang/maven/imageloader-glide/_latestVersion "imageloader-glide"
[ladingwu]:https://github.com/ladingwu/ImageLoaderFramework
[hpdx]:https://github.com/hpdx/fresco-helper
[peng8350]:https://github.com/peng8350/LoadingProgress

<!-- 图片链接 -->

[icon_imageloader]:https://api.bintray.com/packages/veizhang/maven/imageloader/images/download.svg
[icon_imageloader-fresco]:https://api.bintray.com/packages/veizhang/maven/imageloader-fresco/images/download.svg
[icon_imageloader-picasso]:https://api.bintray.com/packages/veizhang/maven/imageloader-picasso/images/download.svg
[icon_imageloader-glide]:https://api.bintray.com/packages/veizhang/maven/imageloader-glide/images/download.svg


<!--

1.options也选用策略模式

2.创建图片选择器

-->