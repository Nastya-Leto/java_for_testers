package tests;

import common.CommonFunction;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ModifyGroupTest extends TestBase {

    @Test
    public void canModifyGroupTests() {

        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("", "name", "header", "footer"));
        }

        List<Group> oldGroups = app.hbm().getGroupListFromDb();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        Group testData = new Group().withName(CommonFunction.randomString(5));
        app.groups().modifyGroups(oldGroups.get(index), testData);
        List<Group> newGroups = app.hbm().getGroupListFromDb();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newGroups));
    }
}
