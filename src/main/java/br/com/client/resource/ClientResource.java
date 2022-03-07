package br.com.client.resource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import br.com.client.entity.Client;
import br.com.client.controller.ClientController;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ClientResource {
    @Inject
    private ClientController clientController;

    @GET
    public List<Client> findAll() {
        return Client.listAll();
    }

    @POST
    @Transactional
    public Response create(Client client) {
        Client.persist(client);
        return Response.ok(client).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Client client) {
        if (clientController.isClientNameIsNotEmpty(client)) {
            return Response.ok("Client name is empty").status(400).build();
        }
        Client clientEntity = clientController.update(id, client);
        return Response.ok(clientEntity).status(200).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Client clientEntity = Client.findById(id);

        if (clientEntity == null) {
            throw new WebApplicationException("Client with id of " + id + " does not exist.",
                    Response.Status.NOT_FOUND);
        }

        clientEntity.delete();
        return Response.ok().status(200).build();
    }
}