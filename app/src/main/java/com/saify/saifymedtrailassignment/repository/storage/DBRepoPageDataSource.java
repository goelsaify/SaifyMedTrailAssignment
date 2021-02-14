package com.saify.saifymedtrailassignment.repository.storage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.saify.saifymedtrailassignment.model.ImageModel;

import java.util.List;

public class DBRepoPageDataSource extends PageKeyedDataSource<Integer, ImageModel> {

    public static final String TAG = DBRepoPageDataSource.class.getSimpleName();

    private final ImageDataDao imageDataDao;
    private String searchQuery;

    public DBRepoPageDataSource(ImageDataDao imageDataDao, String searchQuery) {
        this.imageDataDao = imageDataDao;
        this.searchQuery = searchQuery;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ImageModel> callback) {
        Log.d(TAG, "Loading data from Database: "+ params.requestedLoadSize);
        List<ImageModel> localRepos = imageDataDao.getStoredRepos("%" + searchQuery +"%");
        if(localRepos.size()!=0){
            callback.onResult(localRepos,0,1);
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ImageModel> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ImageModel> callback) {

    }
}
