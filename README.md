# TransparentStatusBarSample

https://medium.com/@manishgiri/android-transparent-status-bar-part-1-989e16b11785

# Overview

# Transparent Status Bar with Navigation Drawer and Coordinator Layout

## With Navigation Drawer

* Khi làm việc với giao diện của Navigation Drawer, thông thường sẽ cần phải làm trong suốt thanh status bar để nhìn thấy giao diện được đồng bộ hơn.

* Sử dụng attribute **android:fitsSystemWindows=”true”** đặt trong xml để làm cho thanh status bar được trong suốt với phần nội dung hiển thị của view được chen vào đó.

```
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawerLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start">

    <include
        layout="@layout/app_bar_nav"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <android.support.design.widget.NavigationView
        android:id="@+id/navigationView"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/nav_menu" />

</android.support.v4.widget.DrawerLayout>
```

* Sử dụng thêm style để setup cho Activity một thuộc tính **android:windowDrawsSystemBarBackgrounds** thành true. Thuộc tính này cho biết liệu cửa sổ này có chịu trách nhiệm vẽ background cho thanh system bar hay không. Nếu giá trị là true và của sổ không đổi, các thanh hệ thống sẽ được vẽ với nền trong suốt và cá khu vực tương ứng sẽ được lấp đầy bằng các màu được chỉ định trong **android.R.attr#statusBarColor** và **android.R.attr#navigationBarColor**. 

```
<style name="AppTheme.TransparentTheme">
        <item name="android:windowDrawsSystemBarBackgrounds">true</item>
        <item name="android:statusBarColor">@android:color/transparent</item>
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>

    <style name="AppTheme.PopupOverlay" parent="ThemeOverlay.AppCompat.Light" />

    <style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar" />
```

* Thông thường khi sử dụng transparent, phần status bar sẽ có phần làm mờ và không được hoàn toàn hiển thị background bên dưới.

![](https://i.stack.imgur.com/Nz7G2.jpg)

* Để xử lý vấn đề này, sử dụng FLAG **FLAG_LAYOUT_NO_LIMITS** ở window của activity.

```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    activity.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}
```

* Cũng có thể thay đổi màu của các icon ở trên status bar bằng cách sử dụng **View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR**.

```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}

// Or add in to style
<item name="android:windowLightStatusBar">true</item>
```

* Thay đổi background của status bar sử dụng phương thức **setStatusBarColor()**.

```
activity.window.statusBarColor = Color.YELLOW
```


## With CollapsingToolbar

* Collapsing Toolbar là một giao diện mới được miêu tả như hình dưới đây:

![](http://arnaud-camus.fr/assets/media/collapsing_header_animated.gif)

* Cũng như việc sử dụng với Navigation Drawer, Collapsing Toolbar cũng phải sử dụng thuộc tính **android:fitsSystemWindows="true"** cho AppBarLayout.

```
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    ...
    android:fitsSystemWindows="true">

    <android.support.design.widget.AppBarLayout
        ...
        android:fitsSystemWindows="true">

        <android.support.design.widget.CollapsingToolbarLayout
            ...
            android:background="@android:color/transparent"
            android:fitsSystemWindows="true"
            app:contentScrim="?attr/colorPrimary"
            app:expandedTitleMarginEnd="64dp"
            app:expandedTitleMarginStart="48dp"
            app:layout_scrollFlags="scroll|exitUntilCollapsed"
            app:statusBarScrim="@android:color/transparent">

            <android.support.constraint.ConstraintLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:fitsSystemWindows="true">

                ...
            </android.support.constraint.ConstraintLayout>

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin" />
        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>

    <android.support.v4.widget.NestedScrollView
        ...
        app:layout_behavior="@string/appbar_scrolling_view_behavior">
        
        ...
        </android.support.constraint.ConstraintLayout>
    </android.support.v4.widget.NestedScrollView>
</android.support.design.widget.CoordinatorLayout>
```

* Như trên ví dụ, chúng ta phải set đồng thời thuộc tính **android:fitsSystemWindows="true"** cho các container và appbar layout, collapsing toolbar để có thể sử dụng vùng hiển thị của status bar.
