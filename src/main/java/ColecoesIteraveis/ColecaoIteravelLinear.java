package ColecoesIteraveis;

public interface ColecaoIteravelLinear<T> extends ColecaoIteravel<T>{
    void inserir(T elem);

    T remover(T elem);

    T removerPorReferencia(T elem);

    T removerPorIndice(int indice);

    T consultarPorIndice(int indice);

    boolean contem(T elem);

    boolean contemReferencia(T elem);
}
