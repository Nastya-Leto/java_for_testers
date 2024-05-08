package tests;

import model.Contact;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

public class GroupContactTests extends TestBase {

    @Test
    public void addContactInGroup() {

        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Москва", "mail@google.com"));
        }
        List<Contact> oldGroups = app.contacts().getListGroup();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.contacts().addingContactToGroup(oldGroups.get(index));
    }
}
