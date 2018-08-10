package models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("ids")
    public List<String> idsCar;

    @SerializedName("count")
    public String countCar;

}
