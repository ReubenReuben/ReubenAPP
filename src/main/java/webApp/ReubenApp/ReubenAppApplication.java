package webApp.ReubenApp;


import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webApp.ReubenApp.StroragFile.FileStrorageService;

@SpringBootApplication
public class ReubenAppApplication {
	@Resource
	FileStrorageService fileStrorageService;

	public static void main(String[] args) {
		SpringApplication.run(ReubenAppApplication.class, args);
	}

	public void run(String... arg) throws Exception {
//    storageService.deleteAll();
		fileStrorageService.init();
	}
}
