package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class CreateNewContactTest extends TestBase {

    public static List<Contact> contactProvider() {
        var result = new ArrayList<Contact>(List.of(new Contact("first Name", "", "", "")));
        for (var firstName : List.of("", "first Name")) {
            for (var middleName : List.of("", "middle Name")) {
                for (var email : List.of("", "email")) {
                    for (var address : List.of("", "address")) {
                        result.add(new Contact(firstName, middleName, email, address));
                    }
                }
                for (int i = 0; i < 5; i++) {
                    result.add(new Contact(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
                }
            }
        }
        return result;
    }

    @Test
    public void createNewContactTest() {

        int contactCount = app.contacts().getCountContact();
        app.contacts().createContact(new Contact("Дейенерис", "Таргариен", "Самара", "mail@google.com"));
        int newContactCount = app.contacts().getCountContact();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void createNewMultipleContactTest(Contact contact) {

        int contactCount = app.contacts().getCountContact();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCountContact();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
