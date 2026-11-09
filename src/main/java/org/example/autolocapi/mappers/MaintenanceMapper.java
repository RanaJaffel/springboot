package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;
import org.example.autolocapi.domain.Maintenance;
import org.example.autolocapi.web.dto.dto.MaintenanceRequestDTO;
import org.example.autolocapi.web.dto.dto.MaintenanceResponseDTO;

@Component
public class MaintenanceMapper {

    public Maintenance toEntity(MaintenanceRequestDTO dto) {
        if (dto == null) return null;

        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(dto.getDateDebut());
        maintenance.setDateFin(dto.getDateFin());
        maintenance.setDescription(dto.getDescription());
        // Le véhicule doit être associé dans le service à partir de dto.getVehiculeId().
        return maintenance;
    }

    public MaintenanceResponseDTO toResponseDTO(Maintenance maintenance) {
        if (maintenance == null) return null;

        MaintenanceResponseDTO dto = new MaintenanceResponseDTO();
        dto.setId(maintenance.getIdMaintenance());
        dto.setDateDebut(maintenance.getDateDebut());
        dto.setDateFin(maintenance.getDateFin());
        dto.setDescription(maintenance.getDescription());

        if (maintenance.getVehicule() != null) {
            dto.setVehiculeId(maintenance.getVehicule().getIdVehicule());
        }
        return dto;
    }
}
