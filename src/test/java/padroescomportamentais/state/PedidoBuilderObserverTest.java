package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoBuilderObserverTest {

    // ── Builder ───────────────────────────────────────────────

    @Test
    void deveRetornarExcecaoParaPedidoSemCodigo() {
        try {
            new PedidoBuilder()
                    .setClienteNome("João")
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Código inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaPedidoSemClienteNome() {
        try {
            new PedidoBuilder()
                    .setCodigo("PED-001")
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do cliente inválido", e.getMessage());
        }
    }

    @Test
    void deveCriarPedidoValido() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();

        assertNotNull(pedido);
        assertEquals("PED-001", pedido.getCodigo());
        assertEquals("João", pedido.getClienteNome());
        assertEquals("Pendente", pedido.getNomeEstado());
    }

    // ── Observer ──────────────────────────────────────────────

    @Test
    void deveNotificarClienteAoConfirmarPedido() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();

        ClienteNotificado cliente = new ClienteNotificado("João");
        pedido.adicionarObservador(cliente);

        pedido.confirmar();

        assertEquals("João, seu pedido PED-001 está agora: Confirmado",
                cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarMultiplosClientesAoMudarEstado() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-002")
                .setClienteNome("Maria")
                .build();

        ClienteNotificado cliente1 = new ClienteNotificado("Maria");
        ClienteNotificado cliente2 = new ClienteNotificado("Suporte");
        pedido.adicionarObservador(cliente1);
        pedido.adicionarObservador(cliente2);

        pedido.confirmar();

        assertEquals("Maria, seu pedido PED-002 está agora: Confirmado",
                cliente1.getUltimaNotificacao());
        assertEquals("Suporte, seu pedido PED-002 está agora: Confirmado",
                cliente2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteRemovidoDoObserver() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-003")
                .setClienteNome("Carlos")
                .build();

        ClienteNotificado cliente = new ClienteNotificado("Carlos");
        pedido.adicionarObservador(cliente);
        pedido.removerObservador(cliente);

        pedido.confirmar();

        assertNull(cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarSeNaoHouverObservador() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-004")
                .setClienteNome("Ana")
                .build();

        // Nenhum observador registrado — não deve lançar exceção
        assertDoesNotThrow(() -> pedido.confirmar());
    }

    // ── State + Observer integrados ───────────────────────────

    @Test
    void deveNotificarCadaTransicaoDeEstado() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-005")
                .setClienteNome("Pedro")
                .build();

        ClienteNotificado cliente = new ClienteNotificado("Pedro");
        pedido.adicionarObservador(cliente);

        // Pendente → Confirmado
        pedido.confirmar();
        assertEquals("Pedro, seu pedido PED-005 está agora: Confirmado",
                cliente.getUltimaNotificacao());

        // Confirmado → Cancelado
        pedido.cancelar();
        assertEquals("Pedro, seu pedido PED-005 está agora: Cancelado",
                cliente.getUltimaNotificacao());

        // Cancelado → Reembolsado
        pedido.reembolsar();
        assertEquals("Pedro, seu pedido PED-005 está agora: Reembolsado",
                cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveTransicionarEstadoInvalido() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-006")
                .setClienteNome("Lucia")
                .build();

        ClienteNotificado cliente = new ClienteNotificado("Lucia");
        pedido.adicionarObservador(cliente);

        // Reembolsado é estado final — não aceita mais transições
        pedido.confirmar();   // Pendente → Confirmado
        pedido.cancelar();    // Confirmado → Cancelado
        pedido.reembolsar();  // Cancelado → Reembolsado

        // Tentativa inválida: reembolsado não pode confirmar
        assertFalse(pedido.confirmar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
    }

}
