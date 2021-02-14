package com.saify.saifymedtrailassignment.repository.storage;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.saify.saifymedtrailassignment.model.ImageModel;
import com.saify.saifymedtrailassignment.util.Constants;

@Database(entities = {ImageModel.class}, version =1)
public abstract class ImageDataRoomDataBase extends RoomDatabase {

    public abstract ImageDataDao dataDao();

    private static final Object lock = new Object();
    private LiveData<PagedList<ImageModel>> reposData;

    public static ImageDataRoomDataBase getInstance(Context context, String search_query){
        synchronized (lock){
            ImageDataRoomDataBase instance = Room.databaseBuilder(context,ImageDataRoomDataBase.class, Constants.DATABASE).build();
            instance.init(search_query);

            return instance;
        }
    }
    private void init(String search_query){
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build();
        DBRepoDataSourceFactory dataSourceFactory = new DBRepoDataSourceFactory(dataDao(), search_query);
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory,pagedListConfig);
        reposData = livePagedListBuilder.build();
    }

    public LiveData<PagedList<ImageModel>> getRepos(){
        return reposData;
    }
}
