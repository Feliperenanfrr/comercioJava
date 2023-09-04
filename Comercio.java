import java.util.ArrayList;
import java.util.Scanner;

class Produto {
    String nome;
    int codigo;
    int estoque;

    public Produto(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.estoque = 0;
    }
}

public class Comercio {
    static ArrayList<Produto> produtos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;

        while (!sair) {
            exibirMenu();
            int escolha = Integer.parseInt(sc.nextLine());


            switch (escolha) {
                case 1:
                    listarProdutos();
                    menu2();
                    break;
                case 2:
                    cadastrarProduto();
                    menu2();
                    break;
                case 3:
                    adicionarEstoque();
                    menu2();
                    break;
                case 4:
                    removerProduto();
                    menu2();
                    break;
                case 5:
                    venderProduto();
                    menu2();
                    break;
                case 6:
                    sair = true;
                    System.out.println("\nSaindo do programa. \uD83D\uDC4B");
                    sc.close();
                    break;
                default:
                    System.out.println("\nOpção inválida. \uD83D\uDEAB Escolha novamente.");
                    break;
            }
        }
    }

    static void exibirMenu() {
        System.out.println("\n        \uD83C\uDFAE   JAVA GAMES   \uD83C\uDFAE        \n");
        System.out.println("[ 1 ] ▸ Listar todos os produtos \uD83D\uDCCB");
        System.out.println("[ 2 ] ▸ Cadastrar um novo produto \uD83D\uDD8A\uFE0F");
        System.out.println("[ 3 ] ▸ Adicionar estoque de um produto ➕");
        System.out.println("[ 4 ] ▸ Remover um produto do comércio ❌");
        System.out.println("[ 5 ] ▸ Vender algum produto existente 💲");
        System.out.println("[ 6 ] ▸ Sair do programa \uD83C\uDFC3");
        System.out.print("\n▶️ Escolha uma opção: ");
    }

    static void listarProdutos() {
        System.out.println("\n" + "↻ Verificando..." + "\n");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado no sistema. \uD83D\uDEAB");

        } else {
            System.out.println("Produtos cadastrados no sistema:");
            for (Produto item : produtos) {
                System.out.println("• " + item.nome + " (cód.: " + item.codigo + " | estoque: " + item.estoque + ")");

            }
        }
    }

    static void cadastrarProduto() {
        System.out.print("▶️ Digite o nome do novo produto: ");
        String nome = sc.nextLine();

        System.out.print("▶️ Digite o código do novo produto: ");
        int codigo = Integer.parseInt(sc.nextLine());

        // Verificando se o produto com o mesmo código já existe
        Produto produtoExistente = encontrarProduto(codigo);
        if (produtoExistente != null) {
            System.out.println("Produto com código " + codigo + " já está cadastrado.\n" +
                    "Tente Novamente");
            return;
        }
        Produto novoProduto = new Produto(nome, codigo);
        produtos.add(novoProduto);

        System.out.print("▶️ Digite o estoque do produto: ");
        int quantidade = Integer.parseInt(sc.nextLine());

        if (quantidade < 0) {
            System.out.println("Número negativo inválido.\uD83D\uDEAB");
        }
        novoProduto.estoque += quantidade;
        System.out.println("\n" + nome + " cadastrado com sucesso ✔\uFE0F\n➥ Código: " + codigo + "\n" + "➥ Estoque: " + novoProduto.estoque);
    }


    static void adicionarEstoque() {
        if (produtos.isEmpty()) {
            System.out.println("\nNão há nenhum produto no sistema no qual o estoque possa ser alterado. \uD83D\uDEAB");

        } else {
            listarProdutos();
            System.out.print("\n" + "▶️ Digite o código do produto para adicionar estoque: ");
            int codigo = Integer.parseInt(sc.nextLine());

            Produto Verificacao = encontrarProduto(codigo);
            if (Verificacao == null) {
                System.out.println("\nProduto não encontrado. \uD83D\uDEAB");
                return;
            }

            System.out.print("▶️ Digite a quantidade a ser adicionada ao estoque: ");
            int quantidade = Integer.parseInt(sc.nextLine());

            if (quantidade < 0) {
                System.out.println("Número negativo inválido.\uD83D\uDEAB");
            } else {
                Verificacao.estoque += quantidade;
                System.out.println("\n" + quantidade + " unidades adicionadas ao estoque de " + Verificacao.nome + " ✔\uFE0F \n➥ Novo estoque: " + Verificacao.estoque);
            }
        }

    }

    static void removerProduto() {
        if (produtos.isEmpty()) {
            System.out.println("\n" + "Não há nenhum produto no sistema que possa ser removido. \uD83D\uDEAB");

        } else {
            listarProdutos();
            System.out.print("▶️ Digite o código do produto para remover: ");
            int codigo = Integer.parseInt(sc.nextLine());

            Produto Verificacao = encontrarProduto(codigo);
            if (Verificacao == null) {
                System.out.println("\n" + "Produto não encontrado. \uD83D\uDEAB");
                return;

            }

            System.out.print("▶️ Deseja realmente remover o produto " + Verificacao.nome + "? (S/N): ");
            String confirmacao = sc.nextLine().toLowerCase();

            if (confirmacao.equals("s")) {
                produtos.remove(Verificacao);
                System.out.println(Verificacao.nome + " removido do sistema ✔\uFE0F");

            } else {
                System.out.println("Produto não foi removido");

            }
        }
    }

    static void venderProduto() {
        if (produtos.isEmpty()) {
            System.out.println("\nNão há nenhum produto cadastrado no sistema que possa ser vendido. \uD83D\uDEAB");

        } else {
            listarProdutos();
            System.out.print("▶️ Digite o código do produto para vender: ");
            int codigo = Integer.parseInt(sc.nextLine());

            Produto Verificacao = encontrarProduto(codigo);
            if (Verificacao == null) {
                System.out.println("Produto não encontrado. \uD83D\uDEAB");
                return;
            }

            System.out.print("▶️ Digite a quantidade a ser vendida: ");
            int quantidade = Integer.parseInt(sc.nextLine());


            if (quantidade < 0) {
                System.out.println("Número negativo inválido.\uD83D\uDEAB");


            } else if (quantidade <= Verificacao.estoque) {
                Verificacao.estoque -= quantidade;
                System.out.println(quantidade + " unidades de " + Verificacao.nome + " vendidas ✔\uFE0F \n➥ Novo estoque: " + Verificacao.estoque);

            } else {
                System.out.println("Estoque insuficiente para essa venda. \uD83D\uDEAB");
            }
        }

    }

    static Produto encontrarProduto(int codigo) {
        for (Produto item : produtos) {
            if (item.codigo == codigo) {
                return item;
            }
        }
        return null;

    }

    static void menu2() {
        System.out.println("\n🔄 Redirecionando para o menu. 🔄");
    }

}