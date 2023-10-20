package com.example.grappler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
@OpenAPIDefinition(
		info= @Info ( title = "Time Management - Module 6",
				version="1.0.0",
				description="The project is to define the structure of APIs to Track the Time Management of Employees for Productivity Enhancement",
				termsOfService="https://grappler.innogent.in/dashboard"  ,
				contact = @Contact ( name = "Nisha Kashyap, Shubham Mishra, Piyush Kag" , email ="nisha.kashyap@innogent.in ,shubham.mishra@innogent.in , piyush.kag@innogent.in " ) ,
				license = @License ( name = "licence",url="https://github.com/Piyush-kag/Grappler-Enhancement-M6")

		)
)
public class GrapplerApplication {
	public static void main(String[] args) {
		SpringApplication.run(GrapplerApplication.class, args);
		System.out.println("Running Successfully");
	}

}
