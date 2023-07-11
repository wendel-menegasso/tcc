package br.com.mba.engenharia.de.software.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptrografia {
    public boolean criptrografia(String senhaAdmin){
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte messageDigestSenhaAdmin[] = new byte[0];
        try {
            messageDigestSenhaAdmin = algorithm.digest(senhaAdmin.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder hexStringSenhaAdmin = new StringBuilder();
        for (byte b : messageDigestSenhaAdmin) {
            hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
        }
        String senhahexAdmin = hexStringSenhaAdmin.toString();

        System.out.println(senhahexAdmin);
        return comparar("123456", senhahexAdmin);
    }

    public String criptografar(String senhaOriginal){
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte messageDigestSenhaAdmin[] = new byte[0];
        try {
            messageDigestSenhaAdmin = algorithm.digest(senhaOriginal.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder hexStringSenhaAdmin = new StringBuilder();
        for (byte b : messageDigestSenhaAdmin) {
            hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
        }
        String senhahexOriginal = hexStringSenhaAdmin.toString();
        return senhahexOriginal;
    }


    public boolean comparar(String senhaCorreta, String senhaDigitada){
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte messageDigestSenhaAdmin[] = new byte[0];
        try {
            messageDigestSenhaAdmin = algorithm.digest(senhaCorreta.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder hexStringSenhaAdmin = new StringBuilder();
        for (byte b : messageDigestSenhaAdmin) {
            hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
        }
        String senhahexAdmin = hexStringSenhaAdmin.toString();
        return senhahexAdmin.equals(senhaDigitada);
    }
}
