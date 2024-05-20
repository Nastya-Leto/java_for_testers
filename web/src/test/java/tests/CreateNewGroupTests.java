package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static common.CommonFunction.randomString;


public class CreateNewGroupTests extends TestBase {

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


    /*public static List<Group> singleRandomGroup() {
        return List.of(new Group()
                .withName(CommonFunction.randomString(5))
                .withFooter(CommonFunction.randomString(2))
                .withHeader(CommonFunction.randomString(9)));
    }*/ //Создание новой группы черз список

    public static Stream<Group> randomGroups() {
        Supplier<Group> randomGroup = () -> new Group()
                .withName(CommonFunction.randomString(5))
                .withFooter(CommonFunction.randomString(2))
                .withHeader(CommonFunction.randomString(9));
        return Stream.generate(randomGroup).limit(1);
    }


    private static Group generateRandomGroup(int i) {
        String randomName = randomString(i);
        String randomHeader = randomString(i);
        String randomFooter = randomString(i);
        return new Group("", randomName, randomHeader, randomFooter);
    }

    @ParameterizedTest
    @MethodSource("randomGroups")
    public void createNewSingleGroupTests(Group group) {
        List<Group> oldGroups = app.hbm().getGroupListFromDb();
        app.groups().createGroup(group);
        List<Group> newGroups = app.hbm().getGroupListFromDb();

        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList(); //строим списко групп, которры е не встречались в старом
        var newId = extraGroups.get(0).id(); //берем из списка любой элемент

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newGroups));
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
