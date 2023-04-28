package org.ssia.ldap.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }

    @GetMapping("/user-details")
    public LdapUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (LdapUserDetails) authentication.getPrincipal();
    }
}
