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

    public boolean contains(T dado) {
        Node<T> head = this.head;
        while (head != null) {
            if (head.getDado().equals(dado)) {
                return true;
            }
            head = head.getProximo();
        }
        return false;
    }

    public int length() {
        int count = 0;
        if (head == null) return count;
        Node<T> atual = this.head;
        while (atual != null) {
            count++;
            atual = atual.getProximo();
        }
        return count;
    }

    public void removeHead() {
        if (this.head != null) {
            this.head = this.head.getProximo();
        }
    }

    public void printList() {
        Node<T> atual = head;
        while (atual != null) {
            System.out.print(atual.getDado() + " -> ");
            atual = atual.getProximo();
        }
        System.out.println("null");
    }

}
