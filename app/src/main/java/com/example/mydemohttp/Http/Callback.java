package com.example.mydemohttp.Http;

//网络请求回调接口
public interface Callback {
    void onResponse(String response);//请求正确
    void onFailed(Throwable throwable);//请求错误
}
