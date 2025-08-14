package modelo.dao.usuario;

import java.util.List;

import modelo.entidade.usuario.Usuario;

public interface UsuarioDAO {
	
		void inserirUsuario (Usuario usuario);
	
		void deletarUsuario (Usuario usuario);
	
		void editarUsuario (Usuario usuario);
		
		Usuario recuperarUsuario(long id);
		
		List<Usuario> recuperarUsuarios();
		
		Usuario validarConexao (String email, String senha);
	
	


}