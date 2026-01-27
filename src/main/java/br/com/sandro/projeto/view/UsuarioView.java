package br.com.sandro.projeto.view;

import br.com.sandro.projeto.controller.UsuarioController;
import java.util.Scanner;

public class UsuarioView {
    private UsuarioController controller = new UsuarioController();
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Deletar");
    }
}
