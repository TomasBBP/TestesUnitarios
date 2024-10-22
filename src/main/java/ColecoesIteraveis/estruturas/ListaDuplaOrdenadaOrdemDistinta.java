package ColecoesIteraveis.estruturas;

import ColecoesIteraveis.ColecaoIteravel;
import ColecoesIteraveis.ColecaoIteravelLinearOrdenada;
import Comparacoes.Comparacao;

import java.awt.*;

public class ListaDuplaOrdenadaOrdemDistinta<T> extends ListaDuplaOrdenada<T> {
    public ListaDuplaOrdenadaOrdemDistinta(Comparacao<T> cp) {
        super(cp);
    }

    public ListaDuplaOrdenadaOrdemDistinta(Comparacao<T> cp, ColecaoIteravelLinearOrdenada<T> colecao) {
        super(cp, colecao);
    }

    public ListaDuplaOrdenadaOrdemDistinta(Comparacao<T> cp, ColecaoIteravel<T> colecao) {
        super(cp, colecao);
    }




    @Override
    public void inserir(T elem){
        No no = getNoPorOrdem(elem);
        if(no.compararElemento(elem)==0){
            throw new IllegalArgumentException("Elemento duplicado!");
        }
        new NoComElemento(elem,no);
        numeroElementos++;
    }

    public T consultarDistinto(T elem){
        No no = getNoPorOrdem(elem);
        return no.compararElemento(elem) == 0 ? no.elemento : null;
    }

    public void clear(){
    }
}
