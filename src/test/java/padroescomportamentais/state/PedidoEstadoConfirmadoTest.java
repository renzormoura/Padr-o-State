package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEstadoConfirmadoTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();
        pedido.confirmar();
    }

    @Test
    void deveEstarNoEstadoConfirmado() {
        assertEquals("Confirmado", pedido.getNomeEstado());
    }

    @Test
    void deveTransicionarParaPendenteAoConfirmar() {
        assertTrue(pedido.confirmar());
        assertEquals("Pendente", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoPendente);
    }

    @Test
    void deveTransicionarParaCanceladoAoCancelar() {
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoCancelado);
    }

    @Test
    void deveTransicionarParaDevolvidoAoDevolver() {
        assertTrue(pedido.devolver());
        assertEquals("Devolvido", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoDevolvido);
    }

    @Test
    void naoDeveTransicionarAoEnviar() {
        assertFalse(pedido.enviar());
        assertEquals("Confirmado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoEntregar() {
        assertFalse(pedido.entregar());
        assertEquals("Confirmado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveTransicionarAoReembolsar() {
        assertFalse(pedido.reembolsar());
        assertEquals("Confirmado", pedido.getNomeEstado());
    }
}
