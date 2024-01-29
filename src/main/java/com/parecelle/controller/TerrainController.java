package com.parecelle.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parecelle.model.TerrainModel;
import com.parecelle.objects.Tany.Terrain;

@RestController
@CrossOrigin(origins = "*")
public class TerrainController {
    @Autowired
    TerrainModel terrainModel;

    private final ObjectMapper objectMapper;

    public TerrainController (ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("GET/terrains/valides")
    public ResponseEntity<String> getTerrainsValides() throws Exception {
        List<Terrain> terrains = terrainModel.getListTerrainValide();
        String json = objectMapper.writeValueAsString(terrains);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/terrains/valides/{idTerrain}")
    public ResponseEntity<String> getListTerrainValideById(@PathVariable int idTerrain) throws Exception {
        List<Terrain> terrains = terrainModel.getListTerrainValideById(idTerrain);
        String json = objectMapper.writeValueAsString(terrains);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/terrains/valides/users/{idUser}")
    public ResponseEntity<String> getTerrainsValidesByUser(@PathVariable int idUser) throws Exception {
        List<Terrain> terrains = terrainModel.getListTerrainValideByUser(idUser);
        String json = objectMapper.writeValueAsString(terrains);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/terrains/non-valides")
    public ResponseEntity<String> getTerrainsNonValides() throws Exception {
        List<Terrain> terrains = terrainModel.getListTerrainNonValide();
        String json = objectMapper.writeValueAsString(terrains);
        return ResponseEntity.ok(json);
    }

    @GetMapping("GET/terrains/non-valides/users/{idUser}")
    public ResponseEntity<String> getTerrainsNonValidesByUser(@PathVariable int idUser) throws Exception {
        List<Terrain> terrains = terrainModel.getListTerrainNonValideByUser(idUser);
        String json = objectMapper.writeValueAsString(terrains);
        return ResponseEntity.ok(json);
    }

    @PostMapping("POST/terrains")
    public ResponseEntity insertTerrain(@PathVariable Object terrain) throws Exception {
        Terrain newTerrain = (Terrain) terrain;
        newTerrain.insert();
        String json = objectMapper.writeValueAsString("inserted");
        return ResponseEntity.ok(json);
    }

    @PutMapping("PUT/terrains")
    public ResponseEntity validerTerrain(@RequestBody Terrain terrain) throws Exception {
        terrain.valider();
        String json = objectMapper.writeValueAsString("validate");
        return ResponseEntity.ok(json);
    }
}
