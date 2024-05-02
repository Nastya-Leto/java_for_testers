package tests;

import model.Contact;
import org.junit.jupiter.api.Test;


public class CreateNewContactTest extends TestBase {

    @Test
    public void createNewContactTest() {

        app.contacts().createContact(new Contact("Дейенерис", "Таргариен", "Самара", "mail@google.com"));

    }
}
