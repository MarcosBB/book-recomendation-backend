package com.imd.br.bookRecomendation.Enum;

public enum Marketplace {
    AMAZON,
    MERCADO_LIVRE;

    @Override
    public String toString() {
        // Formata o nome de forma amigável
        switch (this) {
            case AMAZON:
                return "Amazon";
            case MERCADO_LIVRE:
                return "Mercado Livre";
            default:
                throw new IllegalArgumentException();
        }
    }
}