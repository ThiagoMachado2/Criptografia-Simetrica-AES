package org.arquiteturaclienteservidor;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Gera a chave secreta fixa
            SecretKey chaveSecreta = CriptografiaSimetrica.gerarChaveSecreta();
            String chaveEmBase64 = Base64.getEncoder().encodeToString(chaveSecreta.getEncoded());

            System.out.println("Chave Secreta (Base64): " + chaveEmBase64);

            while (true) {
                exibirMenu();
                String escolha = scanner.nextLine();

                switch (escolha) {
                    case "1":
                        criptografarMensagem(scanner, chaveSecreta);
                        break;
                    case "2":
                        descriptografarMensagem(scanner, chaveSecreta);
                        break;
                    case "3":
                        System.out.println("Saindo do programa.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Criptografar uma mensagem");
        System.out.println("2. Descriptografar uma mensagem");
        System.out.println("3. Sair");
        System.out.print("Digite sua escolha: ");
    }

    private static void criptografarMensagem(Scanner scanner, SecretKey chaveSecreta) {
        try {
            System.out.print("Digite a mensagem para criptografar: ");
            String mensagemOriginal = scanner.nextLine();
            String mensagemCriptografada = CriptografiaSimetrica.criptografarMensagem(mensagemOriginal, chaveSecreta);
            System.out.println("Mensagem Criptografada: " + mensagemCriptografada);
        } catch (Exception e) {
            System.err.println("Erro ao criptografar a mensagem: " + e.getMessage());
        }
    }

    private static void descriptografarMensagem(Scanner scanner, SecretKey chaveSecreta) {
        try {
            System.out.print("Digite a mensagem criptografada para descriptografar: ");
            String mensagemCriptografada = scanner.nextLine();
            String mensagemDescriptografada = CriptografiaSimetrica.descriptografarMensagem(mensagemCriptografada, chaveSecreta);
            System.out.println("Mensagem Descriptografada: " + mensagemDescriptografada);
        } catch (Exception e) {
            System.err.println("Erro ao descriptografar a mensagem: " + e.getMessage());
        }
    }
}
