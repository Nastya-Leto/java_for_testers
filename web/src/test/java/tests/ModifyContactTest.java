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
        List<Contact> oldContactList = app.hbm().getContactFromDb();
        var rnd = new Random();
        var index = rnd.nextInt(oldContactList.size());

        Contact testData = new Contact().withFirstName("modified Сноу");

        final Contact oldContact = oldContactList.get(index);//получить
        app.contacts().modifyContacts(oldContact, testData);
        List<Contact> newContact = app.hbm().getContactFromDb();
        var expectedList = new ArrayList<>(oldContactList);
        expectedList.set(index, testData.withId(oldContact.id())); //помещает элемент в указанный индекс листа
        final Comparator<Contact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContact);
    }
}
