public enum TipoArquivoRetorno {
    BANCO_BRASIL {
        @Override
        public void processar() {

        }
    },
    BRADESCO {
        @Override
        public void processar() {

        }
    };

    public abstract void processar();
}
