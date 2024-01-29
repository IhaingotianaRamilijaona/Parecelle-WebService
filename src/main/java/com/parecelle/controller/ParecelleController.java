package com.parecelle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parecelle.model.ParecelleModel;
import com.parecelle.objects.Tany.Parecelle;

@RestController
@CrossOrigin(origins = "*")
public class ParecelleController {
    private final ObjectMapper objectMapper;

    @Autowired
    ParecelleModel parecelleModel;

    public ParecelleController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("GET/parecelles")
    public ResponseEntity<String> getParecelles() throws Exception {
        List<Parecelle> parecelles = parecelleModel.getListParecelles();
        String json = objectMapper.writeValueAsString(parecelles);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/parecelles/{idParecelle}")
    public ResponseEntity<String> getParecellesById(@PathVariable int idParecelle) throws Exception {
        Parecelle parecelles = parecelleModel.getParecellesById(idParecelle);
        String json = objectMapper.writeValueAsString(parecelles);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/parecelles/terrains/{idTerrain}")
    public ResponseEntity<String> getParecellesByTerrain(@PathVariable int idTerrain) throws Exception {
        List<Parecelle> parecelles = parecelleModel.getParecellesByTerrain(idTerrain);
        String json = objectMapper.writeValueAsString(parecelles);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/parecelles/cultures/{idCulture}")
    public ResponseEntity<String> getParecellesByCulture(@PathVariable int idCulture) throws Exception {
        List<Parecelle> parecelles = parecelleModel.getParecellesByCulture(idCulture);
        String json = objectMapper.writeValueAsString(parecelles);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/parecelles/users/{idUser}")
    public ResponseEntity<String> getParecellesByUser(@PathVariable int idUser) throws Exception {
        List<Parecelle> parecelles = parecelleModel.getParecellesByUser(idUser);
        String json = objectMapper.writeValueAsString(parecelles);
        return ResponseEntity.ok(json);
    }

}
