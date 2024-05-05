package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteContactTest extends TestBase {

    @Test
    public void deleteContactTest() {

        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Москва", "mail@google.com"));

        }
        List<Contact> oldGroups = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.contacts().removeContact(oldGroups.get(index));
        List<Contact> newGroups = app.contacts().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void DeleteAllContactsTests() {

        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Москва", "mail@google.com"));
        }
        app.contacts().removeAllContacts();
        var contactsCount = app.contacts().getCountContact();
        Assertions.assertEquals(0, contactsCount);
    }
}
