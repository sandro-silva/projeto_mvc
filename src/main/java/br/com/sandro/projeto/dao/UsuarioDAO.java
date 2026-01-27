package br.com.sandro.projeto.dao;

import br.com.sandro.projeto.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // CREATE
    public void salvar(Usuario u) {
        String sql = """
            INSERT INTO usuarios
            (nome, quantidade_horas, participou, observacao)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setInt(2, u.getQuantidadeHoras());
            ps.setBoolean(3, u.isParticipou());
            ps.setString(4, u.getObservacao());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ
    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> lista = new ArrayList<>();

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id"));
                u.setNome(rs.getString("nome"));
                u.setQuantidadeHoras(rs.getInt("quantidade_horas"));
                u.setParticipou(rs.getBoolean("participou"));
                u.setObservacao(rs.getString("observacao"));
                lista.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // UPDATE
    public void atualizar(Usuario u) {
        String sql = """
            UPDATE usuarios
            SET nome = ?, quantidade_horas = ?, participou = ?, observacao = ?
            WHERE id = ?
        """;

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setInt(2, u.getQuantidadeHoras());
            ps.setBoolean(3, u.isParticipou());
            ps.setString(4, u.getObservacao());
            ps.setLong(5, u.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE
    public void deletar(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
