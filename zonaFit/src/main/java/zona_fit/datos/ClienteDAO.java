package zona_fit.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

public class ClienteDAO implements IClienteDAO {

	@Override
	public List<Cliente> listarClientes() {

		List<Cliente> listaClientes = new ArrayList<>();
		PreparedStatement ps;
		ResultSet rs;
		Connection con = Conexion.getConnection();
		String sql = "SELECT * FROM cliente ORDER BY id";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setMembresia(rs.getInt("membresia"));
				listaClientes.add(cliente);
			}
			
		}catch(Exception ex) {
			System.out.println("Error al listar clientes: "+ ex.getMessage());
		}
		finally {
		 try {
			 con.close();
		 }catch(Exception e) {
			 System.out.println("Error " + e.getMessage());
		 }
		}
		return listaClientes;
	}

	@Override
	public boolean AgregarCliente(Cliente cliente) {
		PreparedStatement ps;
		Connection con = Conexion.getConnection();
		String sql = "INSERT INTO cliente(nombre,apellido,membresia)" 
		+ " VALUES(?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setInt(3, cliente.getMembresia());
			ps.execute();
			return true;
		}catch(Exception ex) {
			System.out.println("Error al agregar un cliente"+ex.getMessage());
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Error al cerrar la conexion"+ e.getMessage());
			}
		}
		return false;
	}

	@Override
	public boolean BuscarClientePorId(Cliente cliente) {
		PreparedStatement ps;
		ResultSet rs;
		Connection con = Conexion.getConnection();
		String sql = "SELECT * FROM cliente WHERE id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cliente.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setMembresia(rs.getInt("membresia"));
				return true;
			}
			
		}catch(Exception ex) {
			System.out.println("Error al buscar el id deseado "+ ex.getMessage());
		}
		finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Error "+ e.getMessage());
			}
		}
		return false;
	}

	@Override
	public boolean modificarCliente(Cliente cliente) {
		PreparedStatement ps;
		Connection con = Conexion.getConnection();
		String sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? "+ " WHERE id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setInt(3, cliente.getMembresia());
			ps.setInt(4, cliente.getId());
			ps.execute();
			return true;
		}catch(Exception ex) {
			System.out.println("Error al intentar modificar los datos"+ex.getMessage());
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Error al cerrar la conexion"+ e.getMessage());
			}
		}
		return false;
	}

	@Override
	public boolean eliminarCliente(Cliente cliente) {
		PreparedStatement ps;
		Connection con = Conexion.getConnection();
		String sql = "DELETE FROM cliente WHERE id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cliente.getId());
			ps.execute();
			return true;
		}catch (Exception ex) {
			System.out.println("Error al intentar eliminar el cliente "+ ex.getMessage());
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Error al cerrar la conexion "+ e.getMessage());
			}
		}
		return false;
	}
	
	public static void main(String[] args) {

		IClienteDAO clienteDAO = new ClienteDAO();
		
		//buscar por ID
		/*Cliente cliente1 = new Cliente(2);
		System.out.println("Cliente buscado: "+cliente1);
		boolean encontrado = clienteDAO.BuscarClientePorId(cliente1);
		if(encontrado) {
			System.out.println("Cliente encontrado: "+ cliente1);
		}
		else {
			System.out.println("No se ha encontrado ningun cliente con ese ID");
		}*/
		
		//agregar cliente
		/*Cliente nuevoCliente = new Cliente("Cristian","Gutierrez",350);
		boolean agregado = clienteDAO.AgregarCliente(nuevoCliente);
		if(agregado) {
			System.out.println("Se ha agregado correctamente el nuevo cliente: "+nuevoCliente);
		}else {
			System.out.println("No se ha podido agregar el cliente");
		}*/
		
		
		//Modificar cliente
		/*Cliente modificarCliente = new Cliente(2,"Carlos Alberto","Millen",244);
		boolean modificado = clienteDAO.modificarCliente(modificarCliente);
		if(modificado) {
			System.out.println("Cliente modificado correctamente: "+ modificado);
		}else {
			System.out.println("No se ha podido modificar");
		}*/
		
		
		//Eliminar cliente
		Cliente aEliminar = new Cliente(5);
		boolean eliminado = clienteDAO.eliminarCliente(aEliminar);
		if(eliminado) {
			System.out.println("Cliente eliminado correctamente: "+eliminado);
		}else {
			System.out.println("No se ha podido eliminar el cliente");
		}
		
		// listar clientes
		System.out.println("******** LISTAR CLIENTES******");
		List<Cliente> clientes =clienteDAO.listarClientes();
		clientes.forEach(System.out::println);
	}

}
