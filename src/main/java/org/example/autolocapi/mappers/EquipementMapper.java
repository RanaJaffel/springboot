package org.example.autolocapi.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.example.autolocapi.domain.Equipement;
import org.example.autolocapi.web.dto.dto.EquipementRequestDTO;
import org.example.autolocapi.web.dto.dto.EquipementResponseDTO;

@Component
public class EquipementMapper {

    public Equipement toEntity(EquipementRequestDTO dto) {
        if (dto == null) return null;

        Equipement equipement = new Equipement();
        equipement.setLibelle(dto.getLibelle());
        // Les véhicules associés doivent être chargés dans le service à partir de leurs IDs.
        return equipement;
    }

    public EquipementResponseDTO toResponseDTO(Equipement equipement) {
        if (equipement == null) return null;

        EquipementResponseDTO dto = new EquipementResponseDTO();
        dto.setId(equipement.getIdEquipement());
        dto.setLibelle(equipement.getLibelle());

        if (equipement.getVehicules() != null) {
            dto.setVehiculeIds(equipement.getVehicules().stream()
                    .map(v -> v.getIdVehicule())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }
}
