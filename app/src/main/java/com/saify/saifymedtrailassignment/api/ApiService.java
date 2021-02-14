package com.saify.saifymedtrailassignment.api;

import com.saify.saifymedtrailassignment.model.ImageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


@GET("rest/")
    Call<String> getImageDetail(@Query("method") String method,
                                          @Query("api_key") String api_key,
                                          @Query("format") String format,
                                          @Query("nojsoncallback") String nojsoncallback,
                                          @Query("page") int page,
                                          @Query("per_page") int per_page,
                                          @Query("text") String text);

}
