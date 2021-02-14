package com.saify.saifymedtrailassignment.repository.network.paging;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.google.gson.Gson;
import com.saify.saifymedtrailassignment.api.RetrofitClient;
import com.saify.saifymedtrailassignment.model.ImageModel;
import com.saify.saifymedtrailassignment.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.ReplaySubject;

public class NetReposPageDataSource extends PageKeyedDataSource<Integer, ImageModel> {

    private static final String TAG = NetReposPageDataSource.class.getSimpleName();
    private final ReplaySubject<ImageModel> repoObservable;
    private String search_query = "";

    public NetReposPageDataSource(String search_query) {
        repoObservable = ReplaySubject.create();
        this.search_query = search_query;
    }

    public ReplaySubject<ImageModel> getRepoObservable(){
        return repoObservable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ImageModel> callback) {
        Log.i(TAG, "Loading Data size"+params.requestedLoadSize);
        Call<String> call = RetrofitClient.getInstance().getApi().getImageDetail("flickr.photos.getRecent",
                "3189212285dcb4cf5b2f044edcb0544e",
                "json",
                "1",
                Constants.FIRST_PAGE,
                Constants.PAGE_SIZE,
                search_query);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                List<ImageModel> final_result_list = jsonParseData(response.body());
                callback.onResult(final_result_list,1,2);

                for (int i = 0; i < final_result_list.size(); i++)
                    repoObservable.onNext(final_result_list.get(i));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String errorMessage;
                if(t.getMessage() == null){
                    errorMessage = "unknown error";
                }else{
                    errorMessage = t.getMessage();
                }

                Log.e("API Call",errorMessage);
                callback.onResult(new ArrayList(),1,2);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, final @NonNull LoadCallback<Integer, ImageModel> callback) {
        Log.i(TAG, "Loading Data size"+params.key);
        Call<String> call = RetrofitClient.getInstance().getApi().getImageDetail("flickr.photos.getRecent",
                "3189212285dcb4cf5b2f044edcb0544e",
                "json",
                "1",
                Constants.FIRST_PAGE,
                Constants.PAGE_SIZE,
                search_query);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                List<ImageModel> final_result_list = jsonParseData(response.body());
                callback.onResult(final_result_list, params.key+1);

                for (int i = 0; i < final_result_list.size(); i++)
                    repoObservable.onNext(final_result_list.get(i));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String errorMessage;
                if(t.getMessage() == null){
                    errorMessage = "unknown error";
                }else{
                    errorMessage = t.getMessage();
                }

                Log.e("API Call",errorMessage);
                callback.onResult(new ArrayList(),params.key);

            }
        });
    }

    private List<ImageModel> jsonParseData(String response){
        List<ImageModel> result_list = new ArrayList<>();

        if(TextUtils.isEmpty(response)){
            return result_list;
        }

        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONObject photosJsonObject = jsonObject.getJSONObject("photos");

            JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

            for(int i=0;i<photoJsonArray.length();i++){
                Gson gson = new Gson();
                ImageModel imageModel = gson.fromJson(photoJsonArray.getString(i),ImageModel.class);
                if(imageModel.getId().equals("0") || imageModel.getServer().equals("0"))
                    continue;
                imageModel.setText(search_query);

                result_list.add(imageModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(getClass().getSimpleName(), String.valueOf(result_list.size()));
        return result_list;
    }
}
