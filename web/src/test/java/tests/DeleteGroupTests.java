package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DeleteGroupTests extends TestBase {

    @Test
    public void deleteGroupTests() {


        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("name", "header", "footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    public void DeleteAllGroupsTests() {

        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("name", "header", "footer"));
        }
        app.groups().removeAllGroups();
        var groupCount = app.groups().getCount();
        Assertions.assertEquals(0, groupCount);
    }
}
