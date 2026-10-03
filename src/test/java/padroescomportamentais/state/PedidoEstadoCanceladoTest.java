package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEstadoCanceladoTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();
        pedido.cancelar();
    }

    @Test
    void deveEstarNoEstadoCancelado() {
        assertEquals("Cancelado", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoCancelado);
    }

    @Test
    void deveTransicionarParaReembolsadoAoReembolsar() {
        assertTrue(pedido.reembolsar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoReembolsado);
    }

    @Test
    void naoDeveTransicionarAoConfirmar() {
        assertFalse(pedido.confirmar());
        assertEquals("Cancelado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEnviar() {
        assertFalse(pedido.enviar());
        assertEquals("Cancelado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEntregar() {
        assertFalse(pedido.entregar());
        assertEquals("Cancelado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoCancelar() {
        assertFalse(pedido.cancelar());
        assertEquals("Cancelado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoDevolver() {
        assertFalse(pedido.devolver());
        assertEquals("Cancelado", pedido.getNomeEstado());
    }
}
