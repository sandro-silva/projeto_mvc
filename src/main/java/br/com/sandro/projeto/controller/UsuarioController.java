package br.com.sandro.projeto.controller;

import br.com.sandro.projeto.dao.UsuarioDAO;
import br.com.sandro.projeto.model.Usuario;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void salvar(Usuario usuario) {
        usuarioDAO.salvar(usuario);
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public void deletar(Long id) {
        usuarioDAO.deletar(id);
    }
}
