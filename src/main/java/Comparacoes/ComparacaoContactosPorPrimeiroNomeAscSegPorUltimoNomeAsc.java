package Comparacoes;
import Classes.Contact;

public class ComparacaoContactosPorPrimeiroNomeAscSegPorUltimoNomeAsc implements Comparacao<Contact>{
    @Override
    public int comparar(Contact o1, Contact o2) {
        int result = o1.getFirstName().compareTo(o2.getFirstName());
        return result == 0 ? o1.getLastName().compareTo(o2.getLastName()) : result;
    }
}
