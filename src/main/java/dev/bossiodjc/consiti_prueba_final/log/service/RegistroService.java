/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.log.service;

import dev.bossiodjc.consiti_prueba_final.log.entity.Registro;
import dev.bossiodjc.consiti_prueba_final.log.repository.RegistroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bossi
 */
@Service
@Transactional
public class RegistroService {
    
    @Autowired
    private RegistroRepository registroRep;
    
    public Optional<Registro> getById(int id){
        return this.registroRep.findById(id);
    }
    
    public boolean existsById(int id){
        return this.registroRep.existsById(id);
    }
    
    public Optional<List<Registro>> getByUsuario(String usuario){
        return this.registroRep.findByUsuario(usuario);
    }
    
    public boolean existsByNombreUsuario(String usuario){
        return this.registroRep.existsByUsuario(usuario);
    }
    
    public void save(Registro registro){
        this.registroRep.save(registro);
    }
}
