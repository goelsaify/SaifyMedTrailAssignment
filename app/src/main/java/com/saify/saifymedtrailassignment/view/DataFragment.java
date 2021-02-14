package com.saify.saifymedtrailassignment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.saify.saifymedtrailassignment.R;
import com.saify.saifymedtrailassignment.viewModel.DataListViewModel;
import com.saify.saifymedtrailassignment.viewModel.DataListViewModelFactory;

public class DataFragment  extends Fragment {

    private static final String TAG = DataFragment.class.getSimpleName();

    protected DataListViewModel viewModel;

    protected RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModelStore().clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_view,container,false);
        recyclerView = view.findViewById(R.id.recyclerViewImages);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        String search_query = getArguments().getString("search");
        getViewModelStore().clear();

        viewModel = ViewModelProviders.of(getActivity(), new DataListViewModelFactory(this,search_query)).get(DataListViewModel.class);

        DataListAdapter dataListAdapter = new DataListAdapter();
        viewModel.getImagesData().observe(getViewLifecycleOwner(), dataListAdapter::submitList);
        recyclerView.setAdapter(dataListAdapter);

        return view;
    }
}
