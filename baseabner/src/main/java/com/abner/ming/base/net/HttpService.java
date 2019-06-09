package com.abner.ming.base.net;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * author:AbnerMing
 * date:2019/4/18
 */
public interface HttpService {

    @GET
    Observable<ResponseBody> get(@Url String url,
                                 @HeaderMap Map<String, String> headMap,
                                 @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url,
                                  @HeaderMap Map<String, String> headMap,
                                  @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @PUT
    Observable<ResponseBody> put(@Url String url,
                                 @HeaderMap Map<String, String> headMap,
                                 @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @DELETE
    Observable<ResponseBody> delete(@Url String url,
                                    @HeaderMap Map<String, String> headMap,
                                    @FieldMap Map<String, String> map);
}
