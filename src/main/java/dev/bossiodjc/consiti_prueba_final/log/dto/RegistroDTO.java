/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.log.dto;

import dev.bossiodjc.consiti_prueba_final.security.entity.Usuario;
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
public class RegistroDTO {
    
    private int idRegistro;
    private String consulta;
    private String exitoso;
    private String repuesta;
    private Usuario usuario;

    public RegistroDTO(String consulta, String exitoso, String repuesta, Usuario usuario) {
        this.consulta = consulta;
        this.exitoso = exitoso;
        this.repuesta = repuesta;
        this.usuario = usuario;
    }
}
