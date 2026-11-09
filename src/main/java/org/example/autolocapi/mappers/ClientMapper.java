package org.example.autolocapi.mappers;

import org.springframework.stereotype.Component;
import org.example.autolocapi.domain.Client;
import org.example.autolocapi.web.dto.dto.ClientRequestDTO;
import org.example.autolocapi.web.dto.dto.ClientResponseDTO;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Client client = new Client();

        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setNumPermis(dto.getNumPermis());

        if (dto.getDateInscription() != null) {
            client.setDateInscription(dto.getDateInscription().toString());
        }

        return client;
    }

    public ClientResponseDTO toResponseDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientResponseDTO dto = new ClientResponseDTO();

        dto.setId(client.getIdClient());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setEmail(client.getEmail());
        dto.setTelephone(client.getTelephone());
        dto.setNumPermis(client.getNumPermis());
        dto.setDateInscription(client.getDateInscription());

        return dto;
    }
}