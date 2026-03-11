import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Teste {
    @Test
    public void test() {
        Steam bib = new Steam();
        bib.cadastrarJogo(new Jogo("The Witcher 3", "CD Project", "RPG"));
        assertEquals(bib.getJogos().size(), 1);
        List<Jogo> encontrados = bib.buscarJogoTituloProdutora(new Jogo("The Witcher 3", "CD Project", "RPG"));
        assertEquals(encontrados.get(0).getProdutora(), "CD Project");
    }
}
