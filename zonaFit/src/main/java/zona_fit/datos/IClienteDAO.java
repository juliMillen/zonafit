package zona_fit.datos;

import java.util.List;

import zona_fit.dominio.Cliente;

public interface IClienteDAO{

	List<Cliente> listarClientes();
	boolean AgregarCliente(Cliente cliente);
	boolean BuscarClientePorId(Cliente cliente);
	boolean modificarCliente(Cliente cliente);
	boolean eliminarCliente(Cliente cliente);
}
