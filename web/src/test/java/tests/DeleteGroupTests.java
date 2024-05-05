package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DeleteGroupTests extends TestBase {

    @Test
    public void deleteGroupTests() {


        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("", "name", "header", "footer"));
        }
        List<Group> oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        List<Group> newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void DeleteAllGroupsTests() {

        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("", "name", "header", "footer"));
        }
        app.groups().removeAllGroups();
        var groupCount = app.groups().getCount();
        Assertions.assertEquals(0, groupCount);
    }
}
