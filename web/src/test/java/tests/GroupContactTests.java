package tests;

import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class GroupContactTests extends TestBase {

    @Test
    public void addContactInGroup() {

        Contact testContact = null;
        Group testGroup = null;

        if (app.hbm().getGroupListFromDb().isEmpty()) {
            app.groups().createGroup(new Group("", "name", "header", "footer"));
        }

        List<Group> fullListOfGroups = app.hbm().getGroupListFromDb();
        int groupsCount = fullListOfGroups.size();
        List<Contact> fullListOfContacts = app.hbm().getContactFromDb();

        for (Contact contact : fullListOfContacts) {
            List<Group> groupsInContact = app.hbm().getGroupsInContact(contact);
            int contactGroupsCount = groupsInContact.size();
            if (contactGroupsCount < groupsCount) {
                testContact = contact;

                for (Group group : fullListOfGroups) {
                    if (!groupsInContact.contains(group)) {
                        testGroup = group;
                        break;
                    }
                }
                break;
            }
        }

        if (testContact == null) {
            app.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Москва", "mail@google.com"));
            List<Contact> contactFromDb = app.hbm().getContactFromDb();
            int maxIndex = contactFromDb.size() - 1;
            testContact = contactFromDb.get(maxIndex);
            testGroup = fullListOfGroups.get(0);
        }

        app.contacts().addingContactToGroup(testContact, testGroup);
        Assertions.assertTrue(app.hbm().getGroupsInContact(testContact).contains(testGroup));
    }


    @Test
    public void removeContactFromGroup() {

        Contact testContact = null;
        Group testGroup = null;

        if (app.hbm().getGroupListFromDb().isEmpty()) {
            app.groups().createGroup(new Group("", "name", "header", "footer"));
        }

        List<Group> fullListOfGroups = app.hbm().getGroupListFromDb();

        for (Group group : fullListOfGroups) {
            List<Contact> groupsInContact = app.hbm().getContactsInGroup(group);

            if (!groupsInContact.isEmpty()) {
                testContact = groupsInContact.get(0);
                testGroup = group;
                break;
            }
        }
        if (testGroup == null) {
            List<Contact> contactFromDb = app.hbm().getContactFromDb();
            if (contactFromDb.isEmpty()) {
                app.contacts().createContact(new Contact("", "Сноу", "Джон", "Самара", "ввв"));
            }
            testContact = contactFromDb.get(0);
            testGroup = fullListOfGroups.get(0);
            app.contacts().addingContactToGroup(testContact, testGroup);
        }
        app.contacts().removeContactFromGroup(testContact, testGroup);
        Assertions.assertFalse(app.hbm().getContactsInGroup(testGroup).contains(testContact));
    }
}

