package com.saify.saifymedtrailassignment.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;

import com.saify.saifymedtrailassignment.model.ImageModel;
import com.saify.saifymedtrailassignment.repository.network.NetworkRepos;
import com.saify.saifymedtrailassignment.repository.network.paging.NetReposDataSourceFactory;
import com.saify.saifymedtrailassignment.repository.storage.ImageDataRoomDataBase;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class DataRepository {

    private static final String TAG = DataRepository.class.getSimpleName();
    final private NetworkRepos network;
    final private ImageDataRoomDataBase database;
    MediatorLiveData liveDataMerger;

    private DataRepository(Context context, String search_query){
        NetReposDataSourceFactory dataSourceFactory = new NetReposDataSourceFactory(search_query);

        network = new NetworkRepos(dataSourceFactory, boundaryCallback);
        database = ImageDataRoomDataBase.getInstance(context.getApplicationContext(),search_query);


        liveDataMerger = new MediatorLiveData();
        liveDataMerger.addSource(network.getPagedData(), value->{
            liveDataMerger.setValue(value);
            Log.d(TAG, value.toString());
        });

        //Save Data in DB
        dataSourceFactory.getRepos().observeOn(
                Schedulers.io()).subscribe(repo->{
                    database.dataDao().insertRepo(repo);
                });
    }

    private PagedList.BoundaryCallback<ImageModel> boundaryCallback = new PagedList.BoundaryCallback<ImageModel>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            liveDataMerger.addSource(database.getRepos(), value->{
                liveDataMerger.setValue(value);
                liveDataMerger.removeSource(database.getRepos());
            });
        }
    };

    public static DataRepository getRepository(Context context, String search_query){
        return new DataRepository(context, search_query);
    }

    public LiveData<PagedList<ImageModel>> getImagesData(){
        return liveDataMerger;
    }
}
