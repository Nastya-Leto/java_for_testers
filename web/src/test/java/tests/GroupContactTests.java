package tests;

import model.Contact;
import model.ContactAndGroupPair;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GroupContactTests extends TestBase {

    @Test
    public void addContactInGroup() {

        app.groups().checkListGroupsNotEmpty();

        ContactAndGroupPair pairUnrelatedContactsAndGroups = app.contacts().getPairUnrelatedContactsAndGroups();
        Contact testContact = pairUnrelatedContactsAndGroups.contact();
        Group testGroup = pairUnrelatedContactsAndGroups.group();

        app.contacts().addingContactToGroup(testContact, testGroup);
        Assertions.assertTrue(app.hbm().getGroupsInContact(testContact).contains(testGroup));
    }


    @Test
    public void removeContactFromGroup() {
        app.groups().checkListGroupsNotEmpty();
        ContactAndGroupPair contactWithOutAllGroups = app.contacts().getContactIncludedFromGroup();
        Contact testContact = contactWithOutAllGroups.contact();
        Group testGroup = contactWithOutAllGroups.group();

        app.contacts().removeContactFromGroup(testContact, testGroup);
        Assertions.assertFalse(app.hbm().getContactsInGroup(testGroup).contains(testContact));
    }
}

