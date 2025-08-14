package modelo.entidade;

public enum TipoEstabelecimento {
    MERCADO ("Mercado"),
    LOJA ("Loja"),
    FARMACIA ("Farmácia"),
    RESTAURANTE ("Restaurante"),
    BAR ("Bar"),
    CASA_NOTURNA ("Casa Noturna"),
    TEATRO ("Teatro"),
    SHOPPING ("Shopping"),
    MUSEU ("Museu"),
    HOTEL ("Hotel"),
    LIVRARIA ("Livraria"),
    ACADEMIA ("Academia"),
    CLINICA ("Clínica");

    private final String tipoEstabelecimento;



    TipoEstabelecimento(String tipoEstabelecimento) {
        this.tipoEstabelecimento = tipoEstabelecimento;
    }

    public String getTipoEstabelecimento(){
        return tipoEstabelecimento;
    }
}
