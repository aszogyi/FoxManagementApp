package hu.szabolcs.fox;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.validation.Valid;

@Path("/foxes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoxResource {

    @EJB
    private FoxService foxService;

    @GET
    public List<Fox> findAll() {
        return foxService.findAll();
    }

    @POST
    public Response create(@Valid Fox fox) {
        foxService.save(fox);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        foxService.delete(id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/all")
    public Response deleteAll() {
        foxService.deleteAll();
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Fox findById(@PathParam("id") Long id) {
        Fox fox = foxService.findById(id);

        if (fox == null) {
            throw new NotFoundException("Fox not found with id: " + id);
        }

        return fox;
    }
}