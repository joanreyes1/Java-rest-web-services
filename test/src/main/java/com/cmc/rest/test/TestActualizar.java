package com.cmc.rest.test;

import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ServicioPersonas;

public class TestActualizar {

	public static void main(String[] args) {
		Persona p = new Persona("23232323", "joan", "reyes", 24);
		try {
			ServicioPersonas.actualizar(p);
		} catch (ValidationException e) {
			System.out.println("Error al actualizar la persona" + e);
		}

	}

}
