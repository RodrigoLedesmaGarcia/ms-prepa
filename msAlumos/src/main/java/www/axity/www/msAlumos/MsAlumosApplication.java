package www.axity.www.msAlumos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsAlumosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAlumosApplication.class, args);
	}

}
