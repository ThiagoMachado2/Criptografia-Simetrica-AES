package org.arquiteturaclienteservidor;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class AppInterface {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppInterface::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        SecretKey chaveSecreta = CriptografiaSimetrica.gerarChaveSecreta();
        String chaveEmBase64 = Base64.getEncoder().encodeToString(chaveSecreta.getEncoded());

        System.out.println("Chave Secreta gerada (Base64): " + chaveEmBase64);

        JFrame frame = new JFrame("Criptografia Simétrica - AES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblInfo = new JLabel("Chave Secreta (Base64): " + chaveEmBase64, SwingConstants.CENTER);
        panel.add(lblInfo);

        JButton btnCriptografar = new JButton("Criptografar Mensagem");
        JButton btnDescriptografar = new JButton("Descriptografar Mensagem");
        JButton btnCreditos = new JButton("Créditos");

        panel.add(btnCriptografar);
        panel.add(btnDescriptografar);
        panel.add(btnCreditos);

        frame.add(panel, BorderLayout.CENTER);

        btnCriptografar.addActionListener(e -> criptografarMensagem(chaveSecreta));
        btnDescriptografar.addActionListener(e -> descriptografarMensagem(chaveSecreta));
        btnCreditos.addActionListener(AppInterface::exibirCreditos);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("Interface gráfica inicializada.");
    }

    private static void criptografarMensagem(SecretKey chaveSecreta) {
        String mensagemOriginal = JOptionPane.showInputDialog(null, "Digite a mensagem para criptografar:", "Criptografar", JOptionPane.PLAIN_MESSAGE);
        if (mensagemOriginal != null) {
            try {
                System.out.println("Criptografando a mensagem: " + mensagemOriginal);
                String mensagemCriptografada = CriptografiaSimetrica.criptografarMensagem(mensagemOriginal, chaveSecreta);

                System.out.println("Mensagem Criptografada (Base64): " + mensagemCriptografada);

                JOptionPane.showMessageDialog(null, "Mensagem Criptografada:\n" + mensagemCriptografada, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Mensagem criptografada com sucesso.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criptografar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Erro ao criptografar: " + e.getMessage());
            }
        }
    }

    private static void descriptografarMensagem(SecretKey chaveSecreta) {
        String mensagemCriptografada = JOptionPane.showInputDialog(null, "Digite a mensagem criptografada:", "Descriptografar", JOptionPane.PLAIN_MESSAGE);
        if (mensagemCriptografada != null) {
            try {
                System.out.println("Descriptografando a mensagem: " + mensagemCriptografada);
                String mensagemDescriptografada = CriptografiaSimetrica.descriptografarMensagem(mensagemCriptografada, chaveSecreta);
                JOptionPane.showMessageDialog(null, "Mensagem Descriptografada:\n" + mensagemDescriptografada, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Mensagem descriptografada com sucesso.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao descriptografar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Erro ao descriptografar: " + e.getMessage());
            }
        }
    }

    public static void exibirCreditos(ActionEvent event) {
        String creditos = "Créditos:\n- Thiago Machado\n- Carlos Segundo\n- Joaquim Carvalho\n- Moab Lima";
        JOptionPane.showMessageDialog(null, creditos, "Créditos", JOptionPane.INFORMATION_MESSAGE);

        // Exibe no terminal a ação de créditos
        System.out.println("Exibindo créditos no terminal.");
    }
}
