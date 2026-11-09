package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;
import org.example.autolocapi.domain.Agence;
import org.example.autolocapi.web.dto.dto.AgenceRequestDTO;
import org.example.autolocapi.web.dto.dto.AgenceResponseDTO;

@Component
public class AgenceMapper {

    public Agence toEntity(AgenceRequestDTO dto) {
        if (dto == null) return null;

        Agence agence = new Agence();
        agence.setNom(dto.getNom());
        agence.setVille(dto.getVille());
        agence.setAdresse(dto.getAdresse());
        agence.setTelephone(dto.getTelephone());
        return agence;
    }

    public AgenceResponseDTO toResponseDTO(Agence agence) {
        if (agence == null) return null;

        AgenceResponseDTO dto = new AgenceResponseDTO();
        dto.setId(agence.getIdAgence());
        dto.setNom(agence.getNom());
        dto.setVille(agence.getVille());
        dto.setAdresse(agence.getAdresse());
        dto.setTelephone(agence.getTelephone());
        return dto;
    }
}
