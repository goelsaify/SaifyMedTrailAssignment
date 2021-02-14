package com.saify.saifymedtrailassignment.repository.network;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.saify.saifymedtrailassignment.model.ImageModel;
import com.saify.saifymedtrailassignment.repository.network.paging.NetReposDataSourceFactory;
import com.saify.saifymedtrailassignment.util.Constants;


public class NetworkRepos {

    final private static String TAG = NetworkRepos.class.getSimpleName();
    final private LiveData<PagedList<ImageModel>> reposList;

    public NetworkRepos(NetReposDataSourceFactory dataSourceFactory, PagedList.BoundaryCallback<ImageModel> boundaryCallback) {

        //Prepare paging config for loading limited data over internet

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE).setPageSize(Constants.PAGE_SIZE).build();

        Log.d(TAG, "Initialize Network Data Source");
        reposList = new LivePagedListBuilder<Integer, ImageModel>(dataSourceFactory, pagedListConfig).setBoundaryCallback(boundaryCallback).build();

    }

    public LiveData<PagedList<ImageModel>> getPagedData() {
        return reposList;
    }


}
