package br.com.mba.engenharia.de.software.negocio.contas;

public enum TipoContaEnum{
    CC(1, "Conta Corrente"),
    CE(2, "Conta Empresarial"),
    CP(3, "Conta Poupanca"),
    CS(4, "Conta Salarial");
    private int id;
    private String descr;
    public Tipoconta tipoconta = new Tipoconta();

    TipoContaEnum(int id, String descr){
        this.id = id;
        this.descr = descr;
    }

    public Tipoconta getTipoconta(int id){
        for (TipoContaEnum tipoContaEnum: this.getDeclaringClass().getEnumConstants()){
            if (tipoContaEnum.id == id){
                this.tipoconta.setDescr(tipoContaEnum.descr);
                this.tipoconta.setId(id);
                return this.tipoconta;
            }
        }
        return null;
    }

}

