package com.platzi.qa.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class getProductsDetails {

	public static void main(String[] args) {
		
		/**
		 * HTTPRequest :     
		 *              POST : create a body
		 *              GET :  get information
		 *              PUT: to update 
		 *              DELETE: delete the data
		 */
		
		RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("https://api.escuelajs.co/api/v1/products");
		System.out.println(response.prettyPrint());
		int status_code = response.statusCode();
		if(status_code == 200 ){
			System.out.println("Valid Status Code : "+status_code);
		}
		else {
			System.out.println("Invalid Status Code : "+status_code);
		}



	}

}
