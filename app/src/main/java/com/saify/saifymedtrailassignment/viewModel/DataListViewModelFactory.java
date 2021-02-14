package com.saify.saifymedtrailassignment.viewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DataListViewModelFactory implements ViewModelProvider.Factory {

    private Fragment mFragment;
    private String searchedQuery;

    public DataListViewModelFactory(Fragment mFragment, String searchedQuery){
        this.mFragment = mFragment;
        this.searchedQuery = searchedQuery;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DataListViewModel(mFragment, searchedQuery);
    }
}
