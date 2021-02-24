import com.curso.dto.ProdutoDto;
import com.curso.model.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JPQLTest extends EntityManagerTest {

    private static final int LIMITE_INSERCOES = 4;

    @Test
    public void inserirEmLote() {
        InputStream in = JPQLTest.class.getClassLoader()
                .getResourceAsStream("importar.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        entityManager.getTransaction().begin();

        int contadorInsercoes = 0;

        for (String linha : reader.lines().collect(Collectors.toList())) {
            if (linha.isBlank()) {
                continue;
            }

            String[] produtoColuna = linha.split(";");
            Produto produto = new Produto();
            produto.setNome(produtoColuna[0]);
            produto.setDescricao(produtoColuna[1]);
            produto.setPreco(new BigDecimal(produtoColuna[2]));

            entityManager.persist(produto);

            if (++contadorInsercoes == LIMITE_INSERCOES) {
                entityManager.flush();
                entityManager.clear();

                contadorInsercoes = 0;

                System.out.println("---------------------------------");
            }
        }

        entityManager.getTransaction().commit();
    }

    @Test
    public void ordenarResultados() {
        String jpql = "select c from Cliente c order by c.nome asc";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }

    @Test
    public void passarParametroDate() {
        String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";

        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

        List<NotaFiscal> lista = typedQuery.getResultList();
        Assert.assertNotEquals(1, lista.size());
    }

    @Test
    public void passarParametro() {
        String jpql = "select p from Pedido p join p.pagamento pag " +
                " where p.id = :pedidoId and pag.status = ?1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("pedidoId", 2);
        typedQuery.setParameter(1, StatusPagamento.PROCESSANDO);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertEquals(1, lista.size());
    }

    @Test
    public void usarJoinFetch() {
        String jpql = "select p from Pedido p "
                + " left join fetch p.pagamento "
                + " join fetch p.cliente "
                + " left join fetch p.notaFiscal ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void projetarNoDTO() {
        String jpql = "select new com.curso.dto.ProdutoDto(id, nome) from Produto";

        TypedQuery<ProdutoDto> typedQuery = entityManager.createQuery(jpql, ProdutoDto.class);
        List<ProdutoDto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println(p.getId() + ", " + p.getNome()));
    }

    @Test
    public void buscarPorIdentificador() {
        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido);
    }
}
