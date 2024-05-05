package manager;

import model.Contact;
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

        type(By.name("firstname"), contactInfo.lastName());
        type(By.name("middlename"), contactInfo.middleName());
        type(By.name("address"), contactInfo.address());
        type(By.name("email"), contactInfo.email());
    }

    public void modifyContacts(Contact contact) {
        openHomePage();
        manager.driver.findElement(By.xpath("//img[@alt=\'Edit\']")).click();
        fillContactForm(contact);
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

    public List<Contact> getList() {
        openHomePage();
        var contacts = new ArrayList<Contact>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (var tr : trs) {
            var lastName = tr.findElement(By.xpath("//td[2]")).getText();
            var firstName = tr.findElement(By.xpath("//td[3]")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new Contact().withId(id).withLastName(lastName).withMiddleName(firstName));
        }
        return contacts;
    }
}
