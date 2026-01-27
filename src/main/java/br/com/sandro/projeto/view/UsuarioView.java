package br.com.sandro.projeto.view;

import br.com.sandro.projeto.controller.UsuarioController;
import br.com.sandro.projeto.model.Usuario;

import java.util.List;
import java.util.Scanner;

public class UsuarioView {
    private UsuarioController controller = new UsuarioController();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int opcao;

        do {
            menu();
            opcao = sc.nextInt();
            sc.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> deletar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void menu() {
        System.out.println("\n=== SISTEMA DE PARTICIPANTES ===");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Deletar");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // CREATE
    private void cadastrar() {
        Usuario usuario = new Usuario();

        System.out.print("Nome: ");
        usuario.setNome(sc.nextLine());

        System.out.print("Quantidade de horas: ");
        usuario.setQuantidadeHoras(sc.nextInt());
        sc.nextLine();

        // SIM / NÃO -> boolean
        boolean participou = false;
        while (true) {
            System.out.print("Participou? (SIM/NÃO): ");
            String resp = sc.nextLine().trim().toUpperCase();

            if (resp.equals("SIM")) {
                participou = true;
                break;
            } else if (resp.equals("NÃO") || resp.equals("NAO")) {
                participou = false;
                break;
            } else {
                System.out.println("Resposta inválida. Digite SIM ou NÃO.");
            }
        }
        usuario.setParticipou(participou);

        System.out.print("Observação: ");
        usuario.setObservacao(sc.nextLine());

        controller.salvar(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // READ
    private void listar() {
        List<Usuario> lista = controller.listar();

        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        for (Usuario u : lista) {
            System.out.println(
                    "ID: " + u.getId() +
                            " | Nome: " + u.getNome() +
                            " | Horas: " + u.getQuantidadeHoras() +
                            " | Participou: " + (u.isParticipou() ? "SIM" : "NÃO") +
                            " | Obs: " + u.getObservacao()
            );
        }
    }

    // UPDATE
    private void atualizar() {
        Usuario usuario = new Usuario();

        System.out.print("ID do usuário: ");
        usuario.setId(sc.nextLong());
        sc.nextLine();

        System.out.print("Novo nome: ");
        usuario.setNome(sc.nextLine());

        System.out.print("Nova quantidade de horas: ");
        usuario.setQuantidadeHoras(sc.nextInt());
        sc.nextLine();

        boolean participou = false;
        while (true) {
            System.out.print("Participou? (SIM/NÃO): ");
            String resp = sc.nextLine().trim().toUpperCase();

            if (resp.equals("SIM")) {
                participou = true;
                break;
            } else if (resp.equals("NÃO") || resp.equals("NAO")) {
                participou = false;
                break;
            } else {
                System.out.println("Resposta inválida. Digite SIM ou NÃO.");
            }
        }
        usuario.setParticipou(participou);

        System.out.print("Nova observação: ");
        usuario.setObservacao(sc.nextLine());

        controller.atualizar(usuario);
        System.out.println("Usuário atualizado!");
    }

    // DELETE
    private void deletar() {
        System.out.print("ID do usuário: ");
        Long id = sc.nextLong();
        sc.nextLine();

        controller.deletar(id);
        System.out.println("Usuário removido!");
    }
}
