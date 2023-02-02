package org.siit.springworkshop.service;

import org.siit.springworkshop.exception.AgeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.StringTokenizer;


@Service
//@Qualifier("service")
@Primary
//@Lazy
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) - default behaviour
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HelloWorldService implements IService{

    private StringTokenizer stringTokenizer;

//    @Value("my name") // can be used with SpEl
    @Value("${my.name}")
    private String name;

    @Autowired
    public void setStringTokenizer(StringTokenizer stringTokenizer) {
        this.stringTokenizer = stringTokenizer;
    }

    public String greet(String firstName, String lastName, Integer age) throws AgeException {
        if(age>99)
        {
            throw new AgeException("Invalid age");
        }
        return String.format("Hello World, %s %s. You are %s", firstName, lastName, age);
    }

    @Override
    public String getCurrentTime() {
        return " Year - 2023";
    }
}

