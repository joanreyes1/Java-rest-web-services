package com.cmc.rest.servicios;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;

public class ServicioPersonas {
	private static final String FILE = "C:\\apache-tomcat-9.0.50\\bin\\Cliente.txt";

	public static Persona actualizar(Persona persona) throws ValidationException {
		if (persona.getNombre().length() <= 3) {
			System.out.println(persona + "uaaaaaaa");
			throw new ValidationException("El nombre es muy corto");
		} else if (persona.getApellido().length() <= 3) {
			throw new ValidationException("El apellido es muy corto");
		} else {
			System.out.println(persona.getNombre().toUpperCase());
			System.out.println(persona.getApellido().toUpperCase());
		}
		return persona;
	}

	public static Persona buscarPorCedula(String cedula) {
		ManejadorArchivos ma = new ManejadorArchivos();
		Persona encontrada = null;
		try {
			for (Persona persona : ma.leer(FILE)) {
				if (persona.getCedula().equals(cedula)) {
					encontrada = new Persona(persona.getCedula(), persona.getNombre(), persona.getApellido(),
							persona.getEdad());
					encontrada.setFechaCreacion(persona.getFechaCreacion());
				}
			}
		} catch (ArchivoException e) {
			System.out.println("Error no se pudo entrar " + e);
		}

		return encontrada;
	}
}
