package com.saify.saifymedtrailassignment.repository.network.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.saify.saifymedtrailassignment.model.ImageModel;

import rx.subjects.ReplaySubject;

public class NetReposDataSourceFactory extends DataSource.Factory{

    private MutableLiveData<NetReposPageDataSource> mutableLiveData;
    private NetReposPageDataSource netReposPageDataSource;

    public NetReposDataSourceFactory(String query){
        this.mutableLiveData = new MutableLiveData<>();
        netReposPageDataSource = new NetReposPageDataSource(query);
    }

    @NonNull
    @Override
    public DataSource create() {
        mutableLiveData.postValue(netReposPageDataSource);
        return netReposPageDataSource;
    }

    public ReplaySubject<ImageModel> getRepos(){return netReposPageDataSource.getRepoObservable();}
}
