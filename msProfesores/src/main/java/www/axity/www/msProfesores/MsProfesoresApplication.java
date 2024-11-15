package www.axity.www.msProfesores;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsProfesoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProfesoresApplication.class, args);
	}

}
