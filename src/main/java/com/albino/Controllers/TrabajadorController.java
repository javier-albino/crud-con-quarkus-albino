package com.albino.Controllers;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.albino.model.Trabajador;
import com.albino.model.Trabajo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.util.List;

@Path("/api/trabajadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrabajadorController {
    // Obtener todos los trabajadores
    @GET
    public List<Trabajador> obtenerTodos() {
        return Trabajador.listAll();
    }

    // Obtener un trabajador por ID
    @GET
    @Path("/{id}")
    public Trabajador obtenerPorId(@PathParam("id") Long id) {
        return Trabajador.findById(id);
    }

    // Crear un nuevo trabajador y asignarle un trabajo
    @POST
    @Transactional
    public Response crearTrabajador(Trabajador trabajador, @QueryParam("trabajoId") Long trabajoId) {
        if (trabajoId != null) {
            Trabajo trabajo = Trabajo.findById(trabajoId);
            if (trabajo != null) {
                trabajador.setTrabajo(trabajo);
            }
        }
        trabajador.persist();
        return Response.status(Response.Status.CREATED).entity(trabajador).build();
    }

    // Actualizar un trabajador existente
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizarTrabajador(@PathParam("id") Long id, Trabajador trabajadorActualizado) {
        Trabajador trabajador = Trabajador.findById(id);
        if (trabajador == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        trabajador.nombre = trabajadorActualizado.nombre;
        trabajador.email = trabajadorActualizado.email;
        // Puedes actualizar otros campos según sea necesario

        return Response.status(Response.Status.OK).entity(trabajador).build();
    }

    // Eliminar un trabajador por ID
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminarTrabajador(@PathParam("id") Long id) {
        Trabajador trabajador = Trabajador.findById(id);
        if (trabajador == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        trabajador.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
