
package org.example.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.example.autolocapi.service.IPaiementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Gestion des paiements des contrats")
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping
    @Operation(
            summary = "Lister les paiements",
            description = "Récupère la liste de tous les paiements"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des paiements récupérée avec succès"
    )
    public Object getAllPaiements() {
        return paiementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulter un paiement",
            description = "Récupère un paiement à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement trouvé"),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    })
    public Object getPaiementById(@PathVariable Long id) {
        return paiementService.findById(id);
    }
}
