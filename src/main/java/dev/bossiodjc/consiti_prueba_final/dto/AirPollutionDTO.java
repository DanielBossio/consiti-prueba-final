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
public class AirPollutionDTO {
    
    private float[] coord;
    private List<Pollution> list;
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Pollution{
        private long dt;
        private Main main;
        private Components components;
        
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public class Main {
            private int aqi;
        }
        
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public class Components {
            private double co;
            private double no;
            private double no2;
            private double o3;
            private double so2;
            private double pm2_5;
            private double pm10;
            private double nh3;
        }
    }
}
