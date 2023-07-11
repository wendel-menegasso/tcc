package br.com.mba.engenharia.de.software.security;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class ComplexidadeSenha {
    public boolean isStronger(String senha){
        String[] digitosSenha = splitString(senha);
        if(maxLength(senha) && minLength(senha) && haveSymbols(digitosSenha) && haveHaveCapitalLetters(digitosSenha) && haveNumbers(digitosSenha)){
            return true;
        }
        else{
            return false;
        }
    }

    private String[] splitString(String senha){
        String[] digitosSenha = new String[senha.length()];
        for (int index = 0; index < senha.length(); index ++){
            if (index < senha.length() -1){
                digitosSenha[index] = senha.substring(index, index + 1);
            }
            else{
                digitosSenha[index] = senha.substring(index);
            }
        }
        return digitosSenha;
    }


    private boolean minLength(String senha){
        return senha.length() > 12;
    }

    private boolean maxLength(String senha){
        return senha.length() < 25;
    }

    private boolean haveSymbols(String[] senha) {
        for (String digito : senha){
            if(digito.matches("([@+!+#+$+%+&+_+])")){
                return true;
            }
        }
        return false;
    }
    private boolean haveHaveCapitalLetters(String[] senha)
    {
        for (String digito : senha){
            if(digito.matches("([A-Z])")){
                return true;
            }
        }
        return false;
    }
    private boolean haveNumbers(String[] senha){
        for (String digito : senha){
            if(digito.matches("([0-9])")){
                return true;
            }
        }
        return false;
    }

}
