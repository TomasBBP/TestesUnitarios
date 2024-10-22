package ColecoesIteraveis;

import java.util.Iterator;


public interface ColecaoIteravel<T> extends Colecao<T>, Iterable<T> {
    IteradorIteravel<T> iterador();

    @Override
    default Iterator<T> iterator() {
        return iterador();
    }

    int getNumeroElementos();

    default boolean isVazia() {
        return getNumeroElementos() == 0;
    }
}

