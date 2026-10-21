
package org.example.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Client;
import org.example.autolocapi.exception.ResourceNotFoundException;
import org.example.autolocapi.service.IClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    @Operation(summary = "Lister les clients")
    public List<Client> retrieveAllClients() {
        return clientService.retrieveAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un client")
    public Client retrieveClient(@PathVariable("id") Long idClient) {
        Client client = clientService.retrieveClient(idClient);

        if (client == null) {
            throw new ResourceNotFoundException("Client", idClient);
        }

        return client;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un client")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public Client updateClient(@PathVariable("id") Long idClient,
                               @RequestBody Client client) {
        if (clientService.retrieveClient(idClient) == null) {
            throw new ResourceNotFoundException("Client", idClient);
        }

        client.setIdClient(idClient);
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un client")
    public void removeClient(@PathVariable("id") Long idClient) {
        if (clientService.retrieveClient(idClient) == null) {
            throw new ResourceNotFoundException("Client", idClient);
        }

        clientService.removeClient(idClient);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer plusieurs clients")
    public List<Client> addClients(@RequestBody List<Client> clients) {
        return clientService.addClients(clients);
    }
}
