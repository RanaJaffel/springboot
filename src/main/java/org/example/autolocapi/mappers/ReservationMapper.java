package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;
import org.example.autolocapi.domain.Reservation;
import org.example.autolocapi.web.dto.dto.ReservationRequestDTO;
import org.example.autolocapi.web.dto.dto.ReservationResponseDTO;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationRequestDTO dto) {
        if (dto == null) return null;

        Reservation reservation = new Reservation();
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());
        // Client et véhicule doivent être associés dans le service à partir de leurs IDs.
        return reservation;
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation) {
        if (reservation == null) return null;

        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(reservation.getIdReservation());
        dto.setDateDebut(reservation.getDateDebut());
        dto.setDateFin(reservation.getDateFin());
        dto.setStatut(reservation.getStatut());

        if (reservation.getClient() != null) {
            dto.setClientId(reservation.getClient().getIdClient());
        }
        if (reservation.getContrat() != null) {
            dto.setContratId(reservation.getContrat().getIdContrat());
        }
        if (reservation.getVehicule() != null) {
            dto.setVehiculeId(reservation.getVehicule().getIdVehicule());
        }
        return dto;
    }
}
