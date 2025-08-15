package modelo.dao.endereco;

import java.util.List;

import modelo.entidade.Endereco;

public interface EnderecoDAO {
	
	void inserirEndereco(Endereco endereco);

	void deletarEndereco(Endereco endereco);

	void editarEndereco(Endereco endereco);

	Endereco recuperarEndereco(Long id);
	
	List<Endereco> recuperarEnderecos();

}
