package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class CreateNewGroupTests extends TestBase {

    private static final String GROUP_NAME = "group name";
    private static final String GROUP_HEADER = "group header";
    private static final String GROUP_FOOTER = "group footer";
    private static final String EMPTY = "";
    private static final List<Group> INIT_GROUPS = List.of(new Group("", EMPTY, EMPTY, EMPTY),
            new Group("", EMPTY, EMPTY, GROUP_FOOTER),
            new Group("", EMPTY, GROUP_HEADER, EMPTY),
            new Group("", EMPTY, GROUP_HEADER, GROUP_FOOTER),
            new Group("", GROUP_NAME, EMPTY, EMPTY),
            new Group("", GROUP_NAME, EMPTY, GROUP_FOOTER),
            new Group("", GROUP_NAME, GROUP_HEADER, EMPTY),
            new Group("", GROUP_NAME, GROUP_HEADER, GROUP_FOOTER));

    public static List<Group> groupProvider() {
        var result = new ArrayList<Group>(List.of(new Group("", "group name", "", "")));
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new Group().withName(name).withHeader(header).withFooter(footer));
                }
            }
            for (int i = 0; i < 5; i++) {
                result.add(new Group()
                        .withName(randomString(i * 10))
                        .withHeader(randomString(i * 10))
                        .withFooter(randomString(i * 10)));
            }
        }
        return result;
    }

    public static List<Group> groupProvider2() {

        List<Group> result = new ArrayList<>(INIT_GROUPS);

        for (int i = 0; i < 5; i++) {
            result.add(generateRandomGroup(i));
        }
        return result;
    }

    private static Group generateRandomGroup(int i) {
        String randomName = randomString(i * 10);
        String randomHeader = randomString(i * 10);
        String randomFooter = randomString(i * 10);

        return new Group("", randomName, randomHeader, randomFooter);
    }



    @Test
    public void createNewGroupTests() {

        int groupCount = app.groups().getCount();
        app.groups().createGroup(new Group("", "name", "header", "footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void createNewMultipleGroupsTests(Group group) {

        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }
}
