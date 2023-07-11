package br.com.mba.engenharia.de.software.negocio.contas;

public enum BancoEnum{
    BB(1, "Banco do Brasil"),
    BANCO_PAN(2, "Banco Pan"),
    BRADESCO(3, "Bradesco"),
    CAIXA(4, "Caixa Economica"),
    C6(5, "C6"),
    NUBANK(6, "Nubank"),
    SANTANDER(7, "Santander"),
    ITAU(8, "Itau"),
    OUTROS(9, "Outros");
    private int id;
    private String descr;
    public Banco banco = new Banco();

    BancoEnum(int id, String descr){
        this.id = id;
        this.descr = descr;
    }

    public Banco getBanco(int id){
       for (BancoEnum bancoEnum: this.getDeclaringClass().getEnumConstants()){
           if (bancoEnum.id == id){
               this.banco.setDescr(bancoEnum.descr);
               this.banco.setId(id);
               return this.banco;
           }
       }
        return null;
    }

}
