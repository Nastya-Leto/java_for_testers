package manager;

import model.Contact;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void initContactCreation() {

        click(By.linkText("add new"));
    }

    public void fillContactForm(Contact contactInfo) {

        type(By.name("firstname"), contactInfo.firstName());
        type(By.name("middlename"), contactInfo.middleName());
        type(By.name("address"), contactInfo.address());
        type(By.name("email"), contactInfo.email());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    public void createContact(Contact contactInfo) {
        initContactCreation();
        fillContactForm(contactInfo);
        submitContactCreation();
    }

    public void openHomePage() {

        click(By.linkText("home"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    public void removeContact() {
        openHomePage();
        selectContact();
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
}
