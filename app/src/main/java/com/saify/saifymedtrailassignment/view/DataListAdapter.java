package com.saify.saifymedtrailassignment.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saify.saifymedtrailassignment.R;
import com.saify.saifymedtrailassignment.model.ImageModel;

public class DataListAdapter  extends PagedListAdapter<ImageModel, DataListAdapter.DataViewHolder> {
    protected DataListAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_layout, parent, false);
        DataViewHolder viewHolder = new DataViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        ImageModel imageModel = getItem(position);

        String url = "https://live.staticflickr.com/"+ imageModel.getServer()+"/"+imageModel.getId()+"_"+imageModel.getSecret()+".jpg";

        Glide.with(holder.itemView.getContext())
                .load(url)
                .centerCrop()
                .into(holder.imageView);
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgViewSingleImage);
        }
    }

    /**
     * Implementing DiffUtil to check whether the current data is same or has changed
     */
    private static DiffUtil.ItemCallback<ImageModel> diffCallback = new DiffUtil.ItemCallback<ImageModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ImageModel oldItem, @NonNull ImageModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ImageModel oldItem, @NonNull ImageModel newItem) {
            return oldItem.equals(newItem);
        }
    };
}
