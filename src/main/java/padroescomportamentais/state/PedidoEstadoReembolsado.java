package padroescomportamentais.state;

public class PedidoEstadoReembolsado extends PedidoEstado {

    private PedidoEstadoReembolsado() {};
    private static PedidoEstadoReembolsado instance = new PedidoEstadoReembolsado();
    public static PedidoEstadoReembolsado getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Reembolsado";
    }

}
