package br.com.mba.engenharia.de.software.refactoring.controller;

import org.springframework.stereotype.Component;

@Component
public class UserLinks {
	
	public static final String LIST_USERS = "/users";
    public static final String ADD_USER = "/user";
    public static final String AUTHENTICATE = "/authenticate";

}
