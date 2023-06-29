package com.test.cilia.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.cilia.domain.Client;
import com.test.cilia.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/client")
public class ClientResource {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {

        List<Client> clients = clientService.findAll();

        if (clients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findClientById(@PathVariable("id") Long id) {
        Client client;
        client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }
    
    @PostMapping
    public ResponseEntity<Void> saveClient(@RequestBody Client client, HttpServletRequest request) {

        clientService.save(client);
        String path = request.getRequestURI() + client.getId();

        return ResponseEntity.created(URI.create(path)).build();
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {

        clientService.update(client);

        return ResponseEntity.ok(client);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable("id") Long id){
        
        clientService.deleteById(id);
        
        return ResponseEntity.noContent().build();
        }

}
