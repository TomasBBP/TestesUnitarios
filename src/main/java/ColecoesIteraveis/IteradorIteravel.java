package ColecoesIteraveis;

import java.util.Iterator;

public interface IteradorIteravel<T> extends Iterator<T>, Iterable<T> {
    /**
     * Reinicia o iterador.
     */
    void reiniciar();

    /**
     * Devolve o elemento atual.
     * Caso não exista lança uma exceção NoSuchElementException
     *
     * @return o elemento atual
     */
    T corrente();

    /**
     * Verifica se pode avançar para o próximo elemento.
     *
     * @return true caso ainda existam elementos a percorrer;
     * false caso contrário
     */
    boolean podeAvancar();

    /**
     * Devolve o próximo elemento.
     * Caso não exista lança uma exceção NoSuchElementException
     *
     * @return o próximo elemento
     */
    T avancar();


    @Override
    default boolean hasNext() {
        return podeAvancar();
    }

    @Override
    default T next() {
        return avancar();
    }

    @Override
    default Iterator<T> iterator() {
        return this;
    }
}

