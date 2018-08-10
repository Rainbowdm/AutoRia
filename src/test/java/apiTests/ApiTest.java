package apiTests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import models.AutoResponse;
import models.ResponseResult;
import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ApiTest {

    private Logger logger = Logger.getLogger(getClass());
    private String endPointSearch = "https://developers.ria.com/auto/search";
    private String endPointInfo = "https://developers.ria.com/auto/info";

    List<String> listIdMazda = new ArrayList<>();
    List<String> listIdToyota = new ArrayList<>();
    List<String> listIdHonda = new ArrayList<>();

    Map<String, String> paramMazda;
    Map<String, String> paramToyota;
    Map<String, String> paramHonda;


    @Rule
    public JUnitSoftAssertions soft = new JUnitSoftAssertions();

    @Before
    public void setUp() {
        searchAutoMazda6();
        searchAutoToyotaCamry();
        searchAutoHondaCivic();
    }

    @After
    public void tearDown() {

    }

    public void searchAutoMazda6() {

        Gson gson = new Gson();

        paramMazda = new HashMap<>();
        paramMazda.put("api_key", "C7bOrwd9Ow9RZxXjnGFjJcEX30qvSdVN7dlyozsN");
        paramMazda.put("category_id", "1");
        paramMazda.put("bodystyle[0]", "3");
        paramMazda.put("marka_id[0]", "47");
        paramMazda.put("model_id[0]", "393");
        paramMazda.put("s_yers[0]", "2017");
        paramMazda.put("po_yers[0]", "2017");
        paramMazda.put("gearbox[0]", "2");
        paramMazda.put("raceInt", "1");

        Response response = given()
                .contentType("application/json")
                .params(paramMazda)
                .when()
                .get(endPointSearch)
                .then()
                .time(lessThan(1000L))
                .assertThat().statusCode(200)
                .extract().response();

        ResponseResult resultApi = gson.fromJson(response.asString(), ResponseResult.class);
        listIdMazda = resultApi.result.searchResult.idsCar;
        String countCar = resultApi.result.searchResult.countCar;
        soft.assertThat(countCar)
                .isEqualTo("1");
        logger.info("\n" + listIdMazda + "\n" + countCar);
    }


    public void searchAutoToyotaCamry() {

        Gson gson = new GsonBuilder().create();

        paramToyota = new HashMap<>();
        paramToyota.put("api_key", "C7bOrwd9Ow9RZxXjnGFjJcEX30qvSdVN7dlyozsN");
        paramToyota.put("category_id", "1");
        paramToyota.put("bodystyle[0]", "3");
        paramToyota.put("marka_id[0]", "79");
        paramToyota.put("model_id[0]", "698");
        paramToyota.put("s_yers[0]", "2017");
        paramToyota.put("po_yers[0]", "2017");
        paramToyota.put("gearbox[0]", "1");
        paramToyota.put("raceInt", "1");

        Response response = given()
                .contentType("application/json")
                .params(paramToyota)
                .when()
                .get(endPointSearch)
                .then()
                .time(lessThan(1000L))
                .assertThat().statusCode(200)
                .extract().response();

        ResponseResult resultApi = gson.fromJson(response.asString(), ResponseResult.class);
        listIdToyota = resultApi.result.searchResult.idsCar;
        String countCar = resultApi.result.searchResult.countCar;
        soft.assertThat(countCar)
                .isEqualTo("0");
        logger.info("\n" + listIdToyota + "\n" + countCar);
    }

    public void searchAutoHondaCivic() {

        Gson gson = new GsonBuilder().create();

        paramHonda = new HashMap<>();
        paramHonda.put("api_key", "C7bOrwd9Ow9RZxXjnGFjJcEX30qvSdVN7dlyozsN");
        paramHonda.put("category_id", "1");
        paramHonda.put("bodystyle[0]", "3");
        paramHonda.put("marka_id[0]", "28");
        paramHonda.put("model_id[0]", "265");
        paramHonda.put("s_yers[0]", "2017");
        paramHonda.put("po_yers[0]", "2017");
        paramHonda.put("gearbox[0]", "1");
        paramHonda.put("raceInt", "1");

        Response response = given()
                .contentType("application/json")
                .params(paramHonda)
                .when()
                .get(endPointSearch)
                .then()
                .time(lessThan(1000L))
                .assertThat().statusCode(200)
                .extract().response();

        ResponseResult resultApi = gson.fromJson(response.asString(), ResponseResult.class);
        listIdHonda = resultApi.result.searchResult.idsCar;
        String countCar = resultApi.result.searchResult.countCar;
        soft.assertThat(countCar)
                .isEqualTo("0");
        logger.info("\n" + listIdHonda + "\n" + countCar);
    }

    @Test
    public void getInfoAuto() {

        Gson gson = new GsonBuilder().create();

        Map<String, String> param = new HashMap<>();
        param.put("api_key", "C7bOrwd9Ow9RZxXjnGFjJcEX30qvSdVN7dlyozsN");
        param.put("auto_id", listIdMazda.get(0));

        Response response = given()
                .contentType("application/json")
                .params(param)
                .when()
                .get(endPointInfo)
                .then()
                .time(lessThan(1000L))
                .assertThat().statusCode(200)
                .extract().response();

        AutoResponse autoResponse = gson.fromJson(response.asString(), AutoResponse.class);

        String categoryId = String.valueOf(autoResponse.autoData.categoryId);
        String bodyId = String.valueOf(autoResponse.autoData.bodyId);
        String markId = String.valueOf(autoResponse.markId);
        String modelId = String.valueOf(autoResponse.modelId);
        String year = String.valueOf(autoResponse.autoData.year);
        //String gearboxNameString = autoResponse.autoData.gearboxName;
        String raceInt = String.valueOf(autoResponse.autoData.raceInt);
        boolean isFieldsValid = false;

        if (categoryId.equals(paramMazda.get("category_id"))
                && bodyId.equals(paramMazda.get("bodystyle[0]"))
                && markId.equals(paramMazda.get("marka_id[0]"))
                && modelId.equals(paramMazda.get("model_id[0]"))
                && year.equals(paramMazda.get("s_yers[0]"))
                && year.equals(paramMazda.get("po_yers[0]"))
                && autoResponse.checkMapGearBox().equals(paramMazda.get("gearbox[0]"))
                && raceInt.equals(paramMazda.get("raceInt"))) {
            isFieldsValid = true;
        }
        Assert.assertTrue(isFieldsValid);
    }
}
