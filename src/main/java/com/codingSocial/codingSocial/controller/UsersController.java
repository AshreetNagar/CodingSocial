package com.codingSocial.codingSocial.controller;

import com.codingSocial.codingSocial.model.UsersModel;
import com.codingSocial.codingSocial.service.UsersService;
import com.codingSocial.codingSocial.resource.LoginForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.csrf.CsrfToken;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    // public UsersController(UsersService usersService) {
    //     this.usersService = usersService;
    // }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@RequestBody UsersModel usersModel) {
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(),
                usersModel.getEmail());

        return registeredUser == null ? "error_page" : "redirect/login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersModel usersModel, Model model) {
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("usersLogin", authenticated.getLogin());
            authenticated.setPassword("");
            return ResponseEntity.ok().body(authenticated);
        } else {
            return ResponseEntity.badRequest().body("error_page");
        }

    }


    // NEW AUTH????????????????????????????????????????????????????????????????????
    private final RememberMeServices rememberMeServices;

    @PostMapping("/login_v2")
    public CurrentUser login(@RequestBody LoginForm form, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        if (request.getUserPrincipal() != null) {
            System.out.println("Please logout first.");
            return null;
        }
        if (bindingResult.hasErrors()) {
            System.out.println("Invalid username or password");
            return null;
        }
        System.out.println("login info:"+form.getUsername()+" "+form.getPassword());
        try {
            // usersService.hashCode(form.getPassword())
            request.login(form.getUsername(), form.getPassword());
        } catch (ServletException e) {
            System.out.println("Invalid username or password");
            return null;
        }

        var auth = (Authentication) request.getUserPrincipal();
        var user = (UsersModel) auth.getPrincipal();
        System.out.println("User "+user.getUsername()+" logged in.");

        rememberMeServices.loginSuccess(request, response, auth);
        return new CurrentUser(user.getId(), user.getUsername());
    }

    @PostMapping("/register_v2")
    public String register_v2(@RequestBody UsersModel usersModel) {
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser_v2(usersModel.getLogin(), usersModel.getPassword(),
                usersModel.getEmail());

        return registeredUser == null ? "error_page" : "redirect/login";
    }

    @PostMapping("/logout_v2")
    public LogoutResponse logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return new LogoutResponse();
    }

    @GetMapping("/current-user_v2")
    public String getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        if (user == null){
            return "USER IS NULL";
        }
        return user.getUsername();
        // return new CurrentUser(user.getId(), user.getUsername());
    }

    @GetMapping("/csrf")
    public CsrfResponse csrf(HttpServletRequest request) {
        var csrf = (CsrfToken) request.getAttribute("_csrf");
        return new CsrfResponse(csrf.getToken());
    }

    public record CurrentUser(Integer id, String nickname) {
    }

    public record LogoutResponse() {
    }

    public record CsrfResponse(String token) {
    }

}
