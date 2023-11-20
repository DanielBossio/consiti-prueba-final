/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.bossiodjc.consiti_prueba_final.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bossiodjc.consiti_prueba_final.dto.AirPollutionDTO;
import dev.bossiodjc.consiti_prueba_final.dto.CurrentWeatherDTO;
import dev.bossiodjc.consiti_prueba_final.dto.ForecastDTO;
import dev.bossiodjc.consiti_prueba_final.dto.GeolocalizationDTO;
import dev.bossiodjc.consiti_prueba_final.log.controller.RegistroController;
import dev.bossiodjc.consiti_prueba_final.log.dto.RegistroDTO;
import dev.bossiodjc.consiti_prueba_final.security.entity.Usuario;
import dev.bossiodjc.consiti_prueba_final.security.entity.UsuarioActual;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author bossi
 */
@RestController
@RequestMapping("/servicios")
@PreAuthorize("isAuthenticated()")
public class ApiController {
    
    @Autowired
    private RestTemplate template;
    
    @Value("{app.key}")
    private String key;
    
    @Autowired
    RegistroController reg;
    
    @Autowired
    UsuarioActual usr;
    
    @Autowired
    ObjectMapper mapper;
    
    private Usuario usuario(){
        return new Usuario(usr.getEmail(), usr.getNombre(), usr.getPassword());
    }
    
    @CacheEvict(value="localizaciones", allEntries=true)
    public float[] localizar(String nombre){
        //Se localizan las coordenadas, se lee el JSON y se retorna como un arreglo de float
        try {
            ArrayList<String> res = template.getForEntity(
                    String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s",
                            nombre, key), ArrayList.class).getBody();
            GeolocalizationDTO loc = mapper.readValue(res.get(res.size()-1), GeolocalizationDTO.class);
            float[] coords = {loc.getLat(), loc.getLon()};
            reg.create(new RegistroDTO("Coordenadas de "+nombre, "SI",
                    res.get(res.size()-1),
                    this.usuario()));
            return coords;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si no se leen las coordenadas se retorna null
        reg.create(new RegistroDTO("Coordenadas de "+nombre, "NO",
                    "Error al parsear JSON",
                    this.usuario()));
        return null;
    }
    
    @CacheEvict(value="climas", allEntries=true)
    @GetMapping("/clima-actual/{ciudad}")
    public ResponseEntity<?> climaActual(@PathVariable("ciudad") String ciudad){
        //Se obtiene la respuesta, se agrega el registro y luego se retorna
        try {
            ResponseEntity<CurrentWeatherDTO> resp =  template.getForEntity(
                    String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s",
                            ciudad, key),
                    CurrentWeatherDTO.class);
            reg.create(new RegistroDTO("Clima actual de "+ciudad, "SI",
                    mapper.writeValueAsString(resp.getBody()),
                    this.usuario()));
            return resp;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si no se logra leer la respuesta se retorna un mensaje de error
        reg.create(new RegistroDTO("Clima actual de "+ciudad, "NO",
                    "Error al parsear JSON",
                    this.usuario()));
        return new ResponseEntity<> ("Error al parsear JSON", HttpStatus.BAD_REQUEST); 
    }
    
    @CacheEvict(value="pronosticos", allEntries=true)
    @GetMapping("/historico/{ciudad}")
    public ResponseEntity<?> historico(@PathVariable("ciudad") String ciudad){
        //Se obtiene la respuesta, se agrega el registro y luego se retorna
        try {
            ResponseEntity<?> resp = template.getForEntity(
                    String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s",
                            ciudad, key),
                    ForecastDTO.class);
            reg.create(new RegistroDTO("Pronósticos de "+ciudad, "SI",
                    mapper.writeValueAsString(resp.getBody()),
                    this.usuario()));
            return resp;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si no se logra leer la respuesta se retorna un mensaje de error
        reg.create(new RegistroDTO("Clima actual de "+ciudad, "NO",
                    "Error al parsear JSON",
                    this.usuario()));
        return new ResponseEntity<> ("Error al parsear JSON", HttpStatus.BAD_REQUEST); 
    }
    
    @CacheEvict(value="contaminaciones", allEntries=true)
    @GetMapping("/contaminacion/{ciudad}")
    public ResponseEntity<?> contaminacion(@PathVariable("ciudad") String ciudad){
        try {
            float[] coords = localizar(ciudad);
            if (coords == null) throw new ArithmeticException();
            ResponseEntity<?> resp =  template.getForEntity(
                    String.format("https://api.openweathermap.org/data/2.5/air_pollution?lat=%.2f&lon=%.2f&appid=%s",
                            coords[0], coords[1], key),
                    AirPollutionDTO.class);
            reg.create(new RegistroDTO("Contaminación del aire de "+ciudad, "SI",
                    mapper.writeValueAsString(resp.getBody()),
                    this.usuario()));
            return resp;
        } catch (JsonProcessingException | ArithmeticException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si no se logra leer la respuesta se retorna un mensaje de error
        reg.create(new RegistroDTO("Clima actual de "+ciudad, "NO",
                    "Error al parsear JSON",
                    this.usuario()));
        return new ResponseEntity<> ("Error al parsear JSON", HttpStatus.BAD_REQUEST); 
    }
}
