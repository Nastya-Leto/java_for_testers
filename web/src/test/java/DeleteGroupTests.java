import org.junit.jupiter.api.Test;


public class DeleteGroupTests extends TestBase {

    @Test
    public void deleteGroupTests() {
        openGroupPage();
        if (isGroupPresent()) {
            createGroup();

        }
        removeGroup();
    }

}
