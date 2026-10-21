
package org.example.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Reservation;
import org.example.autolocapi.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Réservations", description = "Gestion des réservations")
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping
    @Operation(summary = "Lister les réservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une réservation")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une réservation")
    public Reservation ajouterReservation(
            @RequestBody Reservation reservation) {
        return reservationService.ajouterReservation(reservation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une réservation")
    public Reservation modifierReservation(
            @PathVariable Long id,
            @RequestBody Reservation reservation) {
        return reservationService.modifierReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une réservation")
    public void supprimerReservation(@PathVariable Long id) {
        reservationService.supprimerReservation(id);
    }
}
