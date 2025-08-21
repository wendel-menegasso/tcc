package refactoring.entity.contas;

public class BancoDescr{
    public enum BancoEnum{
        BB("Banco do Brasil"),
        BRADESCO("Bradesco"),
        C6("C6"),
        CAIXA("Caixa Economica"),
        ITAU("Itau"),
        NUBANK("Nubank"),
        BANCO_PAN("Banco Pan"),
        SANTANDER("Santander"),
        OUTROS("Outros");
        private int id;
        private String descr;
        public Banco banco = new Banco();

        BancoEnum(String descr){
            this.descr = descr;
        }

        public String getDescr(){
            return this.descr;
        }

    }



    public String getBanco(int id){
        if (id == 1){
            return BancoEnum.BB.getDescr();
        }
        if (id == 2){
            return BancoEnum.BRADESCO.getDescr();
        }
        if (id == 3){
            return BancoEnum.C6.getDescr();
        }
        if (id == 4){
            return BancoEnum.CAIXA.getDescr();
        }
        if (id == 5){
            return BancoEnum.ITAU.getDescr();
        }
        if (id == 6){
            return BancoEnum.NUBANK.getDescr();
        }
        if (id == 7){
            return BancoEnum.BANCO_PAN.getDescr();
        }
        if (id == 8){
            return BancoEnum.SANTANDER.getDescr();
        }
        if (id == 9){
            return BancoEnum.OUTROS.getDescr();
        }
        return null;
    }
}