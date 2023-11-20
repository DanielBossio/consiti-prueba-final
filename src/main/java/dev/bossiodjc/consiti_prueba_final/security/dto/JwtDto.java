/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.security.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author bossi
 */
@Getter
@Setter
public class JwtDto {
    
    private String token;

    public JwtDto(String token) {
        this.token = token;
    }
}
