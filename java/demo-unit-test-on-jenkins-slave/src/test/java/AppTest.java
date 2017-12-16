import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO comments
 *
 * @author Willow
 * @date 12/16/17
 */
public class AppTest {
    @Test
    public void read() throws Exception {
        App app = new App();
        String string = app.read();
        assertEquals("jenkins-slave", string);
    }
}