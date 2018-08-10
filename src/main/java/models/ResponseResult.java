package models;

import com.google.gson.annotations.SerializedName;


public class ResponseResult {
    @SerializedName("result")
    public Result result;


    public class Result {

        @SerializedName("search_result")
        public SearchResult searchResult;
    }
}
