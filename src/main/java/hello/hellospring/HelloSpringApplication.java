package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	//main method 실행 ,, 글씨체가 왜이래..?
	//어노테이션으로 인해 내장 톰캣 실행
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
