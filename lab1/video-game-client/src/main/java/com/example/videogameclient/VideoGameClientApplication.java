package com.example.videogameclient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication   //(exclude = JmxAutoConfiguration.class)
@EnableDiscoveryClient
public class VideoGameClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoGameClientApplication.class, args);
	}

}
