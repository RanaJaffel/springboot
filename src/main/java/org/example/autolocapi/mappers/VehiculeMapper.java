package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;

import org.example.autolocapi.domain.Vehicule;
import org.example.autolocapi.web.dto.dto.VehiculeRequestDTO;
import org.example.autolocapi.web.dto.dto.VehiculeResponseDTO;

@Component
public class VehiculeMapper {

    public Vehicule toEntity(VehiculeRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Vehicule vehicule = new Vehicule();

        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setStatut(dto.getStatut());

        return vehicule;
    }

    public VehiculeResponseDTO toResponseDTO(Vehicule vehicule) {

        if (vehicule == null) {
            return null;
        }

        VehiculeResponseDTO dto = new VehiculeResponseDTO();

        dto.setId(vehicule.getIdVehicule());
        dto.setImmatriculation(vehicule.getImmatriculation());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setTarifJournalier(vehicule.getTarifJournalier());
        dto.setCategorie(vehicule.getCategorie());
        dto.setStatut(vehicule.getStatut());

        if (vehicule.getAgence() != null) {
            dto.setAgenceId(vehicule.getAgence().getIdAgence());
        }

        return dto;
    }
}