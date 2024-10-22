package ColecoesIteraveis;

import Comparacoes.Comparacao;

public interface ColecaoIteravelLinearOrdenada<T> extends ColecaoIteravelLinear<T>{
    Comparacao<T> getComparador();

    // Devolve um iterador de todos os elementos que tenham a mesma ordem que elem
    default IteradorIteravel<T> consultar(T elem) {
        return consultar(elem, elem);
    }

    // Devolve um iterador dos elementos de ordem
    // superior ou igual à ordem de elemInicial e
    // menor ou igual à ordem de elemFinal
    IteradorIteravel<T> consultar(T elemInicial, T elemFinal);

    boolean contemOrdem(T elem);
}
