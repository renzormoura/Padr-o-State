package padroescomportamentais.state;

public class Pedido {

    private String codigo;
    private PedidoEstado estado;

    public Pedido() {
        this.estado = PedidoEstadoPendente.getInstance();
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
    }

    public boolean confirmar() {
        return estado.confirmar(this);
    }

    public boolean enviar() {
        return estado.enviar(this);
    }

    public boolean entregar() {
        return estado.entregar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    public boolean devolver() {
        return estado.devolver(this);
    }

    public boolean reembolsar() {
        return estado.reembolsar(this);
    }

    public String getNomeEstado() {
        return estado.getEstado();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public PedidoEstado getEstado() {
        return estado;
    }
}
