package com.smyy.sharetour.buyer.network.rx;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 动态url的通用Http请求接口
 */
public interface BaseApiService {

    @GET
    Flowable<Response<ResponseBody>> executeGet(@Url String url);
    @GET
    Flowable<Response<ResponseBody>> executeGet(@Url String url, @QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Flowable<Response<ResponseBody>> executePost(@Url String url, @FieldMap Map<String, String> params);

    @POST
    Flowable<Response<ResponseBody>> executePostJson(@Url String url, @Body RequestBody jsonStr);

    @GET
    Flowable<Response<ResponseBody>> executeGet(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST
    Flowable<Response<ResponseBody>> executePost(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @POST
    Flowable<Response<ResponseBody>> executePostJson(@Url String url, @Body RequestBody jsonStr, @HeaderMap Map<String, String> headers);

    @POST
    Flowable<Response<ResponseBody>> executePostNoParams(@Url String url, @HeaderMap Map<String, String> headers);

    @POST
    Flowable<Response<ResponseBody>> executePostNoParams(@Url String url);

    @DELETE
    Flowable<Response<ResponseBody>> executeDeleteNoParams(@Url String url, @HeaderMap Map<String, String> headers);

    @DELETE
    Flowable<Response<ResponseBody>> executeDelete(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @DELETE
    Flowable<Response<ResponseBody>> executeDeleteNoParams(@Url String url);

    @Multipart
    @POST()
    Flowable<Response<ResponseBody>> upLoadFile(@Url String url, @Part() RequestBody requestBody, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @Multipart
    @POST()
    Flowable<Response<ResponseBody>> upLoadFile(@Url String url, @Part() RequestBody requestBody);
    @POST()
    Flowable<Response<ResponseBody>> uploadFiles(@Url String url, @Path("headers") Map<String, String> headers, @Part("filename") String description, @PartMap Map<String, RequestBody> maps);

    @Streaming
    @GET
    Flowable<Response<ResponseBody>> downloadFile(@Url String fileUrl);
    @Streaming
    @GET
    Flowable<Response<ResponseBody>> downloadFile(@Url String fileUrl, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @POST
    Call<ResponseBody> postNoParams(@Url String url, @HeaderMap Map<String, String> headers);

}
