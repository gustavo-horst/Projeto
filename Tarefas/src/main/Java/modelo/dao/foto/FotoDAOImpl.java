package modelo.dao.foto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.entidade.Foto;
import modelo.factory.conexao.ConexaoFactory;


public class FotoDAOImpl implements FotoDAO{
    private ConexaoFactory conexao;

    public FotoDAOImpl() {
        conexao = new ConexaoFactory();
    }

    @Override
    public void adicionarFoto(Foto foto) {

        String sql = "INSERT INTO foto (caminho_arquivo_foto, conteudo_foto) VALUES (?, ?)";

        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, foto.getCaminhoArquivo());
            stmt.setBytes(2, foto.getConteudoFoto());
            stmt.executeUpdate();
            

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    foto.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    @Override
    public void deletarFoto(Foto foto) {
        String sql = "DELETE FROM foto WHERE id = ?";

        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, foto.getId());
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Foto deletada com sucesso!");
            } else {
                System.out.println("Nenhuma foto encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }

    @Override
    public void atualizarFoto(Foto foto) {
        String sql = "UPDATE foto SET caminhoArquivo = ?, conteudoFoto = ? WHERE id = ?";

        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, foto.getCaminhoArquivo());
            stmt.setBytes(2, foto.getConteudoFoto());
            stmt.setLong(3, foto.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Foto atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma foto encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }

    @Override
    public Foto recuperarFoto(Foto foto) {
        String sql = "SELECT id, caminhoArquivo, conteudoFoto FROM foto WHERE id = ?";
        //Foto foto = null;

        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, foto.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   // foto = new Foto();
                    foto.setId(rs.getLong("id"));
                    foto.setCaminhoArquivo(rs.getString("caminhoArquivo"));
                    foto.setConteudoFoto(rs.getBytes("conteudoFoto"));
                } else {
                    System.out.println("Nenhuma foto encontrada com o ID fornecido.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        return foto;
    }
}
