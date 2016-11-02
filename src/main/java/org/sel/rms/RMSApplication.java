package org.sel.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

import static org.apache.coyote.http11.Constants.a;


@SpringBootApplication
public class RMSApplication {


	public static void main(String[] args) {
		SpringApplication.run(RMSApplication.class, "--debug");
    }
}
