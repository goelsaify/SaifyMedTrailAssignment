package com.saify.saifymedtrailassignment.repository.storage;


import androidx.annotation.NonNull;
import androidx.paging.DataSource;

public class DBRepoDataSourceFactory extends DataSource.Factory {

    private DBRepoPageDataSource dbRepoPageDataSource;

    public DBRepoDataSourceFactory(ImageDataDao dao, String query){
        dbRepoPageDataSource = new DBRepoPageDataSource(dao, query);
    }

    @NonNull
    @Override
    public DataSource create() {
        return dbRepoPageDataSource;
    }
}
