/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.security.controller;

import dev.bossiodjc.consiti_prueba_final.security.dto.JwtDto;
import dev.bossiodjc.consiti_prueba_final.security.dto.LoginUsr;
import dev.bossiodjc.consiti_prueba_final.security.dto.NuevoUsr;
import dev.bossiodjc.consiti_prueba_final.security.entity.Usuario;
import dev.bossiodjc.consiti_prueba_final.security.jwt.JwtProvider;
import dev.bossiodjc.consiti_prueba_final.security.service.UsuarioService;
import java.text.ParseException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bossi
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private UsuarioService uService;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<String> nuevo(@Valid @RequestBody NuevoUsr nuevoUsuario, BindingResult bindingRes){
        if (bindingRes.hasErrors())
            return new ResponseEntity<> ("Verifique los datos introducidos", HttpStatus.BAD_REQUEST);
        if (this.uService.existsByNombreUsuario(nuevoUsuario.getEmail()))
            return new ResponseEntity<> ("El nombre "+nuevoUsuario.getEmail()+" ya se encuentra registrado", HttpStatus.BAD_REQUEST);
        if (this.uService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity<> ("El email "+nuevoUsuario.getEmail()+" ya se encuentra registrado", HttpStatus.BAD_REQUEST);
        Usuario u = new Usuario (nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), this.passwordEncoder.encode(nuevoUsuario.getPassword()));
        this.uService.save(u);
        return new ResponseEntity<> ("Usuario registrado con éxito", HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsr loginUsr, BindingResult bindingRes){
        if (bindingRes.hasErrors())
            return new ResponseEntity<> ("Usuario inválido", HttpStatus.UNAUTHORIZED);
        Authentication auth = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsr.getEmail(), loginUsr.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = this.jwtProvider.generateToken(auth);
        JwtDto dto = new JwtDto(jwt);
        return new ResponseEntity<> (dto, HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto dto) throws ParseException {
        String token = this.jwtProvider.refreshToken(dto);
        return new ResponseEntity<> (new JwtDto(token), HttpStatus.OK);
    }
}
