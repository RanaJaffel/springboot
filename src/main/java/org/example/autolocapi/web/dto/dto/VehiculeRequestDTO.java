package org.example.autolocapi.web.dto.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.autolocapi.domain.CategorieVehicule;
import org.example.autolocapi.domain.StatutVehicule;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeRequestDTO {

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Size(max = 20)
    private String immatriculation;

    @NotBlank(message = "La marque est obligatoire")
    @Size(max = 50)
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(max = 50)
    private String modele;

    @NotNull(message = "Le tarif journalier est obligatoire")
    @DecimalMin(value = "0.0")
    private BigDecimal tarifJournalier;

    private CategorieVehicule categorie;

    private StatutVehicule statut;

    private Long agenceId;
}