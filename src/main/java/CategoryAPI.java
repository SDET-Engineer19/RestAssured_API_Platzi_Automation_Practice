import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class CategoryAPI {

    public static void main(String[] args) {

        // test data:

        String create_category_endpoint_url = "https://api.escuelajs.co/api/v1/categories/";
        String get_category_endpoint_url = "https://api.escuelajs.co/api/v1/categories/";
        String update_category_endpoint_url = "https://api.escuelajs.co/api/v1/categories/";
        String delete_category_endpoint_url = "https://api.escuelajs.co/api/v1/categories/";

        String create_category_json_body = "{\n" +
                "  \"name\": \"Mobile Phones\",\n" +
                "  \"image\": \"https://placeimg.com/6409/4800/any\"\n" +
                "}";
        String update_category_json_body = "{\n" +
                "  \"name\": \"IOS Mobile Phones\",\n" +
                "  \"image\": \"https://placeimg.com/6409/4800/any\"\n" +
                "} ";

        Response create_category_response = createCategory(create_category_endpoint_url,create_category_json_body);
        System.out.println("##### CREATE CATEGORY RESPONSE #############");
        int category_id = create_category_response.jsonPath().getInt("id");
        String category_name = create_category_response.jsonPath().getString("name");
        create_category_response.prettyPrint();
        System.out.println("Status of Category : "+create_category_response.statusCode());
        System.out.println(" Category Name : "+category_name);
        System.out.println("Category ID : "+category_id);
        System.out.println("###########################################");

        System.out.println("##### GET CATEGORY RESPONSE #############");
        Response get_category_response = getCategory(get_category_endpoint_url);
        get_category_response.prettyPrint();
        System.out.println("Status of Category : "+get_category_response.statusCode());
        System.out.println("###########################################");
        System.out.println("##### UPDATE CATEGORY RESPONSE #############");
        update_category_endpoint_url = update_category_endpoint_url + category_id;
        Response update_category_response = updateCategory(update_category_endpoint_url,update_category_json_body);
        String updated_category_name = update_category_response.jsonPath().get("name");
        update_category_response.prettyPrint();
        System.out.println(updated_category_name);
        if (!updated_category_name.equals(category_name)) {
            System.out.println(" Test Case Passed");
        }
        else {
            System.out.println("Test Case Failed");
        }
        System.out.println("###########################################");
        System.out.println("##### DELETE CATEGORY RESPONSE #############");
        delete_category_endpoint_url = delete_category_endpoint_url + category_id;
        Response delete_category_response = deleteCategory(delete_category_endpoint_url);
        delete_category_response.prettyPrint();
        System.out.println(delete_category_response.statusCode());
        System.out.println("###########################################");
    }

public static Response createCategory(String endPointURL, String jsonBody){

    Map<String,String> headerMap = new HashMap<String,String>();
    headerMap.put("Content-Type","application/json");
    RequestSpecification httpRequest = RestAssured.given();
    RequestSpecification httpRequestBody  = httpRequest.body(jsonBody).headers(headerMap);
    return httpRequestBody.post(endPointURL);

}

    public static Response getCategory(String endPointURL){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody  = httpRequest.headers(headerMap);
        return httpRequestBody.get(endPointURL);

    }

    public static Response updateCategory(String endPointURL, String jsonBody){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody = httpRequest.headers(headerMap).body(jsonBody);
        return httpRequestBody.put(endPointURL);

    }

    public static Response deleteCategory(String endPointURL){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody = httpRequest.headers(headerMap);
        return httpRequestBody.delete(endPointURL);

    }
}
