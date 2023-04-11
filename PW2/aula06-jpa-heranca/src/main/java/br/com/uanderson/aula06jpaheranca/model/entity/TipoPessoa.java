package br.com.uanderson.aula06jpaheranca.model.entity;

public enum TipoPessoa {
    PESSOA_FISICA(1){
        @Override
        public Pessoa pessoa() {
            return new PessoaFisica();
        }
    },
    PESSOA_JURIDICA(2){
        @Override
        public Pessoa pessoa() {
            return new PessoaJuridica();
        }
    };

    private final int VALOR;

    TipoPessoa(int VALOR) {
        this.VALOR = VALOR;
    }

    public int getVALOR() {
        return VALOR;
    }

    public abstract Pessoa pessoa();
}//class
