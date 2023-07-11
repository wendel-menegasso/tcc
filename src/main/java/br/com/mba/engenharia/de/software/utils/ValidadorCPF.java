package br.com.mba.engenharia.de.software.utils;

public class ValidadorCPF {
    public boolean isValid(String cpf){
        if (cpf.length() != 11){
            return  false;
        }
        String[] digitosCPF = new String[cpf.length()];
        for (int index = 0; index < cpf.length(); index ++){
            if (index < cpf.length() -1){
                digitosCPF[index] = cpf.substring(index, index + 1);
            }
            else{
                digitosCPF[index] = cpf.substring(index);
            }
        }
        int somaDigitos = 0;
        for (String digitos : digitosCPF){
            somaDigitos = somaDigitos + Integer.parseInt(digitos);
        }

        String[] somaDigitosStrArray = {String.valueOf(somaDigitos).substring(0,1), String.valueOf(somaDigitos).substring(1)};
        if (somaDigitosStrArray[0].equals(somaDigitosStrArray[1])){
            return true;
        }
        else{
            return false;
        }
    }

}
