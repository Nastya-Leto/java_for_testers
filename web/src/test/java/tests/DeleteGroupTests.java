package tests;

import model.Group;
import org.junit.jupiter.api.Test;


public class DeleteGroupTests extends TestBase {

    @Test
    public void deleteGroupTests() {

        if (app.groups().isGroupPresent()) {
            app.groups().createGroup(new Group("name", "header", "footer"));

        }
        app.groups().removeGroup();
    }

}
