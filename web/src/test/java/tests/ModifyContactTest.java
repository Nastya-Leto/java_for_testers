package tests;

import model.Contact;
import org.junit.jupiter.api.Test;

public class ModifyContactTest extends TestBase {

    @Test
    public void modifyContactTest() {

        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Москва", "mail@google.com"));

        }
        app.contacts().modifyContacts(new Contact("", "Дейенерис", "Таргариен", "Самара", "mail@google.com"));
    }
}
