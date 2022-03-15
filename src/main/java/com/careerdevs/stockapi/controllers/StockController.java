package com.careerdevs.stockapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//addong a rest controller for the class
@RestController
//setting the parameters that we want to be requested
@RequestMapping("/api/stock")
public class StockController {
    // once we instantiate the enviorment we use autowire to continually access the same variable.
    //setting the field that will be accessed variables within our application.properties file.
    @Autowired
    private Environment env;


    @GetMapping("/ping")
    public String rootroute(){
        return "Pong";
    }

 //
    @GetMapping("/query")


    public String stockApi(RestTemplate restTemplate,
// creating a parameter to request
   @RequestParam("symbol") String symbol
    ){

        // creating the variable of the url
        String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol="+symbol;
        // instantiating a variable that will hold all the value
        String apodKey= env.getProperty("API_KEY");
        //creating a string that will hold the API key.
        String apiKey = "&apikey=";
        //concatonating the key with the value in the properties folder
        URL += apiKey + apodKey;
        // Using the rest template and using the getforObject
        String response = restTemplate.getForObject(URL,String.class);
        System.out.println(URL);
        System.out.println("\n\n"+response);
        return response;


    }
//    private String forImage(
//            RestTemplate restTemplate,
//            // this is requesting the specfic data from the user
//            @RequestParam("year") String year,
//            @RequestParam("month") String month,
//            @RequestParam("day") String day
//    ) {
//        String date = year + "-" + month + "-" + day;
//        String apodKey = env.getProperty("API_KEY", "APOD_KEY");
//        String URL = "https://api.nasa.gov/planetary/apod?";
//        URL += "api_key=" + apodKey;
//        URL += "&date=" + date;
//
//        ApodModel response = restTemplate.getForObject(URL, ApodModel.class);
//
//        System.out.println("\n\n" + response.getUrl());
////        return response;
//        return response.getUrl();
//    }


}
