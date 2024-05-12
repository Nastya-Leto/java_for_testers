package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CreateNewContactTest extends TestBase {

    public static List<Contact> contactProvider() throws IOException {
        var result = new ArrayList<Contact>(List.of(new Contact("", "last Name", "", "", "")));
        for (var firstName : List.of("", "first Name")) {
            for (var lastName : List.of("", "last Name")) {
                for (var email : List.of("", "email")) {
                    for (var address : List.of("", "address")) {
                        result.add(new Contact("", lastName, firstName, address, email));
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

    public static List<Contact> singleRandomContact() {
        return List.of(new Contact()
                .withFirstName(CommonFunction.randomString(5))
                .withLastName(CommonFunction.randomString(3))
                .withAddress(CommonFunction.randomString(11))
                .withEmail(CommonFunction.randomString(9)));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void createNewMultipleContactTest(Contact contact) {

        List<Contact> oldContacts = app.hbm().getContactFromDb();
        app.contacts().createContact(contact);
        List<Contact> newContacts = app.hbm().getContactFromDb();
        final Comparator<Contact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void createNewSingleContactTest(Contact contact) {

        List<Contact> oldContacts = app.hbm().getContactFromDb();
        app.contacts().createContact(contact);
        List<Contact> newContacts = app.hbm().getContactFromDb();
        final Comparator<Contact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }
}
