public class ListaSimplesEncadeada<T> {

    private Node<T> head;

    public ListaSimplesEncadeada() {
        this.head = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public void add(T dado) {
        Node<T> novoNode = new Node<T>(dado);
        if (head == null) {
            head = novoNode;
        } else {
            Node<T> current = head;
            while (current.getProximo() != null) {
                current = current.getProximo();
            }
            current.setProximo(novoNode);
        }
    }

}
