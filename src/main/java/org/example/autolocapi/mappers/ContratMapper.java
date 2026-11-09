package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;
import org.example.autolocapi.domain.Contrat;
import org.example.autolocapi.web.dto.dto.ContratRequestDTO;
import org.example.autolocapi.web.dto.dto.ContratResponseDTO;

@Component
public class ContratMapper {

    public Contrat toEntity(ContratRequestDTO dto) {
        if (dto == null) return null;

        Contrat contrat = new Contrat();
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(dto.isValide());
        return contrat;
    }

    public ContratResponseDTO toResponseDTO(Contrat contrat) {
        if (contrat == null) return null;

        ContratResponseDTO dto = new ContratResponseDTO();
        dto.setId(contrat.getIdContrat());
        dto.setDateSignature(contrat.getDateSignature());
        dto.setMontantTotal(contrat.getMontantTotal());
        dto.setValide(contrat.getValide());
        return dto;
    }
}
