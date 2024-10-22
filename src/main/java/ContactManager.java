import Classes.Contact;
import ColecoesIteraveis.estruturas.ListaDuplaOrdenada;
import ColecoesIteraveis.estruturas.ListaDuplaOrdenadaOrdemDistinta;
import Comparacoes.ComparacaoContactosPorTelefoneSegPorEmail;

import java.util.HashMap;
import java.util.List;

public class ContactManager {
    private ListaDuplaOrdenadaOrdemDistinta<Contact> contacts;
    private HashMap<String, ListaDuplaOrdenadaOrdemDistinta<Contact>> labels;

    public ContactManager(){
        contacts = new ListaDuplaOrdenadaOrdemDistinta<>(new ComparacaoContactosPorTelefoneSegPorEmail());
        labels = new HashMap<>(200);
    }

    public List<String> getLabels(){
        List<String> labelList = (List<String>) labels.keySet();
        return labelList;
    }
    public ListaDuplaOrdenadaOrdemDistinta<Contact> getContacts(String... labels){
        ListaDuplaOrdenadaOrdemDistinta listToReturn = new ListaDuplaOrdenadaOrdemDistinta<>(new ComparacaoContactosPorTelefoneSegPorEmail());
        if(labels.length == 0){
            return contacts;
        }
        if(!this.labels.isEmpty()){
            for(String key: labels){
                listToReturn.inserirTodos(this.labels.get(key).iterador());
            }
        }
        return listToReturn;
    }

    public ListaDuplaOrdenada<Contact> search(String term, String... labels){
        ListaDuplaOrdenada<Contact> listacontactos = new ListaDuplaOrdenada<>(new ComparacaoContactosPorTelefoneSegPorEmail());
        //Contact[] listacontactos = new Contact[contacts.getNumeroElementos()];
        if(labels.length == 0){
            for (Contact contact: contacts.iterador()) {
                if(contact.matches(term)){
                    listacontactos.inserir(contact);
                }
            }
            return listacontactos;
        }else{
            for (Contact contact: getContacts(labels)) {
                if(contact.matches(term)){
                    listacontactos.inserir(contact);
                }
            }
            return listacontactos;
        }
    }

    public void addContact(Contact contact, String... labels){
        this.contacts.inserir(contact);
        for (String key: labels) {
            if(this.labels.containsKey(key)){
                this.labels.get(key).inserir(contact);
            }else{
                ListaDuplaOrdenadaOrdemDistinta<Contact> value = new ListaDuplaOrdenadaOrdemDistinta<>(new ComparacaoContactosPorTelefoneSegPorEmail());
                value.inserir(contact);
                this.labels.put(key, value);
            }
        }
    }
    public void removeContact(Contact contact){
        this.contacts.remover(contact);
        labels.values().forEach(contacts -> contacts.remover(contact));
        /*for (String key: this.labels.keySet()) {
            this.labels.get(key).remover(contact);
        }*/
    }

    public int size(){
        return contacts.getNumeroElementos();
    }

    public boolean isEmpty() {
        return this.contacts.isVazia();
    }
}
