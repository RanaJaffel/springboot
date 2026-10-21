
package org.example.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Equipement;
import org.example.autolocapi.service.IEquipementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
@Tag(name = "Équipements", description = "Gestion des équipements")
public class EquipementController {

    private final IEquipementService equipementService;

    @GetMapping
    @Operation(summary = "Lister les équipements")
    public List<Equipement> findAll() {
        return equipementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un équipement")
    public Equipement findById(@PathVariable Long id) {
        return equipementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un équipement")
    public Equipement create(@RequestBody Equipement equipement) {
        return equipementService.create(equipement);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un équipement")
    public Equipement update(@PathVariable Long id,
                             @RequestBody Equipement equipement) {
        return equipementService.update(id, equipement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un équipement")
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}
