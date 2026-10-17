
package org.example.autolocapi.service;

import org.example.autolocapi.domain.Reservation;
import java.util.List;

public interface IReservationService {

    Reservation ajouterReservation(Reservation reservation);

    Reservation getReservationById(Long id);

    List<Reservation> getAllReservations();

    Reservation modifierReservation(Long id, Reservation reservation);

    void supprimerReservation(Long id);
}
