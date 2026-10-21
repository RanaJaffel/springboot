
package org.example.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Agence;
import org.example.autolocapi.service.IAgenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences")
public class AgenceController {

    private final IAgenceService agenceService;

    @GetMapping
    @Operation(summary = "Lister toutes les agences")
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une agence")
    public Agence findById(@PathVariable Long id) {
        return agenceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une agence")
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence")
    public Agence update(@PathVariable Long id,
                         @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une agence")
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}
