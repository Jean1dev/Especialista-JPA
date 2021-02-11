import com.curso.model.Produto;
import org.junit.Assert;
import org.junit.Test;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void busarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("Kindle", produto.getNome());
    }
}
