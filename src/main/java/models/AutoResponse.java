package models;

import com.google.gson.annotations.SerializedName;

public class AutoResponse {

    @SerializedName("autoData")
    public AutoData autoData;

    public class AutoData{

        @SerializedName("categoryId")
        public int categoryId;

        @SerializedName("bodyId")
        public int bodyId;

        @SerializedName("year")
        public int year;

        @SerializedName("gearboxName")
        public String gearboxName;

        @SerializedName("raceInt")
        public int raceInt;
    }

    @SerializedName("markId")
    public int markId;

    @SerializedName("modelId")
    public int modelId;

    public String checkMapGearBox(){
        String result = new String();
        switch (autoData.gearboxName) {
            case "Автомат" : result = "2";
            break;
            case "Ручная / Механика" : result = "1";
            break;
        }
        return result;
    }
}
