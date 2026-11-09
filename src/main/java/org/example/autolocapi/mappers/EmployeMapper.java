package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;

import org.example.autolocapi.domain.Employe;
import org.example.autolocapi.web.dto.dto.EmployeRequestDTO;
import org.example.autolocapi.web.dto.dto.EmployeResponseDTO;

@Component
public class EmployeMapper {

    public Employe toEntity(EmployeRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Employe employe = new Employe();

        employe.setNom(dto.getNom());
        employe.setPrenom(dto.getPrenom());
        employe.setRole(dto.getRole());

        return employe;
    }

    public EmployeResponseDTO toResponseDTO(Employe employe) {
        if (employe == null) {
            return null;
        }

        EmployeResponseDTO dto = new EmployeResponseDTO();

        dto.setId(employe.getIdEmploye());
        dto.setNom(employe.getNom());
        dto.setPrenom(employe.getPrenom());
        dto.setRole(employe.getRole());

        if (employe.getAgence() != null) {
            dto.setAgenceId(employe.getAgence().getIdAgence());
        }

        return dto;
    }
}