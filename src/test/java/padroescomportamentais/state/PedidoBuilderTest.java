package padroescomportamentais.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoBuilderTest {

    @Test
    void deveCriarPedidoComDadosValidos() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();

        assertNotNull(pedido);
        assertEquals("PED-001", pedido.getCodigo());
        assertEquals("João", pedido.getClienteNome());
    }

    @Test
    void deveCriarPedidoComEstadoInicialPendente() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();

        assertEquals("Pendente", pedido.getNomeEstado());
        assertTrue(pedido.getEstado() instanceof PedidoEstadoPendente);
    }

    @Test
    void deveLancarExcecaoQuandoCodigoNulo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new PedidoBuilder()
                        .setClienteNome("João")
                        .build()
        );
        assertEquals("Código inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCodigoVazio() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new PedidoBuilder()
                        .setCodigo("   ")
                        .setClienteNome("João")
                        .build()
        );
        assertEquals("Código inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNomeNulo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new PedidoBuilder()
                        .setCodigo("PED-001")
                        .build()
        );
        assertEquals("Nome do cliente inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNomeVazio() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new PedidoBuilder()
                        .setCodigo("PED-001")
                        .setClienteNome("   ")
                        .build()
        );
        assertEquals("Nome do cliente inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNenhumCampoInformado() {
        assertThrows(IllegalArgumentException.class, () ->
                new PedidoBuilder().build()
        );
    }

    @Test
    void deveRetornarOProprioPedidoBuilderNosSetters() {
        PedidoBuilder builder = new PedidoBuilder();
        assertSame(builder, builder.setCodigo("PED-001"));
        assertSame(builder, builder.setClienteNome("João"));
    }
}
