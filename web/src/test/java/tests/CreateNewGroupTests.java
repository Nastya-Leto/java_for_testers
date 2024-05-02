package tests;

import model.Group;
import org.junit.jupiter.api.Test;

public class CreateNewGroupTests extends TestBase {

    @Test
    public void createNewGroupTests() {

        app.groups().createGroup(new Group("name", "header", "footer"));
    }

}
