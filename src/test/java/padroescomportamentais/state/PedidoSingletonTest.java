package padroescomportamentais.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoSingletonTest {

    @Test
    void pendenteSempreRetornaMesmaInstancia() {
        assertSame(PedidoEstadoPendente.getInstance(), PedidoEstadoPendente.getInstance());
    }

    @Test
    void confirmadoSempreRetornaMesmaInstancia() {
        assertSame(PedidoEstadoConfirmado.getInstance(), PedidoEstadoConfirmado.getInstance());
    }

    @Test
    void enviadoSempreRetornaMesmaInstancia() {
        assertSame(PedidoEstadoEnviado.getInstance(), PedidoEstadoEnviado.getInstance());
    }

    @Test
    void entregueSempreRetornaMesmaInstancia() {
        assertSame(PedidoEstadoEntregue.getInstance(), PedidoEstadoEntregue.getInstance());
    }

    @Test
    void canceladoSempreRetornaMesmaInstancia() {
        assertSame(PedidoEstadoCancelado.getInstance(), PedidoEstadoCancelado.getInstance());
    }

    @Test
    void devolvidoSempreRetornaMesmaInstancia() {
        assertSame(PedidoEstadoDevolvido.getInstance(), PedidoEstadoDevolvido.getInstance());
    }

    @Test
    void reembolsadoSempreRetornaMesmaInstancia() {
        assertSame(PedidoEstadoReembolsado.getInstance(), PedidoEstadoReembolsado.getInstance());
    }

    @Test
    void estadosDiferentesNaoSaoAMesmaInstancia() {
        assertNotSame(PedidoEstadoPendente.getInstance(), PedidoEstadoConfirmado.getInstance());
        assertNotSame(PedidoEstadoPendente.getInstance(), PedidoEstadoEnviado.getInstance());
        assertNotSame(PedidoEstadoCancelado.getInstance(), PedidoEstadoReembolsado.getInstance());
        assertNotSame(PedidoEstadoDevolvido.getInstance(), PedidoEstadoReembolsado.getInstance());
    }

    @Test
    void pedidoUsaInstanciaSingletonDePendente() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();

        assertSame(PedidoEstadoPendente.getInstance(), pedido.getEstado());
    }

    @Test
    void pedidoUsaInstanciaSingletonAposTransicao() {
        Pedido pedido = new PedidoBuilder()
                .setCodigo("PED-001")
                .setClienteNome("João")
                .build();

        pedido.confirmar();
        assertSame(PedidoEstadoConfirmado.getInstance(), pedido.getEstado());

        pedido.cancelar();
        assertSame(PedidoEstadoCancelado.getInstance(), pedido.getEstado());

        pedido.reembolsar();
        assertSame(PedidoEstadoReembolsado.getInstance(), pedido.getEstado());
    }
}
