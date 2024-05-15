package manager;

import model.Contact;
import model.Group;
import org.openqa.selenium.By;

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
        //click(By.cssSelector(String.format("option[value='%s']", group.id())));
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
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void selectGroupFromList(Contact contact) {
        click(By.cssSelector(String.format("option[value='%s']", contact.id())));
    }

    private void clickSelectGroup() {
        click(By.name("to_group"));
    }

    private void clickSelectFilterGroup(Group group) {
        click(By.name("group"));
    }

    private void clickAddFromGroupButton() {
        click(By.name("add"));
    }

    private void clickDeleteButton() {
        click(By.cssSelector("input[value]"));
    }

    private void clickRemoveFromGroupButton() {
        click(By.name("remove"));
    }

    private void clickCheckBox() {
        click(By.name("selected[]"));
    }

    private void clickCheckBoxId(Contact contact) {
        //click(By.cssSelector(String.format("[name='selected[]'] [value='%s'] ", contact.id())));
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
}
