package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static common.CommonFunction.randomString;


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

    public static List<Group> groupProvider() throws IOException {
        var result = new ArrayList<Group>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new Group().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<Group>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<Group> groupProvider2() {

        List<Group> result = new ArrayList<>(INIT_GROUPS);

        for (int i = 0; i < 5; i++) {
            result.add(generateRandomGroup(i));
        }
        return result;
    }

    public static List<Group> singleRandomGroup() {
        return List.of(new Group()
                .withName(CommonFunction.randomString(5))
                .withFooter(CommonFunction.randomString(2))
                .withHeader(CommonFunction.randomString(9)));
    }


    private static Group generateRandomGroup(int i) {
        String randomName = randomString(i);
        String randomHeader = randomString(i);
        String randomFooter = randomString(i);
        return new Group("", randomName, randomHeader, randomFooter);
    }


    @Test
    public void createNewGroupTests() {

        List<Group> oldGroups = app.hbm().getGroupListFromDb();
        Group rnd = generateRandomGroup(5);
        app.groups().createGroup(rnd);
        List<Group> newGroups = app.hbm().getGroupListFromDb();
        final Comparator<Group> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(rnd);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void createNewGroupTests3(Group group) {
        List<Group> oldGroups = app.hbm().getGroupListFromDb();
        app.groups().createGroup(group);
        List<Group> newGroups = app.hbm().getGroupListFromDb();
        final Comparator<Group> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void createNewMultipleGroupsTests(Group group) {

        List<Group> oldGroups = app.hbm().getGroupListFromDb();
        app.groups().createGroup(group);
        List<Group> newGroups = app.hbm().getGroupListFromDb();
        final Comparator<Group> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }
}
