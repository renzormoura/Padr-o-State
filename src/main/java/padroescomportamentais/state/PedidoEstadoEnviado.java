package padroescomportamentais.state;

public class PedidoEstadoEnviado extends PedidoEstado {

    private PedidoEstadoEnviado() {};
    private static PedidoEstadoEnviado instance = new PedidoEstadoEnviado();
    public static PedidoEstadoEnviado getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Enviado";
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
