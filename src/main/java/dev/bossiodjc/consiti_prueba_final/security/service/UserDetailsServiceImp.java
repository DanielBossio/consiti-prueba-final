/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.security.service;

import dev.bossiodjc.consiti_prueba_final.security.entity.Usuario;
import dev.bossiodjc.consiti_prueba_final.security.entity.UsuarioActual;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author bossi
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> u = this.usuarioService.getByEmail(username);
        if (!u.isPresent())
            throw new UsernameNotFoundException("Usuario no encontrado");
        return UsuarioActual.build(u.get());
    }
}
