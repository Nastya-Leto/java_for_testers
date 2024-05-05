package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ModifyContactTest extends TestBase {

    @Test
    public void modifyContactTest() {

        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Москва", "mail@google.com"));
        }
        List<Contact> oldGroups = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        Contact testData = new Contact().withFirstName("modified Сноу");
        app.contacts().modifyContacts(oldGroups.get(index), testData);
        List<Contact> newContact = app.contacts().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        final Comparator<Contact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContact);
    }
}
