
package org.example.autolocapi.web.dto.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipementResponseDTO {

    private Long id;

    private String libelle;

    private Set<Long> vehiculeIds;
}
