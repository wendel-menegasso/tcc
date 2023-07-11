package br.com.mba.engenharia.de.software.model;

import br.com.mba.engenharia.de.software.negocio.contas.Conta;
import org.springframework.stereotype.Component;

@Component
public class Cliente {

    private Conta account;

    public Conta getAccount() {
        return account;
    }

    public void setAccount(Conta account) {
        this.account = account;
    }
}