package backend.speedspringstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "backend.speedspringstudy.external.kakao.client")
public class SpeedspringstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeedspringstudyApplication.class, args);
	}

}
