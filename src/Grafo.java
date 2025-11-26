public class Grafo<T> {

    private ListaSimplesEncadeada<Vertice<T>> vertices;
    private ListaSimplesEncadeada<Aresta<T>> arestas;
    private int[][] matrizAdj;

    public Grafo(int maxVertices) {
        vertices = new ListaSimplesEncadeada<>();
        arestas = new ListaSimplesEncadeada<>();
        this.matrizAdj = new int[maxVertices][maxVertices];
    }

    public ListaSimplesEncadeada<Vertice<T>> getVertices() {
        return vertices;
    }

    public void adicionarVertice(T dado) {
        this.vertices.add(new Vertice<>(dado));
    }

    public Vertice<T> getVertice(T dado) {
        Node<Vertice<T>> head = vertices.getHead();
        while (head != null) {
            if (head.getDado().getDado().equals(dado)) {
                return head.getDado();
            }
            head = head.getProximo();
        }
        return null;
    }

    private int indexOf(T dado) {
        int index = 0;
        Node<Vertice<T>> atual = vertices.getHead();
        while (atual != null) {
            if (atual.getDado().getDado().equals(dado)) return index;
            atual = atual.getProximo();
            index++;
        }
        return -1;
    }

    public int size() {
        int count = 0;
        Node<Vertice<T>> atual = vertices.getHead();
        while (atual != null) {
            count++;
            atual = atual.getProximo();
        }
        return count;
    }

    public void adicionarConexao(T origem, T destino, double distancia) {
        int i = indexOf(origem);
        int j = indexOf(destino);

        if (i == -1 || j == -1)
            throw new RuntimeException("Creche inexistente");

        matrizAdj[i][j] = 1;
        matrizAdj[j][i] = 1;

        Vertice<T> v1 = getVertice(origem);
        Vertice<T> v2 = getVertice(destino);

        arestas.add(new Aresta<>(distancia, v1, v2));
    }

    public int numeroConexoes(T dado) {
        int i = indexOf(dado);
        if (i == -1) throw new RuntimeException("Creche não encontrada");
        int count = 0;

        for (int j = 0; j < size(); j++) {
            if (matrizAdj[i][j] == 1) count++;
        }
        return count;
    }

    public ListaSimplesEncadeada<Vertice<T>> vizinhosOrdenados(T origem) {
        int indexOrigem = indexOf(origem);
        if (indexOrigem == -1) throw new RuntimeException("Creche inexistente");
        ListaSimplesEncadeada<Vertice<T>> conexoes = new ListaSimplesEncadeada<>();
        Node<Vertice<T>> atual = vertices.getHead();
        int posicaoAtual = 0;
        while (atual != null) {
            if (matrizAdj[indexOrigem][posicaoAtual] == 1) {
                conexoes.add(atual.getDado());
            }
            atual = atual.getProximo();
            posicaoAtual++;
        }
        ordenarPorDistancia(origem, conexoes);
        return conexoes;
    }

    private void ordenarPorDistancia(T origem, ListaSimplesEncadeada<Vertice<T>> lista) {
        boolean trocou;
        do {
            trocou = false;
            Node<Vertice<T>> head = lista.getHead();
            while (head != null && head.getProximo() != null) {
                double distancia1 = distanciaEntreVertices(origem, head.getDado().getDado());
                double distancia2 = distanciaEntreVertices(origem, head.getProximo().getDado().getDado());
                boolean deveTrocar = distancia1 > distancia2;
                if (deveTrocar) {
                    Vertice<T> temp = head.getDado();
                    head.setDado(head.getProximo().getDado());
                    head.getProximo().setDado(temp);
                    trocou = true;
                }
                head = head.getProximo();
            }
        } while (trocou);
    }

    public double distanciaEntreVertices(T origem, T destino) {
        Node<Aresta<T>> atual = arestas.getHead();
        while (atual != null) {
            Aresta<T> ar = atual.getDado();

            boolean origemEDestinoEstaoNaMesmaAresta =
                    (ar.getInicio().getDado().equals(origem) && ar.getFim().getDado().equals(destino))
                            || (ar.getInicio().getDado().equals(destino) && ar.getFim().getDado().equals(origem));

            if (origemEDestinoEstaoNaMesmaAresta) return ar.getPeso();

            atual = atual.getProximo();
        }
        return -1;
    }

}
