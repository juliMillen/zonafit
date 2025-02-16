package zona_fit.presentacion;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

public class ZonaFit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		zonaFitApp();
	}

	private static void zonaFitApp() {
		// TODO Auto-generated method stub
		boolean salir = false;
		Scanner consola = new Scanner(System.in);
		IClienteDAO clienteDAO = new ClienteDAO();
		while(!salir) {
			try {
				int opcion = mostrarMenu(consola);
				salir= ejecutarOpciones(consola,opcion, clienteDAO);
			}catch(Exception ex) {
				System.out.println("Error al ejecutar opciones: "+ex.getMessage());
			}
			System.out.println();
		}
	}



	private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO) {
		boolean salir = false;
		switch(opcion) {
		case 1:
			System.out.println("LISTADO DE CLIENTES");
			List<Cliente> clientes = clienteDAO.listarClientes();
			clientes.forEach(System.out::println);
			break;
		case 2:
			System.out.println("ingrese un id para buscar: ");
			int id = Integer.parseInt(consola.nextLine());
			Cliente buscado = new Cliente(id);
			boolean encontrado= clienteDAO.BuscarClientePorId(buscado);
			if(encontrado) {
					System.out.println("Cliente encontrado: "+ buscado);
				}else {
					System.out.println("Cliente no encontrado");
				}
			break;
			
		case 3: 
			System.out.println("Ingrese nombre del cliente: ");
			String nombre = consola.nextLine();
			System.out.println("Ingrese apellido del cliente: ");
			String apellido = consola.nextLine();
			System.out.println("Ingrese membresia del cliente: ");
			int membresia = Integer.parseInt(consola.nextLine());
			Cliente nuevoCliente = new Cliente(nombre,apellido,membresia);
			boolean agregado = clienteDAO.AgregarCliente(nuevoCliente);
			if(agregado) {
				System.out.println("Se ha agregado correctamente el cliente");
			}
			else {
				System.out.println("No se ha podido agregar el cliente");
			}
			break;
			
		case 4:
			System.out.println("Nombre: ");
			String nombreAc = consola.nextLine();
			System.out.println("Apellido: ");
			String apellidoAc = consola.nextLine();
			System.out.println("Membresia: ");
			int membresiaAc = Integer.parseInt(consola.nextLine());
			System.out.println("ID: ");
			int idAc = Integer.parseInt(consola.nextLine());
			Cliente aModificar = new Cliente(idAc,nombreAc,apellidoAc,membresiaAc);
			boolean actualizado = clienteDAO.modificarCliente(aModificar);
			if(actualizado) {
				System.out.println("Cliente actualizado correctamente: "+ aModificar);
			}
			else {
				System.out.println("No se ha podido actualizar");
			}
			break;
			
		case 5:
			System.out.println("Ingrese el ID del cliente a eliminar: ");
			int idCliente = Integer.parseInt(consola.nextLine());
			Cliente aEliminar = new Cliente(idCliente);
			boolean eliminado = clienteDAO.eliminarCliente(aEliminar);
			if(eliminado) {
				System.out.println("Cliente eliminado correctamente");
			}
			else{
					System.out.println("No se ha podido eliminar el cliente");
				}
			break;
		case 6:
			System.out.println("Vuelva pronto!");
			salir = true;
			break;
			
		default:
				System.out.println("Opcion invalida");
				break;
		}
		return salir;
	}

	private static int mostrarMenu(Scanner consola) {
		// TODO Auto-generated method stub
		System.out.println("***ZONA FIT (GYM)****\n"
				+"\n 1- Listar Clientes"
				+"\n 2- Buscar Cliente"
				+"\n 3- Agregar Cliente"
				+"\n 4- Modificar Cliente"
				+"\n 5- Eliminar Cliente"
				+"\n 6- Salir"
				+"\n Elija una opcion por favor: ");
		return Integer.parseInt(consola.nextLine());
	}
	

	
	

}
