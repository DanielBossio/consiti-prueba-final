/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.security.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author bossi
 */
@Getter
@Setter
public class LoginUsr {
    
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
