package padroescomportamentais.state;

public class PedidoEstadoConfirmado extends PedidoEstado {

    private PedidoEstadoConfirmado() {};
    private static PedidoEstadoConfirmado instance = new PedidoEstadoConfirmado();
    public static PedidoEstadoConfirmado getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Confirmado";
    }

    public boolean confirmar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoPendente.getInstance());
        return true;
    }

    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }

    public boolean devolver(Pedido pedido) {
        pedido.setEstado(PedidoEstadoDevolvido.getInstance());
        return true;
    }

}
