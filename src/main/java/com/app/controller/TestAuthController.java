package com.app.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@PreAuthorize("denyAll()")
public class TestAuthController {

    @GetMapping("/public")
//    @PreAuthorize("permitAll()")
    public String helloPublic(){
        return "Hello World desde public";
    }

    @GetMapping("/private")
//    @PreAuthorize("isAuthenticated()")
    public String helloPrivate(){
        return "Hello World desde private";
    }

    @GetMapping("/user")
//    @PreAuthorize("hasAuthority('READ')")
    public String helloUser(){
        return "Hello World desde User";
    }

    @GetMapping("/manager")
//    @PreAuthorize("hasRole('MANAGER')")
    public String helloManager(){
        return "Hello World desde Manager";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('READ')")
    public String helloAdmin(){
        return "Hello World desde Admin";
    }
}
