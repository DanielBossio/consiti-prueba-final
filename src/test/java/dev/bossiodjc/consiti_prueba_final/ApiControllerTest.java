/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final;

import dev.bossiodjc.consiti_prueba_final.controller.ApiController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author bossi
 */
@RestClientTest(ApiController.class)
@AutoConfigureMockMvc
public class ApiControllerTest {
    
    private String key = "7b8a11d449bd4041c4c09234763b95e6";
    
    @Autowired
    TestRestTemplate test = new TestRestTemplate();
    
    @Test
    public void testingCurrentWeatherConnection() throws Exception{
        ResponseEntity<String> response = test.getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?q=Cartagena&appid="+key, 
                String.class);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    public void testingForecastConnection(){
        ResponseEntity<String> response = test.getForEntity(
                "https://api.openweathermap.org/data/2.5/forecast?q=Cartagena&appid="+key, 
                String.class);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    public void testingGeolocationConnection(){
        ResponseEntity<String> response = test.getForEntity(
                "https://api.openweathermap.org/data/1.0/direct?q=Cartagena&limit=1&appid="+key, 
                String.class);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    public void testingPollutionConnection(){
        ResponseEntity<String> response = test.getForEntity(
                "https://api.openweathermap.org/data/2.5/air_pollution?lat=80f&lon=80&limit=1&appid="+key, 
                String.class);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
