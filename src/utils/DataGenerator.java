package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import dao.Interfaz.abstractDAO;
import models.Cliente;
import models.Factura;
import models.FacturaProducto;
import models.Producto;

public class DataGenerator {
	public static final String CLIENTE = "clientes";
	public static final String FACTURA = "facturas";
	public static final String FACTURAPRODUCTO = "facturas-productos";
	public static final String PRODUCTO = "productos";

	public static void crearTabla(abstractDAO dao) throws Exception {
		dao.createTable();
	}

	public static void cargarDatos(abstractDAO dao, String option) {
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./dataset/" + option + ".csv"));

			switch (option) {
			case CLIENTE:
				parser.forEach(row -> cargarCliente(dao, row));break;
			case FACTURA:
				parser.forEach(row -> cargarFactura(dao, row));break;

			case FACTURAPRODUCTO:
				parser.forEach(row -> cargarFacturaProducto(dao, row));break;

			case PRODUCTO:
				parser.forEach(row -> cargarProducto(dao, row));break;
			}

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void cargarCliente(abstractDAO<Cliente, ?> dao, CSVRecord row) {
		
		try {
			dao.save(new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cargarFactura(abstractDAO<Factura, ?> dao, CSVRecord row) {

		try {
			dao.save(new Factura(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente"))));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cargarFacturaProducto(abstractDAO<FacturaProducto, ?> dao, CSVRecord row) {

		try {
			dao.save(new FacturaProducto(Integer.parseInt(row.get("idFactura")),
					Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad"))));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cargarProducto(abstractDAO<Producto, ?> dao, CSVRecord row) {

		try {
			dao.save(new Producto(Integer.parseInt(row.get("idProducto")), row.get("nombre"),
					Float.parseFloat(row.get("valor"))));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
