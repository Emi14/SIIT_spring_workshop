package org.siit.springworkshop.service;

import org.siit.springworkshop.exception.AgeException;

public interface IService {

    public String getCurrentTime();

    String greet(String firstName, String lastName, Integer age) throws AgeException;
}
