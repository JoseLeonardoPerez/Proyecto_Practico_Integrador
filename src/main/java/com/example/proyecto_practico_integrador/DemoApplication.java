
package com.example.proyecto_practico_integrador;

		import com.example.proyecto_practico_integrador.repository.CursoRepository;
		import com.example.proyecto_practico_integrador.repository.ProfesorRepository;
		import com.example.proyecto_practico_integrador.repository.RepositoryOnMem;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
		import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CursoRepository createRepository(){

		return new RepositoryOnMem();

	}


}