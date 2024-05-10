package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ModifyGroupTest extends TestBase {

    @Test
    public void canModifyGroupTests() {

        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("", "name", "header", "footer"));
        }

        List<Group> oldGroups = app.hbm().getGroupListFromDb();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        Group testData = new Group().withName("modified name");
        app.groups().modifyGroups(oldGroups.get(index), testData);
        List<Group> newGroups = app.hbm().getGroupListFromDb();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        final Comparator<Group> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }
}
