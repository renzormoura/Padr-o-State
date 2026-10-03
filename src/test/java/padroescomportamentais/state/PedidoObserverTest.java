package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoObserverTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();
    }

    @Test
    void deveRetornarNomeDoCliente() {
        ClienteNotificado cliente = new ClienteNotificado("Maria");
        assertEquals("Maria", cliente.getNome());
    }

    @Test
    void deveRetornarNotificacaoNulaAntesDePrimeiraAtualizacao() {
        ClienteNotificado cliente = new ClienteNotificado("Maria");
        assertNull(cliente.getUltimaNotificacao());
    }

    @Test
    void deveFormatarMensagemDeNotificacaoCorretamente() {
        ClienteNotificado cliente = new ClienteNotificado("João");
        pedido.adicionarObservador(cliente);

        pedido.confirmar();

        assertEquals("João, seu pedido PED-001 está agora: Confirmado",
                cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarObservadorAdicionado() {
        ClienteNotificado cliente = new ClienteNotificado("João");
        pedido.adicionarObservador(cliente);

        pedido.confirmar();

        assertNotNull(cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarObservadorRemovido() {
        ClienteNotificado cliente = new ClienteNotificado("João");
        pedido.adicionarObservador(cliente);
        pedido.removerObservador(cliente);

        pedido.confirmar();

        assertNull(cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveLancarExcecaoSemObservadores() {
        assertDoesNotThrow(() -> pedido.confirmar());
    }

    @Test
    void deveNotificarMultiplosObservadores() {
        ClienteNotificado cliente1 = new ClienteNotificado("João");
        ClienteNotificado cliente2 = new ClienteNotificado("Suporte");
        ClienteNotificado cliente3 = new ClienteNotificado("Logística");

        pedido.adicionarObservador(cliente1);
        pedido.adicionarObservador(cliente2);
        pedido.adicionarObservador(cliente3);

        pedido.confirmar();

        assertEquals("João, seu pedido PED-001 está agora: Confirmado",
                cliente1.getUltimaNotificacao());
        assertEquals("Suporte, seu pedido PED-001 está agora: Confirmado",
                cliente2.getUltimaNotificacao());
        assertEquals("Logística, seu pedido PED-001 está agora: Confirmado",
                cliente3.getUltimaNotificacao());
    }

    @Test
    void deveNotificarSomenteObservadoresRestantesAposRemocao() {
        ClienteNotificado cliente1 = new ClienteNotificado("João");
        ClienteNotificado cliente2 = new ClienteNotificado("Suporte");

        pedido.adicionarObservador(cliente1);
        pedido.adicionarObservador(cliente2);
        pedido.removerObservador(cliente1);

        pedido.confirmar();

        assertNull(cliente1.getUltimaNotificacao());
        assertNotNull(cliente2.getUltimaNotificacao());
    }

    @Test
    void deveAtualizarNotificacaoACadaTransicao() {
        ClienteNotificado cliente = new ClienteNotificado("João");
        pedido.adicionarObservador(cliente);

        pedido.confirmar();
        assertEquals("João, seu pedido PED-001 está agora: Confirmado",
                cliente.getUltimaNotificacao());

        pedido.cancelar();
        assertEquals("João, seu pedido PED-001 está agora: Cancelado",
                cliente.getUltimaNotificacao());

        pedido.reembolsar();
        assertEquals("João, seu pedido PED-001 está agora: Reembolsado",
                cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarEmTransicaoInvalida() {
        ClienteNotificado cliente = new ClienteNotificado("João");
        pedido.adicionarObservador(cliente);

        pedido.confirmar();
        pedido.cancelar();
        pedido.reembolsar();

        String notificacaoAntes = cliente.getUltimaNotificacao();

        pedido.confirmar();

        assertEquals(notificacaoAntes, cliente.getUltimaNotificacao());
    }
}
