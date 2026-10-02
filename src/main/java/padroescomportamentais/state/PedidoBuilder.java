package padroescomportamentais.state;

public class PedidoBuilder {

    private String codigo;
    private String clienteNome;

    public PedidoBuilder setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public PedidoBuilder setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
        return this;
    }

    public Pedido build() {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código inválido");
        }
        if (clienteNome == null || clienteNome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente inválido");
        }
        return new Pedido(codigo, clienteNome);
    }

}
