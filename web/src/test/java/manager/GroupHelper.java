package manager;

import model.Group;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {


    public GroupHelper(ApplicationManager manager) {

        super(manager);
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
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
        type(By.name("group_footer"), group.footer());
    }

    public void modifyGroups(Group group, Group modifiedGroup) {
        openGroupPage();
        selectGroup(group);
        click(By.name("edit"));
        fillGroupForm(modifiedGroup);
        submitGroupUpdate();
        openGroupPage();
    }

    public void removeGroup(Group group) {
        openGroupPage();
        selectGroup(group);
        removeSelectedGroup();
        returnToGroupsPage();
    }

    private void selectGroup(Group group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    private void removeSelectedGroup() {
        click(By.name("delete"));
    }

    private void submitGroupUpdate() {
        click(By.name("update"));
    }


    public int getCount() {
        openGroupPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupPage();
        selectAllGroups();
        removeSelectedGroup();
    }


    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<Group> getList() {
        openGroupPage();
        var groups = new ArrayList<Group>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans) {
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new Group().withId(id).withName(name));
        }
        return groups;
    }
}