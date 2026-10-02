package padroescomportamentais.state;

public class PedidoEstadoDevolvido extends PedidoEstado {

    private PedidoEstadoDevolvido() {};
    private static PedidoEstadoDevolvido instance = new PedidoEstadoDevolvido();
    public static PedidoEstadoDevolvido getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Devolvido";
    }

    public boolean reembolsar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoReembolsado.getInstance());
        return true;
    }

}
