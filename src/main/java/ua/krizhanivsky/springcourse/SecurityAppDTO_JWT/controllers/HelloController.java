package ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.security.PersonDetails;
import ua.krizhanivsky.springcourse.SecurityAppDTO_JWT.services.AdminService;


@RestController
public class HelloController {
    private final AdminService adminService;
      @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getUsername();
    }
    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdminStuff();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getUsername();
    }

}