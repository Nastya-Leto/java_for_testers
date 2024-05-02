package manager;

import model.Group;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {


    public GroupHelper(ApplicationManager manager) {

        super(manager);
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(Group group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void fillGroupForm(Group group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.header());
    }

    public void removeGroup() {
        openGroupPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupsPage();
    }

    private void removeSelectedGroup() {
        click(By.name("delete"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }
}