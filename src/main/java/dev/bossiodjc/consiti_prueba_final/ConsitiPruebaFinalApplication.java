package dev.bossiodjc.consiti_prueba_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ConsitiPruebaFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsitiPruebaFinalApplication.class, args);
	}

}
