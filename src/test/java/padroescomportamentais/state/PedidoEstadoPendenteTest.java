package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEstadoPendenteTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();
    }

    @Test
    void deveIniciarComEstadoPendente() {
        assertEquals("Pendente", pedido.getNomeEstado());
    }

    @Test
    void deveTransicionarParaConfirmadoAoConfirmar() {
        assertTrue(pedido.confirmar());
        assertEquals("Confirmado", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoConfirmado);
    }

    @Test
    void deveTransicionarParaEnviadoAoEnviar() {
        assertTrue(pedido.enviar());
        assertEquals("Enviado", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoEnviado);
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
    void deveTransicionarParaReembolsadoAoReembolsar() {
        assertTrue(pedido.reembolsar());
        assertEquals("Reembolsado", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoReembolsado);
    }

    @Test
    void naoDeveTransicionarAoEntregar() {
        assertFalse(pedido.entregar());
        assertEquals("Pendente", pedido.getNomeEstado());
    }
}
