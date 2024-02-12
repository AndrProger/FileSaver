package kz.test.filesaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FilesaverApplication {

  public static void main(String[] args) {
    SpringApplication.run(FilesaverApplication.class, args);
  }
}
