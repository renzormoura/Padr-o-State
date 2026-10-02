package padroescomportamentais.state;

public abstract class PedidoEstado {

    public abstract String getEstado();

    public boolean confirmar(Pedido pedido) {
        return false;
    }

    public boolean enviar(Pedido pedido) {
        return false;
    }

    public boolean entregar(Pedido pedido) {
        return false;
    }

    public boolean cancelar(Pedido pedido) {
        return false;
    }

    public boolean devolver(Pedido pedido) {
        return false;
    }

    public boolean reembolsar(Pedido pedido) {
        return false;
    }

}
