package padroescomportamentais.state;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String codigo;
    private String clienteNome;
    private PedidoEstado estado;
    private List<PedidoObservador> observadores = new ArrayList<>();

    // Construtor de uso interno — instanciação via PedidoBuilder
    Pedido(String codigo, String clienteNome) {
        this.codigo = codigo;
        this.clienteNome = clienteNome;
        this.estado = PedidoEstadoPendente.getInstance();
    }

    // ── Observer ──────────────────────────────────────────────

    public void adicionarObservador(PedidoObservador observador) {
        observadores.add(observador);
    }

    public void removerObservador(PedidoObservador observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (PedidoObservador observador : observadores) {
            observador.atualizar(this);
        }
    }

    // ── State ─────────────────────────────────────────────────

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
        notificarObservadores();
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

    // ── Getters ───────────────────────────────────────────────

    public String getNomeEstado() {
        return estado.getEstado();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public PedidoEstado getEstado() {
        return estado;
    }

}
