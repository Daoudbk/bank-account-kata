package com.katabankaccount.katabankaccount.service;

import com.katabankaccount.katabankaccount.model.Client;
import com.katabankaccount.katabankaccount.repository.ClientRepository;
import com.katabankaccount.katabankaccount.util.ValidatorUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.notEmpty;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Inject
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = requireNonNull(clientRepository);
    }

    public Client createClient(Client client) {
        return clientRepository.save(ValidatorUtil.validate(client));
    }

    public Client findClient(Integer id) {
        if (clientRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Unable to locate client id: " + id, ClientService.class.getName());
        }
        return clientRepository.findById(id).get();
    }

}
