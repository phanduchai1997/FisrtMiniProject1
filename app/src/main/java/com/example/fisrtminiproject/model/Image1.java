package com.example.fisrtminiproject.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Image1 {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("promoted_at")
        @Expose
        private String promotedAt;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("alt_description")
        @Expose
        private String altDescription;
        @SerializedName("urls")
        @Expose
        private Urls urls;
        @SerializedName("categories")
        @Expose
        private List<Object> categories = null;
        @SerializedName("likes")
        @Expose
        private Integer likes;
        @SerializedName("liked_by_user")
        @Expose
        private Boolean likedByUser;
        @SerializedName("current_user_collections")
        @Expose
        private List<Object> currentUserCollections = null;

        public Result(Urls urls) {
            this.urls = urls;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getPromotedAt() {
            return promotedAt;
        }

        public void setPromotedAt(String promotedAt) {
            this.promotedAt = promotedAt;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAltDescription() {
            return altDescription;
        }

        public void setAltDescription(String altDescription) {
            this.altDescription = altDescription;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }


        public List<Object> getCategories() {
            return categories;
        }

        public void setCategories(List<Object> categories) {
            this.categories = categories;
        }

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public Boolean getLikedByUser() {
            return likedByUser;
        }

        public void setLikedByUser(Boolean likedByUser) {
            this.likedByUser = likedByUser;
        }

        public List<Object> getCurrentUserCollections() {
            return currentUserCollections;
        }

        public void setCurrentUserCollections(List<Object> currentUserCollections) {
            this.currentUserCollections = currentUserCollections;
        }

    }


}








