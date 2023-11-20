/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.log.repository;

import dev.bossiodjc.consiti_prueba_final.log.entity.Registro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bossi
 */
@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    
    Optional<List<Registro>> findByUsuario(String usuario);
    
    boolean existsByUsuario(String usuario);
}
