package com.platzi.qa.automation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class ProductAPI {

    public static void main(String[] args) {

        /**
         * 1. testdata
         * 2. execution
         * 3. get the response
         * 4. validate the response
         */
     String endpointURL = "https://api.escuelajs.co/api/v1/products/";
     String jsonBody = "{\n" +
             "  \"title\": \"Redmi Product\",\n" +
             "  \"price\": 20000,\n" +
             "  \"description\": \"Android Mobile\",\n" +
             "  \"categoryId\": 1,\n" +
             "  \"images\": [\"https://placehold.co/600x400\"]\n" +
             "}";

     String get_product_endpoint  = "https://api.escuelajs.co/api/v1/products";


        //Response product_api_response = createProductAPI(endpointURL,jsonBody);
      Response product_api_response = getProductDetails(get_product_endpoint);
     // product_api_response.prettyPrint();
      String title =  product_api_response.jsonPath().getList("slug").get(0).toString();
      System.out.println(title);
        String slug_end_point_url = "https://api.escuelajs.co/api/v1/products/slug/";
      Response get_product_response = getProductById(slug_end_point_url,title.replace(" ","").trim());
      get_product_response.prettyPrint();






    }

    public static Response createProductAPI(String endpointURL, String jsonBody){

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(endpointURL);
        return response;

    }

    public static Response getProductDetails(String endPointURL){

        RequestSpecification httpRequest = RestAssured.given();
        Response response =httpRequest
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get(endPointURL);
        return response;

    }

    public static Response getProductById(String endPointURL,String slug){

        RequestSpecification httpRequest = RestAssured.given();
        Response response =httpRequest
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get(endPointURL+slug);
       return response;
    }
}
