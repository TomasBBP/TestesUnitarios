package ColecoesIteraveis;

public interface IteradorIteravelDuplo<T> extends IteradorIteravel<T>{
    /**
     * Verifica se pode recuar para o elemento anterior.
     *
     * @return true caso ainda existam elementos a percorrer;
     * false caso contrário
     */
    boolean podeRecuar();

    /**
     * Devolve o elemento anterior.
     * Caso não exista lança a excepção ELEMENTO_INVALIDO
     *
     * @return o elemento anterior
     */
    T recuar();
}

