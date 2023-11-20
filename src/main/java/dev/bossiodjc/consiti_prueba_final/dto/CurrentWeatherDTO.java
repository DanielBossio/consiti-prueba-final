/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.dto;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author bossi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeatherDTO {
    
    private Coord coord;
    private ArrayList<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Coord {

        private float lon;
        private float lat;
    }
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Sys {

        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}
