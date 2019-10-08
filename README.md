## android 权限请求助手

借助了 lifecycle 的思想，

在activity/fragment 中插入一个不可见的fragment。

利用这个不可见的fragment来实现请求,无需使用者重写 onRequestPermissionsResult

所以使用起来及其容易

```java
GPR.request(this)
    .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
    .setCallback(new ReqCallback() {
        @Override
        public void callback(boolean isGet) {
            Log.e("MainActivity", "callback:" + isGet);
        }
    })
    .req();
```