package br.com.client.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.client.entity.Client;

@ApplicationScoped
public class ClientController {

    public Client update(Long id, Client client) {
        Client clientEntity = Client.findById(id);

        if (clientEntity == null) {
            throw new WebApplicationException("Client with id of " + id + " does not exist.",
                    Response.Status.NOT_FOUND);
        }

        clientEntity.setName(client.getName());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPhone(client.getPhone());

        return clientEntity;
    }

    /**
     * This method is main purpose to show simple "Business" example.
     * 
     * @param client
     * @return
     */
    public boolean isClientNameIsNotEmpty(Client client) {
        return client.getName().isEmpty();
    }
}
