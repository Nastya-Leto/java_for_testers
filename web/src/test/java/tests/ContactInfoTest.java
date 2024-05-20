package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ContactInfoTest extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactFromDb();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !s.isEmpty()) //фильтр пропусукает пустые строки(не равно null и не равно пустой строке)
                .collect(Collectors.joining("\n"));//склеиваем и в качестве раздедителя используем перевод строки
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress() {
        var contacts = app.hbm().getContactFromDb();
        var contact = contacts.get(0);
        var address = app.contacts().getAddress(contact);
        var expected = contact.address();
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testEmail() {
        var contacts = app.hbm().getContactFromDb();
        var contact = contacts.get(0);
        var email = app.contacts().getEmail(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, email);
    }
}

