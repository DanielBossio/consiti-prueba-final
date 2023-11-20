/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.dto;

import java.util.List;
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
public class ForecastDTO {
    
    private int cod;
    private int message;
    private int cnt;
    private List<WeatherUnt> list;
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class WeatherUnt {
        private long dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private float pop;
        private Rain rain;
        private Sys sys;
        private String dt_txt;
        
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public class Sys {
            char pod;
        }
    }
}
