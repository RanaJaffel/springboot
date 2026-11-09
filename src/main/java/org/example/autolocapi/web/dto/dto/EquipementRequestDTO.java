
package org.example.autolocapi.web.dto.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipementRequestDTO {

    @NotBlank(message = "Le libellé de l'équipement est obligatoire")
    private String libelle;

    private Set<Long> vehiculeIds;
}
