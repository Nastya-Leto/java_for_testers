package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateNewContactTest extends TestBase {

    public static List<Contact> contactProvider() throws IOException {
        var result = new ArrayList<Contact>(List.of(new Contact("", "first Name", "", "", "")));
        for (var firstName : List.of("", "first Name")) {
            for (var middleName : List.of("", "middle Name")) {
                for (var email : List.of("", "email")) {
                    for (var address : List.of("", "address")) {
                        result.add(new Contact("", firstName, middleName, email, address));
                    }
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<Contact>>() {
        });
        result.addAll(value);
        return result;
    }

    @Test
    public void createNewContactTest() {

        int contactCount = app.contacts().getCountContact();
        app.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Самара", "mail@google.com"));
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
