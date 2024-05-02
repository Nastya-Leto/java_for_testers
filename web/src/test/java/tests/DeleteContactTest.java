package tests;

import model.Contact;
import org.junit.jupiter.api.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void createNewContactTest() {

        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new Contact("Дейенерис", "Таргариен", "Москва", "mail@google.com"));

        }
        app.contacts().removeContact();
    }
}
