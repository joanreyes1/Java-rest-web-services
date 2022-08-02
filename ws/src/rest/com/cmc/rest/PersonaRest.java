package com.cmc.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.Consulta;
import com.cmc.rest.servicios.ManejadorArchivos;
import com.cmc.rest.servicios.ServicioPersonas;

@Path("/personas")
public class PersonaRest {

	private static final String FILE = "C:\\apache-tomcat-9.0.50\\bin\\Cliente.txt";

	// Metodo Modificar
	@Path("/cambiar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Persona modificar(Persona persona) {
		System.out.println(persona);
		try {
			ServicioPersonas.actualizar(persona);
		} catch (ValidationException e) {
			System.out.println("Error al modificar" + e);
		}
		return persona;
	}

	// Metodo Cambiar

	@Path("/cambio")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response cambiar(Persona persona) {
		System.out.println(persona); // return
		Response.status(200).entity(persona).build();
		try {
			ServicioPersonas.actualizar(persona);
			return Response.status(200).entity(persona).build();
		} catch (ValidationException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	@Path("/consultar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersona() {
		ManejadorArchivos manejador = new ManejadorArchivos();
		ArrayList<Persona> clientes = new ArrayList<Persona>();
		try {
			clientes = manejador.leer(FILE);
			return Response.status(200).entity(clientes).build();
		} catch (ArchivoException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	@Path("/buscar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorCedula(Consulta consulta) {
		Persona p = ServicioPersonas.buscarPorCedula(consulta.getCedula());
		return Response.status(200).entity(p).build();
	}

}
