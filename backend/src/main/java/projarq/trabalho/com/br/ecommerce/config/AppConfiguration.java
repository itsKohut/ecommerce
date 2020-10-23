package projarq.trabalho.com.br.ecommerce.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppConfiguration {

    @Bean
    public Faker myFaker() {
        return new Faker(new Locale("pt-BR"));
    }

}
