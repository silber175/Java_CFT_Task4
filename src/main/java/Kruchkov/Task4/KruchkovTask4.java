package Kruchkov.Task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication(scanBasePackages = "Kruchkov.Task4")
public class KruchkovTask4 {
    public static void main(String[] args) throws Exception {
    ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
    DataProcess dataProcess =context.getBean(DataProcess.class);

       dataProcess.run();
    }
}
