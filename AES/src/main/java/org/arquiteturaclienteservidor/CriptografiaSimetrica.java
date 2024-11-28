package org.arquiteturaclienteservidor;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CriptografiaSimetrica {

    public static SecretKey gerarChaveSecreta() {
        byte[] chaveFixa = "teste9090teste90".getBytes(); // 16 bytes
        return new SecretKeySpec(chaveFixa, "AES");
    }

    public static String criptografarMensagem(String mensagem, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] mensagemCriptografada = cipher.doFinal(mensagem.getBytes());
        return Base64.getEncoder().encodeToString(mensagemCriptografada);
    }

    public static String descriptografarMensagem(String mensagemCriptografada, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] mensagemDecodificada = Base64.getDecoder().decode(mensagemCriptografada);
        byte[] mensagemOriginal = cipher.doFinal(mensagemDecodificada);
        return new String(mensagemOriginal);
    }
}
