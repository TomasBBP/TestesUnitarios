package Comparacoes;

import Classes.Contact;

import java.util.Objects;

public class ComparacaoContactosPorTelefoneSegPorEmail implements Comparacao<Contact>{
    @Override
    public int comparar(Contact o1, Contact o2) {
        int result = o1.getPhone().compareTo(o2.getPhone());
        /*if(o1.getEmail()!=null)
            return result == 0 ? o1.getEmail().compareTo(o2.getEmail()) : result;
        if(o2.getEmail()!=null)
            return result == 0 ? o2.getEmail().compareTo(o1.getEmail()) : result;
        return result;*/

        //Using Objects class so null values don't throw NullPointerException
        return result == 0 ? Objects.compare(o1.getEmail(),o2.getEmail(),String::compareTo) : result;
    }
}
