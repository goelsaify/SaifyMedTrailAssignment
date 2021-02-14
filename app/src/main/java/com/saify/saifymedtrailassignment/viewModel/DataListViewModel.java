package com.saify.saifymedtrailassignment.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.saify.saifymedtrailassignment.model.ImageModel;
import com.saify.saifymedtrailassignment.repository.DataRepository;

public class DataListViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    public DataListViewModel(@NonNull Fragment mFragment, String search_query) {
        super(mFragment.getActivity().getApplication());

        dataRepository = DataRepository.getRepository(mFragment.getActivity(),search_query);
    }

    public LiveData<PagedList<ImageModel>> getImagesData(){
        return dataRepository.getImagesData();
    }
}
