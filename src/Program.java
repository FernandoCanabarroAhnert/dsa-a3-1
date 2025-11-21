import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Path projectRoot = Paths.get("").toAbsolutePath();
        Path path = projectRoot.resolve(Paths.get("src","grafo.txt"));
        System.out.println(path);
        Grafo<String> grafo = lerArquivo(path.toString());

        int opcao = mostrarMenu();

        while (opcao != -1) {
            switch (opcao) {
                case 1 -> adicionarConexaoEntreCreches(grafo);
                case 2 -> informarDistanciaEntreDuasCreches(grafo);
                case 3 -> informarNumeroDeConexoesPossiveisDeUmaCreche(grafo);
                case 4 -> listarCrechesConectadasEmOrdemCrescenteDeDistancia(grafo);
                default -> System.out.println("Opção inválida! Tente novamente");
            }
            opcao = mostrarMenu();
        }
    }

    public static Grafo<String> lerArquivo(String path) {
        Grafo<String> grafo = new Grafo<>(100);
        File file = new File(path);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] linha = scanner.nextLine().split(" ");
                String verticeInicio = linha[0];
                String verticeFim = linha[1];
                double distancia = Double.parseDouble(linha[2]);

                if (grafo.getVertice(verticeInicio) == null) {
                    grafo.adicionarVertice(verticeInicio);
                }
                if (grafo.getVertice(verticeFim) == null) {
                    grafo.adicionarVertice(verticeFim);
                }

                grafo.adicionarConexao(verticeInicio, verticeFim, distancia);
            }
        }
        catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return grafo;
    }

    public static int mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== MENU =====");
        System.out.println("1 - Inclui nova conexão entre creches");
        System.out.println("2 - Informar distância entre duas creches");
        System.out.println("3 - Informar o número de conexões possíveis partindo de uma creche");
        System.out.println("4 - Listar para uma dada creche as creches com as quais ela se conecta listadas em ordem crescente de distância");
        System.out.println("==========");
        System.out.print("Digite uma opção: ");
        return scanner.nextInt();
    }

    public static void adicionarConexaoEntreCreches(Grafo<String> grafo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da creche de origem:");
        String origem = scanner.nextLine();
        System.out.println("Digite o nome da creche de destino:");
        String destino = scanner.nextLine();
        System.out.println("Digite a distância entre as duas creches:");
        double distancia = scanner.nextDouble();
        grafo.adicionarConexao(origem, destino, distancia);
    }

    public static void informarDistanciaEntreDuasCreches(Grafo<String> grafo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da creche de origem:");
        String origem = scanner.nextLine();
        System.out.println("Digite o nome da creche de destino:");
        String destino = scanner.nextLine();
        double distanciaEntreCreches = grafo.distanciaEntreVertices(origem, destino);
        if (distanciaEntreCreches == -1) {
            System.out.println("Não existe conexão direta entre as creches fornecidas");
            return;
        }
        System.out.println("A distância entre a creche " + origem + " e a creche " + destino + " é de: " + distanciaEntreCreches + "km");
    }

    public static void informarNumeroDeConexoesPossiveisDeUmaCreche(Grafo<String> grafo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da creche:");
        String creche = scanner.nextLine();
        int conexoesPossiveis = grafo.numeroConexoes(creche);
        System.out.println("A creche " + creche + " tem " + conexoesPossiveis + " conexões possíveis");
    }

    public static void listarCrechesConectadasEmOrdemCrescenteDeDistancia(Grafo<String> grafo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da creche:");
        String creche = scanner.nextLine();
        ListaSimplesEncadeada<Vertice<String>> vizinhosOrdenados = grafo.vizinhosOrdenados(creche);

        System.out.println("Conexões de " + creche + ":");
        Node<Vertice<String>> atual = vizinhosOrdenados.getHead();
        int ordem = 1;
        while (atual != null) {
            Vertice<String> vizinho = atual.getDado();
            System.out.println(ordem + ". " + vizinho.getDado());
            atual = atual.getProximo();
            ordem++;
        }
    }

}