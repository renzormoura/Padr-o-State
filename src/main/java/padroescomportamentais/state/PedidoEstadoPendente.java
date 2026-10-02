package padroescomportamentais.state;

public class PedidoEstadoPendente extends PedidoEstado {

    private PedidoEstadoPendente() {};
    private static PedidoEstadoPendente instance = new PedidoEstadoPendente();
    public static PedidoEstadoPendente getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Pendente";
    }

    public boolean confirmar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoConfirmado.getInstance());
        return true;
    }

    public boolean enviar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEnviado.getInstance());
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

    public boolean reembolsar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoReembolsado.getInstance());
        return true;
    }

}
