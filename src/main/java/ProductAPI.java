import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ProductAPI {


    public static void main(String[] args) {

        String create_product_endpoint_url = "https://api.escuelajs.co/api/v1/products/ ";
        String get_product_endpoint_url = "https://api.escuelajs.co/api/v1/products";
        String update_product_endpoint_url = "https://api.escuelajs.co/api/v1/products/";
        String delete_product_endpoint_url = "https://api.escuelajs.co/api/v1/products/";


        String create_product_json = "{\n" +
                "  \"title\": \"Iphone 16\",\n" +
                "  \"price\": 100000,\n" +
                "  \"description\": \"IOS Mobile Phone\",\n" +
                "  \"categoryId\": 2,\n" +
                "  \"images\": [\"https://placeimg.com/6409/4800/any\"]\n" +
                "}\n" +
                "\n";
        String update_product_json = "{\n" +
                "  \"title\": \"Iphone 16\",\n" +
                "  \"price\": 90000\n" +
        "}\n" +
                "\n";
        System.out.println("##### CREATE PRODUCT  RESPONSE #############");
        Response create_product_response = createProduct(create_product_endpoint_url,create_product_json);
        create_product_response.prettyPrint();
        int product_id = create_product_response.jsonPath().getInt("id");
        String product_title = create_product_response.jsonPath().getString("title");
        int product_price = create_product_response.jsonPath().getInt("price");
        System.out.println("Status Code of Product : " +create_product_response.statusCode());
        System.out.println("Product Title : "+product_title);
        System.out.println("Product ID : "+product_id);
        System.out.println("Product Price : "+product_price);
        System.out.println("###########################################");
        System.out.println("##### RETRIEVE PRODUCT  RESPONSE #############");
        Response get_product_response = getProduct(get_product_endpoint_url);
        get_product_response.prettyPrint();
        System.out.println("Status Code of Product : " +get_product_response.statusCode());
        System.out.println("###########################################");
        System.out.println("##### UPDATE PRODUCT  RESPONSE #############");
        update_product_endpoint_url = update_product_endpoint_url + product_id;
        Response update_product_response = updateProduct(update_product_endpoint_url,update_product_json);
        update_product_response.prettyPrint();
        System.out.println("Status Code of Product : " +update_product_response.statusCode());
        int updated_product_price = update_product_response.jsonPath().getInt("price");
        if(product_price != updated_product_price){
            System.out.println(product_title+" price is updated :"+updated_product_price);
        }else {
            System.out.println(product_title+" price is not updated :"+product_price);
            System.out.println(product_title+" price is showing as same old price  :"+product_price);
        }
        System.out.println("###########################################");
        System.out.println("##### DELETE PRODUCT  RESPONSE #############");
        delete_product_endpoint_url = delete_product_endpoint_url + product_id;
        Response delete_product_response = deleteProduct(delete_product_endpoint_url);
        delete_product_response.prettyPrint();
        System.out.println("Status Code of Product : " +delete_product_response.statusCode());
        System.out.println("###########################################");
        System.out.println("##### GET SPECIFIC PRODUCT  RESPONSE #############");
        String get_specific_endpoint_url = get_product_endpoint_url + product_id;
        Response get_product_id_response = getProductDetails(get_specific_endpoint_url);
        get_product_id_response.prettyPrint();
        if(get_product_id_response.statusCode() == 400){
            System.out.println("Product :"+product_title+" is Deleted");
        }
        else {
            System.out.println("Product :"+product_title+" is Not Deleted");
        }

    }



    public static Response createProduct(String endpoint, String jsonBody){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody = httpRequest.headers(headerMap).body(jsonBody);
        return httpRequestBody.post(endpoint);

    }

    public static Response getProduct(String endpoint){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody = httpRequest.headers(headerMap);
        return httpRequestBody.get(endpoint);
    }

    public static Response updateProduct(String endpoint, String jsonBody){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody = httpRequest.headers(headerMap).body(jsonBody);
        return httpRequestBody.put(endpoint);

    }

    public static Response deleteProduct(String endpoint){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody = httpRequest.headers(headerMap);
        return httpRequestBody.delete(endpoint);

    }

    public static Response getProductDetails(String endpoint){

        Map<String,String> headerMap = new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        RequestSpecification httpRequest = RestAssured.given();
        RequestSpecification httpRequestBody = httpRequest.headers(headerMap);
        return httpRequestBody.get(endpoint);
    }

}
