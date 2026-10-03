package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEstadoEntregueTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
    }

    @Test
    void deveEstarNoEstadoEntregue() {
        assertEquals("Entregue", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoEntregue);
    }

    @Test
    void naoDeveTransicionarAoConfirmar() {
        assertFalse(pedido.confirmar());
        assertEquals("Entregue", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEnviar() {
        assertFalse(pedido.enviar());
        assertEquals("Entregue", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEntregar() {
        assertFalse(pedido.entregar());
        assertEquals("Entregue", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoCancelar() {
        assertFalse(pedido.cancelar());
        assertEquals("Entregue", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoDevolver() {
        assertFalse(pedido.devolver());
        assertEquals("Entregue", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoReembolsar() {
        assertFalse(pedido.reembolsar());
        assertEquals("Entregue", pedido.getNomeEstado());
    }
}
