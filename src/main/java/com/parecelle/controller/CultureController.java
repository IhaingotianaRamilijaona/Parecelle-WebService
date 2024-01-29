package com.parecelle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parecelle.model.CultureModel;
import com.parecelle.objects.Tany.Culture;

@RestController
@CrossOrigin(origins = "*")
public class CultureController {
    @Autowired
    CultureModel cultureModel;

    private final ObjectMapper objectMapper;

    public CultureController (ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("GET/cultures")
    public ResponseEntity<String> getListCulture() throws Exception {
        List<Culture> cultures = cultureModel.getListCulture();
        String json = objectMapper.writeValueAsString(cultures);
        return ResponseEntity.ok(json);
    }

    @GetMapping("POST/cultures")
    public ResponseEntity<String> getListCulture(@RequestParam String nomCulture,@RequestParam String prixCulture) throws Exception {
        Culture culture = new Culture();
        culture.setNomCulture(nomCulture);
        culture.setPrix(Integer.parseInt(prixCulture));
        culture.insert();
        String json = objectMapper.writeValueAsString("Inserted");
        return ResponseEntity.ok(json);
    }

    
    // @GetMapping("/Cultures/{} ")
    // public ResponseEntity<String> filterListCulture() throws Exception {
    //     List<Culture> cultures = cultureModel.filterListCulture();
    //     String json = objectMapper.writeValueAsString(cultures);
    //     return ResponseEntity.ok(json);
    // }
    
}
