package com.test.cilia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.test.cilia.domain.Client;
import com.test.cilia.error.NotFoundException;
import com.test.cilia.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado. id= " + id));
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public void update(Client client) {

        if (!clientRepository.existsById(client.getId())) {
            throw new NotFoundException("Cliente não encontrado. id= " + client.getId());

        }

        clientRepository.saveAndFlush(client);
    }

    public void deleteById(Long id) {

        if (!clientRepository.existsById(id)) {
            throw new NotFoundException("Cliente não encontrado. id= " + id);

        }

        clientRepository.deleteById(id);
    }

}
