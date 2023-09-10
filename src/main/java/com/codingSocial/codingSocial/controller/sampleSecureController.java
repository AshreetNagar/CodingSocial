package com.codingSocial.codingSocial.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lombok.AllArgsConstructor;

import com.codingSocial.codingSocial.model.UsersModel;

@RestController
@CrossOrigin
// @AllArgsConstructor
public class sampleSecureController {

    private String[] sampleData = new String[1000];
    private int sampleDataCounter = 0;

    @GetMapping("/sampleSecure")
    public ResponseEntity<?> getFriends(@AuthenticationPrincipal final UsersModel user){
        System.out.println(user);
        return ResponseEntity.ok().body(sampleData);
    }

    @PostMapping("/sampleSecure")
    public ResponseEntity<?> addFriend(@RequestBody String inputMsg){
        sampleData[sampleDataCounter] = inputMsg;
        sampleDataCounter++;
        return ResponseEntity.ok().body(inputMsg);
    }

}
