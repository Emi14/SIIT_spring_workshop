package org.siit.springworkshop.service.hello;

import org.siit.springworkshop.exception.AgeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringTokenizer;


@Service
@Profile("dev")
//@Qualifier("service")
//@Primary
//@Lazy
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) - default behaviour
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HelloWorldService implements IService {

    private StringTokenizer stringTokenizer;

    //    @Value("my name") // can be used with SpEl
    @Value("${my.name}")
    private String name;

    @Value("#{'${my.values}'.split(',')}")
    private List<String> values;


    @Autowired
    public void setStringTokenizer(StringTokenizer stringTokenizer) {
        this.stringTokenizer = stringTokenizer;
    }

    public String greet(String firstName, String lastName, Integer age) throws AgeException {
        if (age > 99) {
            throw new AgeException("Invalid age");
        }
        return String.format("Hello World, %s %s. You are %s", firstName, lastName, age);
    }

    @Override
    public String getCurrentTime() {
        return " Year - 2023";
    }
}

