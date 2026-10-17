
package org.example.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Reservation;
import org.example.autolocapi.exception.ResourceNotFoundException;
import org.example.autolocapi.repository.IReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "L'identifiant doit être null lors de la création."
            );
        }

        verifierDates(reservation);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation", id)
                );
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation modifierReservation(
            Long id, Reservation reservation) {

        Reservation existante = getReservationById(id);

        verifierDates(reservation);

        existante.setDateDebut(reservation.getDateDebut());
        existante.setDateFin(reservation.getDateFin());
        existante.setStatut(reservation.getStatut());
        existante.setVehicule(reservation.getVehicule());
        existante.setClient(reservation.getClient());
        existante.setContrat(reservation.getContrat());

        return reservationRepository.save(existante);
    }

    @Override
    public void supprimerReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }

    private void verifierDates(Reservation reservation) {
        if (reservation.getDateDebut() != null
                && reservation.getDateFin() != null
                && reservation.getDateFin()
                .isBefore(reservation.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être avant la date de début."
            );
        }
    }
}
