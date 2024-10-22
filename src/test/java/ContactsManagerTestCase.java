import Classes.Contact;
import ColecoesIteraveis.estruturas.ListaDuplaOrdenada;
import Comparacoes.ComparacaoContactosPorTelefoneSegPorEmail;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * --------------------------------------------------------------------------
 *
 * JUnit 5 Framework recommends:
 *
 * --------------------------------------------------------------------------
 *
 * Methods annotated with @BeforeAll and @AfterAll must be static.
 *
 * --------------------------------------------------------------------------
 */
public class ContactsManagerTestCase {

    ContactManager cm;


    @BeforeEach
    public void setUp(){
        cm = new ContactManager();
        MockitoAnnotations.initMocks(this);
}

    @Test
    public void testAddContact(){
        var contact = new Contact("firstName","949949494");

        assertDoesNotThrow(()->{
            cm.addContact(contact);
            assertEquals(1,cm.size());
            assertSame(contact,cm.getContacts().consultarPorIndice(0));
        });
    }
    @Test
    public void testAddContactWithSamePhoneNumber(){
        var contact1 = new Contact("firstContact","919919919");
        var contact2 = new Contact("secondContact","919919919");
        assertThrows(IllegalArgumentException.class, () -> {
            cm.addContact(contact1);
            cm.addContact(contact2);
        },"no Illegal Argument thrown when duplicating contacts.");
        assertEquals(1,cm.size(),"Duplicated Contacts!");
    }

    @Test
    public void testAddContactWithNullEmails(){
        var contactWithNullEmail = new Contact("nullEmail","919919919");
        var contactWithEmail = new Contact("hasEmail","lastName","121212121","email@email.com");

        assertDoesNotThrow(() -> {
            cm.addContact(contactWithEmail);
            cm.addContact(contactWithNullEmail);

            cm = new ContactManager();

            cm.addContact(contactWithNullEmail);
            cm.addContact(contactWithEmail);
        });
    }
    @Test
    public void testAddContactWithSameEmails(){
        var contact1 = new Contact("firstname","lastname","919442526","sameemail");
        var contact2 = new Contact("firstname","lastname","919442526","sameemail");
        var contact3 = new Contact("first","last","919442526","sameemail");

        assertThrows(IllegalArgumentException.class,()->{
           cm.addContact(contact1);
           cm.addContact(contact3);
        });
        assertThrows(IllegalArgumentException.class,()->{
            cm.addContact(contact1);
            cm.addContact(contact2);
        });
    }

    @Test
    public void testContactsOrganizingAscending(){
        var contact1 = new Contact("first","contact","111111111","abcde@email.com");
        var contact2 = new Contact("second","contact","222222222","abcde@email.com");
        var contact3 = new Contact("third","contact","222222222","bcdef@email.com");
        assertDoesNotThrow(()->{
            cm.addContact(contact2);
            cm.addContact(contact3);
            cm.addContact(contact1);

            assertSame(contact1,cm.getContacts().consultarPorIndice(0));
            assertSame(contact2,cm.getContacts().consultarPorIndice(1));
            assertSame(contact3,cm.getContacts().consultarPorIndice(2));
        });

    }
    @Test
    public void testRemoverContacto(){
        var contact = new Contact("first","919919919");
        assertDoesNotThrow(()->{
            cm.addContact(contact);
            assertEquals(1,cm.size());
            cm.removeContact(contact);
            assertEquals(0,cm.size());
            assertTrue(cm.isEmpty());
        });

    }
    @Test
    public void testRemoverContactoNaoExistente(){
        var contacto1 = new Contact("first","919919919");
        var contacto2 = new Contact("second","919919919");
        assertDoesNotThrow(()->{
            cm.addContact(contacto1);
            cm.removeContact(contacto2);
        });
        assertFalse(cm.isEmpty());
    }
    @Test
    public void testSearchContactoExistente(){
        var contacto1 = new Contact("first","contact","919442526","email@email.com");
        var contacto2 = new Contact("second","contact","111111111","email@email.com");
        var contacto3 = new Contact("third","contact","919442526","different@email.com");

        assertDoesNotThrow(()->{
            var mockedContactManager = mock(ContactManager.class);

            //inserir contactos no cm e mockedContactManager
            {cm.addContact(contacto1,"sameLastName","label");
            cm.addContact(contacto2,"unique","sameLastName");
            cm.addContact(contacto3);
            mockedContactManager.addContact(contacto1,"sameLastName","label");
            mockedContactManager.addContact(contacto2,"unique","sameLastName");
            mockedContactManager.addContact(contacto3);
            }

            //Mockito Verify
            verify(mockedContactManager,times(2)).addContact(any(Contact.class),anyString(),anyString());
            verify(mockedContactManager,times(1)).addContact(any(Contact.class));
            verify(mockedContactManager).addContact(contacto1,"sameLastName","label");
            //Search contacto unico
            ListaDuplaOrdenada<Contact> second = cm.search("second","unique");
            if(!second.contem(contacto2))
                fail("Search não encontrou o contacto \"second\"");
            assertEquals(1,second.getNumeroElementos());
            ListaDuplaOrdenada<Contact> expectedList = new ListaDuplaOrdenada<>(new ComparacaoContactosPorTelefoneSegPorEmail());
            expectedList.inserir(contacto2);
            when(mockedContactManager.search("second","unique")).thenReturn(expectedList);
            assertTrue(mockedContactManager.search("second","unique").contem(second.consultarPorIndice(0)));

            //Search contactos com o mesmo ultimo nome
            ListaDuplaOrdenada<Contact> contactosUltimoNome = new ListaDuplaOrdenada<>(new ComparacaoContactosPorTelefoneSegPorEmail());

            //inserir contactos no contactosUltimoNome
            {contactosUltimoNome.inserir(contacto1);
            contactosUltimoNome.inserir(contacto2);
            contactosUltimoNome.inserir(contacto3);}

            ListaDuplaOrdenada<Contact> contactosPesquisados = cm.search("contact");
            assertTrue(contactosPesquisados.contem(contactosUltimoNome));
            assertEquals(contactosPesquisados.getNumeroElementos(),contactosUltimoNome.getNumeroElementos());

            //Search contactos com o mesmo email na label email
            contactosPesquisados = cm.search("email@email.com","label");
            assertEquals(1,contactosPesquisados.getNumeroElementos());
            assertTrue(contactosPesquisados.contem(contacto1));

            //Search contactos com o mesmo email
            contactosUltimoNome.remover(contacto3);
            contactosPesquisados = cm.search("email@email.com");
            assertEquals(2,contactosPesquisados.getNumeroElementos());
            assertTrue(contactosPesquisados.contem(contactosUltimoNome));
        });
    }

    @Test
    public void searchContactosNaoExistentes(){
        var contacto1 = new Contact("first","last","443445446","email@email.com");

        assertDoesNotThrow(()->{
            cm.addContact(contacto1);

            //Search contacto nao existente
            ListaDuplaOrdenada<Contact> listaPesquisa = cm.search("doesnt");
            assertEquals(0,listaPesquisa.getNumeroElementos());

            //Search contacto sem labels existentes
            listaPesquisa = cm.search("first","nonexistent label");
            assertEquals(0,listaPesquisa.getNumeroElementos());
        });
    }

}

