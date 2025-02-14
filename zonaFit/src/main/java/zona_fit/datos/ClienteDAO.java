package zona_fit.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		// listar clientes
		System.out.println("******** LISTAR CLIENTES******");
		IClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes =clienteDAO.listarClientes();
		clientes.forEach(System.out::println);
		
		//buscar por ID
		Cliente cliente1 = new Cliente(2);
		System.out.println("Cliente buscado: "+cliente1);
		boolean encontrado = clienteDAO.BuscarClientePorId(cliente1);
		if(encontrado) {
			System.out.println("Cliente encontrado: "+ cliente1);
		}
		else {
			System.out.println("No se ha encontrado ningun cliente con ese ID");
		}
	}

}
