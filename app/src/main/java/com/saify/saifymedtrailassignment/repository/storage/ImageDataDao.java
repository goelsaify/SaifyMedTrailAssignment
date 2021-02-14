package com.saify.saifymedtrailassignment.repository.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.saify.saifymedtrailassignment.model.ImageModel;

import java.util.List;

@Dao
public interface ImageDataDao {

    //Replacing older data with the new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepo(ImageModel resultModel);

    //Basic Query to fetch Data
    @Query("SELECT * from image_data where text like :queryString ORDER BY id ASC")
    List<ImageModel> getStoredRepos(String queryString);

}
