package io.crscube.coop.cubetoken.cooperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"io.crscube.coop.cubetoken"})
public class CooperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooperationApplication.class, args);
	}
}
