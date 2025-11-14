public class Aresta<T> {
    private Double peso;
    private Vertice<T> inicio;
    private Vertice<T> fim;

    public Aresta(Double peso, Vertice<T> inicio, Vertice<T> fim) {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Vertice<T> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice<T> inicio) {
        this.inicio = inicio;
    }

    public Vertice<T> getFim() {
        return fim;
    }

    public void setFim(Vertice<T> fim) {
        this.fim = fim;
    }

    public Vertice<T> getVerticeOposto(Vertice<T> vertice) {
        if (this.inicio.equals(vertice)) {
            return this.fim;
        } else if (this.fim.equals(vertice)) {
            return this.inicio;
        }
        return null;
    }
}
