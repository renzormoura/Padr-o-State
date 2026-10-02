package padroescomportamentais.state;

public class ClienteNotificado implements PedidoObservador {

    private String nome;
    private String ultimaNotificacao;

    public ClienteNotificado(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(Pedido pedido) {
        this.ultimaNotificacao = nome + ", seu pedido " + pedido.getCodigo()
                + " está agora: " + pedido.getNomeEstado();
    }

    public String getNome() {
        return nome;
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }

}
