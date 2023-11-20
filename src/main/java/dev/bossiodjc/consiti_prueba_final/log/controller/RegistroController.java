/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.log.controller;

import dev.bossiodjc.consiti_prueba_final.log.dto.RegistroDTO;
import dev.bossiodjc.consiti_prueba_final.log.entity.Registro;
import dev.bossiodjc.consiti_prueba_final.log.service.RegistroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bossi
 */
@Controller
public class RegistroController {
    
    @Autowired
    private RegistroService rService;
    
    public ResponseEntity<Registro> findById(int id){
        return new ResponseEntity<>(rService.getById(id).get(), HttpStatus.OK);
    }
    
    public ResponseEntity<List<Registro>> findByUsuario(String usuario) {
        return new ResponseEntity<>(rService.getByUsuario(usuario).get(), HttpStatus.OK);
    }
    
    public void create(RegistroDTO rdto){
        Registro reg = new Registro(rdto.getConsulta(), rdto.getExitoso(), rdto.getRepuesta(), rdto.getUsuario());
        this.rService.save(reg);
    }
}
