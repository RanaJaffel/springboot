package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;

import org.example.autolocapi.domain.Paiement;
import org.example.autolocapi.web.dto.dto.PaiementResponseDTO;

@Component
public class PaiementMapper {

    public PaiementResponseDTO toResponseDTO(Paiement paiement) {
        if (paiement == null) {
            return null;
        }

        PaiementResponseDTO dto = new PaiementResponseDTO();

        dto.setId(paiement.getIdPaiement());
        dto.setMontant(paiement.getMontant());
        dto.setDatePaiement(paiement.getDatePaiement());
        dto.setModePaiement(paiement.getModePaiement());

        if (paiement.getContrat() != null) {
            dto.setContratId(paiement.getContrat().getIdContrat());
        }

        return dto;
    }
}