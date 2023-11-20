/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.dto;

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
public class GeolocalizationDTO {
    
    private String name;
    private float lat;
    private float lon;
    private String country;
    private String state;
}
