package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEstadoDevolvidoTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();
        pedido.devolver();
    }

    @Test
    void deveEstarNoEstadoDevolvido() {
        assertEquals("Devolvido", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoDevolvido);
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
        assertEquals("Devolvido", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEnviar() {
        assertFalse(pedido.enviar());
        assertEquals("Devolvido", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEntregar() {
        assertFalse(pedido.entregar());
        assertEquals("Devolvido", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoCancelar() {
        assertFalse(pedido.cancelar());
        assertEquals("Devolvido", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoDevolver() {
        assertFalse(pedido.devolver());
        assertEquals("Devolvido", pedido.getNomeEstado());
    }
}
