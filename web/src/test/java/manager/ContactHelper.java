package manager;

import model.Contact;
import model.ContactAndGroupPair;
import model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(Contact contactInfo) {

        type(By.name("lastname"), contactInfo.lastName());
        type(By.name("firstname"), contactInfo.firstName());
        type(By.name("address"), contactInfo.address());
        type(By.name("email"), contactInfo.email());
    }

    public void modifyContacts(Contact contact, Contact modifyContact) {
        openHomePage();
        selectContactEdit(contact);
        fillContactForm(modifyContact);
        submitContactUpdate();
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    private void submitContactUpdate() {
        click(By.name("update"));
    }


    public void createContact(Contact contactInfo) {
        initContactCreation();
        fillContactForm(contactInfo);
        submitContactCreation();
    }

    public void openHomePage() {

        click(By.linkText("home"));
    }

    private void selectContact(Contact contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void selectGroupList(Group group) {
        click(By.cssSelector(String.format("[name='to_group'] [value='%s'] ", group.id())));
    }

    private void selectGroupFromFilter(Group group) {
        click(By.cssSelector(String.format("[name='group'] option[value='%s'] ", group.id())));
    }

    private void selectContactEdit(Contact contact) {

        manager.driver.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id()))).click();

    }

    private void removeSelectedContact() {
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    public void removeContact(Contact contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
    }

    public int getCountContact() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectAllContacts() {
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }

    private void clickSelectGroup() {
        click(By.name("to_group"));
    }

    private void clickAddFromGroupButton() {
        click(By.name("add"));
    }

    private void clickRemoveFromGroupButton() {
        click(By.name("remove"));
    }

    private void clickCheckBoxId(Contact contact) {
        click(By.cssSelector(String.format(" input[value='%s'] ", contact.id())));
    }

    public void addingContactToGroup(Contact contact, Group group) {
        openHomePage();
        clickCheckBoxId(contact);
        clickSelectGroup();
        selectGroupList(group);
        clickAddFromGroupButton();
    }

    public void removeContactFromGroup(Contact contact, Group group) {
        openHomePage();
        selectGroupFromFilter(group);
        clickCheckBoxId(contact);
        clickRemoveFromGroupButton();
    }

    public List<Contact> getListGroup() {
        openHomePage();
        var groups = new ArrayList<Contact>();
        var options = manager.driver.findElements(By.cssSelector("option"));
        for (var option : options) {
            var id = option.getAttribute("value");
            groups.add(new Contact().withId(id));
        }
        return groups;
    }


    public List<Contact> getList() {
        openHomePage();
        var contacts = new ArrayList<Contact>();
        var trs = manager.driver.findElements(By.cssSelector("[name='entry']"));
        for (var tr : trs) {
            var lastName = tr.findElement(By.xpath(".//td[2]")).getText();
            var firstName = tr.findElement(By.xpath(".//td[3]")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new Contact().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }

    public String getPhones(Contact contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public String getAddress(Contact contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    public String getEmail(Contact contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public ContactAndGroupPair getContactIncludedFromGroup() {

        List<Group> fullListOfGroups = manager.hbm().getGroupListFromDb(); //получить список групп

        Contact testContact = null;
        Group testGroup = null;

        for (Group group : fullListOfGroups) {
            List<Contact> groupsInContact = manager.hbm().getContactsInGroup(group);// получение списка контактов в группе

            if (!groupsInContact.isEmpty()) { //если список контактов не пустой, то присвоить testContact первый контакт из списка, а testGroup группу, которую проверяли
                testContact = groupsInContact.get(0);
                testGroup = group;
                break;//после присвоения значения перемнным  - выйти из цикла
            }
        }

        if (testGroup == null) {
            List<Contact> contactFromDb = manager.hbm().getContactFromDb();
            if (contactFromDb.isEmpty()) {
                manager.contacts().createContact(new Contact("", "Сноу", "Джон", "Самара", "ввв", "", "", "", "", "", ""));
            }
            testContact = contactFromDb.get(0);
            testGroup = fullListOfGroups.get(0);
            manager.contacts().addingContactToGroup(testContact, testGroup);
        }
        return new ContactAndGroupPair(testGroup, testContact);
    }

    public ContactAndGroupPair getPairUnrelatedContactsAndGroups() {

        Contact testContact = null;
        Group testGroup = null;
        List<Group> fullListOfGroups = manager.hbm().getGroupListFromDb();//получить список всех групп
        int groupsCount = fullListOfGroups.size(); //присвоить переменной размер списка
        List<Contact> fullListOfContacts = manager.hbm().getContactFromDb(); //получить список всех контаков

        for (Contact contact : fullListOfContacts) {
            List<Group> groupsInContact = manager.hbm().getGroupsInContact(contact);//получить список групп у контакта
            int contactGroupsCount = groupsInContact.size();////присвоить переменной размер списка групп у контакта
            if (contactGroupsCount < groupsCount) {//если размер списка групп у контакта меньше размер списка всех групп
                testContact = contact; //присвоить перемнной этот контакт

                for (Group group : fullListOfGroups) {// находим группу, которой нет в списке
                    if (!groupsInContact.contains(group)) {
                        testGroup = group;
                        break;
                    }
                }
                break;
            }
        }

        if (testContact == null) {
            manager.contacts().createContact(new Contact("", "Дейенерис", "Таргариен", "Москва", "mail@google.com", "", "", "", "", "", ""));
            List<Contact> contactFromDb = manager.hbm().getContactFromDb();
            int maxIndex = contactFromDb.size() - 1;
            testContact = contactFromDb.get(maxIndex);
            testGroup = fullListOfGroups.get(0);
        }
        return new ContactAndGroupPair(testGroup, testContact);
    }
}
