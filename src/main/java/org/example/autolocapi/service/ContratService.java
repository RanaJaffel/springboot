
package org.example.autolocapi.service;


import org.example.autolocapi.domain.Contrat;
import org.example.autolocapi.exception.ResourceNotFoundException;
import org.example.autolocapi.repository.IContratRepository;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ContratService implements IContratService {


    private final IContratRepository contratRepository;

    @Override
    public Contrat create(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant");
        }

        verifierMontant(contrat);

        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Long id, Contrat contrat) {
        Contrat existant = findById(id);

        verifierMontant(contrat);

        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.getValide());
        existant.setReservation(contrat.getReservation());

        return contratRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }

        contratRepository.deleteById(id);
    }

    private void verifierMontant(Contrat contrat) {
        BigDecimal montant = contrat.getMontantTotal();

        if (montant != null && montant.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Le montant total du contrat ne peut pas être négatif");
        }
    }
}
