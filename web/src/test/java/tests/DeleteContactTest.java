package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void deleteContactTest() {

        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new Contact("Дейенерис", "Таргариен", "Москва", "mail@google.com"));

        }
        int contactCount = app.contacts().getCountContact();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCountContact();
        Assertions.assertEquals(contactCount - 1, newContactCount);
    }

    @Test
    public void DeleteAllContactsTests() {

        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new Contact("Дейенерис", "Таргариен", "Москва", "mail@google.com"));
        }
        app.contacts().removeAllContacts();
        var contactsCount = app.contacts().getCountContact();
        Assertions.assertEquals(0, contactsCount);
    }
}
