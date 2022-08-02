package com.cmc.rest.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.DateUtil;
import com.cmc.rest.entidades.Persona;

public class ManejadorArchivos {

	public ArrayList<Persona> leer(String path) throws ArchivoException {
		File file = new File(path);
		System.out.println(file.getAbsolutePath() + "asdsd");
		BufferedReader br = null;
		FileReader fileReader = null;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String linea = "";
			String[] partes;
			Persona p;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
				partes = linea.split("-");
				p = new Persona(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]));
				p.setFechaCreacion(DateUtil.convertir(new Date()));
				personas.add(p);
			}
			/*
			 * int leido = fileReader.read(); System.out.println("leido :" +
			 * leido); leido = fileReader.read(); System.out.println("leido :" +
			 * leido); leido = fileReader.read(); System.out.println("leido :" +
			 * leido);
			 */
		} catch (FileNotFoundException e) {
			System.out.println("Error a abrir el archivo" + e);
			throw new ArchivoException("no se existe archivo");
		} catch (IOException e) {
			System.out.println("Error al leer archivo" + e);
			throw new ArchivoException("error leer el archivo");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("Error en el BufferedReader" + e);
			}

			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error en el fileReader" + e);
			}
		}

		return personas;

	}
}
