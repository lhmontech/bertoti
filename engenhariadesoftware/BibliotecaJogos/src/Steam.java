import java.util.List;
import java.util.LinkedList;

public class Steam {
    private List<Jogo> jogos = new LinkedList<Jogo>();

    public void cadastrarJogo(Jogo jogo){
        jogos.add(jogo);
    }

    public List<Jogo> buscarJogoTituloProdutora(Jogo jog) {
        List<Jogo> encontrados = new LinkedList<Jogo>();
        for(Jogo jogo:jogos) {
            if (jogo.comparar(jog)) {
                encontrados.add(jogo);
            }
        }
        return encontrados;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }
}