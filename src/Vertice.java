import java.util.Objects;

public class Vertice<T> {

    private T dado;

    public Vertice(T dado) {
        this.dado = dado;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice<?> vertice = (Vertice<?>) o;
        return Objects.equals(dado, vertice.dado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dado);
    }
}
