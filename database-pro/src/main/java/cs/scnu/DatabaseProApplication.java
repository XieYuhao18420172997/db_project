package cs.scnu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ServletComponentScan //开启了对servlet组件的支持
@SpringBootApplication
@EnableCaching//开启缓存注解主持
public class DatabaseProApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseProApplication.class, args);
    }
}
