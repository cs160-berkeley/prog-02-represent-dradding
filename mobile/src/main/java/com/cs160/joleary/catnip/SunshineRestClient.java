//package com.cs160.joleary.catnip;
////import com.loopj.android.http.*;
//
///**
// * Created by dradding on 3/9/16.
// */
//public class SunshineRestClient {
//    private static final String BASE_URL = "congress.api.sunlightfoundation.com/";
//
//    private static AsyncHttpClient client = new AsyncHttpClient();
//
//    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        client.get(getAbsoluteUrl(url), params, responseHandler);
//    }
//
//    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        client.post(getAbsoluteUrl(url), params, responseHandler);
//    }
//
//    private static String getAbsoluteUrl(String relativeUrl) {
//        return BASE_URL + relativeUrl;
//    }
//}