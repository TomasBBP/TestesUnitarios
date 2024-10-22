import Classes.Contact;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTestCase {

    @Test
    public void testCreateContact(){
        var contact = new Contact("foo","bar","919 442 526");

        assertEquals("foo",contact.getFirstName());
        assertEquals("bar",contact.getLastName());
        assertEquals("919 442 526",contact.getPhone());
    }
    @Test
    public void testCreateContactWithBirthday(){
        var contact = new Contact("foo","bar","919 442 526","email@email.com", new Date(1999,13,1));

        assertEquals("foo",contact.getFirstName());
        assertEquals("bar",contact.getLastName());
        assertEquals("919 442 526",contact.getPhone());
        assertEquals(new Date(1999,13,1),contact.getBirthday());
    }
}
