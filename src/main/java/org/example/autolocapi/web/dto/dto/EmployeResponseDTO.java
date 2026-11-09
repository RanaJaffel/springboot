
package org.example.autolocapi.web.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.autolocapi.domain.RoleEmploye;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private RoleEmploye role;
    private Long agenceId;
}
