package com.parecelle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parecelle.model.UserModel;
import com.parecelle.objects.information.User;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserModel userModel;

    private final ObjectMapper objectMapper;

    public UserController (ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("GET/users/login")
    public ResponseEntity<String> login(@RequestParam String nom,@RequestParam String mdp) throws Exception {
        User user = userModel.getOneUser(nom,mdp);
        String json = objectMapper.writeValueAsString(user);
        return ResponseEntity.ok(json);
    }
    
}
