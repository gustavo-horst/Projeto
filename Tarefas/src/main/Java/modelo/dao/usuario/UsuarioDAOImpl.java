package modelo.dao.usuario;

import java.util.ArrayList;
import java.util.List;

import modelo.entidade.usuario.Usuario;
import modelo.factory.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

	private Connection conexao;

	public UsuarioDAOImpl() {
		  try {
			this.conexao = ConexaoFactory.getConexao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    if (this.conexao == null) {
		        throw new RuntimeException("Falha ao conectar com o banco de dados.");
		    }
	}

	@Override
	public void inserirUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		
		
		String sql = "INSERT INTO usuario (nome_usuario, sobrenome_usuario, apelido_usuario, email_usuario, senha_usuario) VALUES (?, ?, ?, ?, ?)";

		try {

			 stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSobrenome());
			stmt.setString(3, usuario.getApelido());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getSenha());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		
		}finally {

			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	public void deletarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		
		
		
		if (usuario.getId() == null) {
			throw new IllegalArgumentException("ID não encontrado");
		}

		String sql = "DELETE FROM usuario WHERE id = ?";

		try {

			 stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		
		}finally {

			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	public void editarUsuario(Usuario usuario) {
			
			PreparedStatement stmt = null;
			
		
		
		if (usuario.getId() == null) {
			throw new IllegalArgumentException("ID não encontrado");
		}

		String sql = "UPDATE usuario SET nome = ?, sobrenome = ?, Apelido = ?, email = ?, senha = ?, WHERE id = ?";

		try {
			 stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSobrenome());
			stmt.setString(3, usuario.getApelido());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getSenha());
			stmt.setLong(6, usuario.getId());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	public Usuario recuperarUsuario(long id) {
		Usuario usuario = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM usuario WHERE id = ?";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();

				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setApelido(rs.getString("apelido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return usuario;
	}

	@Override
	public List<Usuario> recuperarUsuarios() {
		
		List<Usuario> usuarios = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM usuario";

		try {
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setApelido(rs.getString("apelido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				usuarios.add(usuario);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return usuarios;
	}

}
