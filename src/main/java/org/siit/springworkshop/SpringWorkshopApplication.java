package org.siit.springworkshop;

import org.modelmapper.ModelMapper;
import org.siit.springworkshop.service.hello.HelloWorldService;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.StringTokenizer;

@SpringBootApplication
@EnableAsync
//@EnableWebSecurity
public class SpringWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWorkshopApplication.class, args);
    }

    @Bean
//	@DependsOn("myBean")
    public StringTokenizer getStringTokenizer() {
        return new StringTokenizer("ana are mere");
    }

    @Bean
    @Qualifier("myBean")
    public Object getMyBean() {
        return new Object();
    }

    @Lookup
    public HelloWorldService getMyWorldService() {
        return null;
    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public DefaultSecurityFilterChain securityWebFilterChain(HttpSecurity serverHttpSecurity) throws Exception {
//        return serverHttpSecurity.authorizeRequests()
//                .requestMatchers("/actuator/**").permitAll().and().build();
//    }
}
