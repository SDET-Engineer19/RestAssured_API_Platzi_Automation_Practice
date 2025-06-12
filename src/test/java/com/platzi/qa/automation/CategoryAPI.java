package com.platzi.qa.automation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CategoryAPI {


    public static void main(String[] args) {

        // create a category using post call
//String jsonBody = "{\n" +
//        "  \"name\": \"ECart Shopping Category\",\n" +
//        "  \"image\": \"https://placeimg.com/640/480/any\"\n" +
//        "}";
//        RequestSpecification httpRequest = RestAssured.given();
//        RequestSpecification requestBody = httpRequest.body(jsonBody);
//        Response response =requestBody
//                .accept(ContentType.JSON)
//                         .contentType(ContentType.JSON)
//                                    .post("https://api.escuelajs.co/api/v1/categories/");
//        response.prettyPrint();
//        System.out.println(response.statusCode());

        getCategoryResponse("https://api.escuelajs.co/api/v1/categories");
        System.out.println("***********");
        getCategoryResponse("https://api.escuelajs.co/api/v1/categories/6");

    }


    public static void getCategoryResponse(String endPointURL){

        RequestSpecification httpRequest = RestAssured.given();
       Response response = httpRequest
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get(endPointURL);
       response.prettyPrint();
       String category_name = getJsonValue(response,"updatedAt");
       System.out.println(category_name);
    }

    public static String getJsonValue(Response response,String name){

         String Category_Name = response.jsonPath().get(name).toString();
         return Category_Name;
    }
}
