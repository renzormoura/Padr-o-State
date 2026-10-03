package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEstadoReembolsadoTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();
        pedido.cancelar();
        pedido.reembolsar();
    }

    @Test
    void deveEstarNoEstadoReembolsado() {
        assertEquals("Reembolsado", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoReembolsado);
    }

    @Test
    void naoDeveTransicionarAoConfirmar() {
        assertFalse(pedido.confirmar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEnviar() {
        assertFalse(pedido.enviar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEntregar() {
        assertFalse(pedido.entregar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoCancelar() {
        assertFalse(pedido.cancelar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoDevolver() {
        assertFalse(pedido.devolver());
        assertEquals("Reembolsado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoReembolsar() {
        assertFalse(pedido.reembolsar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
    }
}
