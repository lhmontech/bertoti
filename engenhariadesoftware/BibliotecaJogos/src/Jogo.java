public class Jogo {
    private String titulo;
    private String produtora;
    private String estilo;

    public Jogo(String titulo, String produtora, String estilo) {
        this.titulo = titulo;
        this.produtora = produtora;
        this.estilo = estilo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Boolean comparar(Jogo jogo) {
        if(this.titulo.equals(jogo.titulo) && this.produtora.equals(jogo.produtora) && this.estilo.equals(jogo.estilo)) {
            return true;
        } else {
            return false;
        }
    }
}
