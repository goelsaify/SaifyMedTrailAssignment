package com.saify.saifymedtrailassignment.model;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "image_data")
public class ImageModel {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("owner")
    @Expose
    private String owner;

    @SerializedName("secret")
    @Expose
    private String secret;

    @SerializedName("server")
    @Expose
    private String server;

    @SerializedName("farm")
    @Expose
    private Integer farm;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("ispublic")
    @Expose
    private Integer ispublic;

    @SerializedName("isfriend")
    @Expose
    private Integer isfriend;

    @SerializedName("isfamily")
    @Expose
    private Integer isfamily;

    @SerializedName("text")
    @Expose
    private String text;

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public Integer getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public Integer getIsfriend() {
        return isfriend;
    }

    public Integer getIsfamily() {
        return isfamily;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }
}
