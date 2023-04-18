import org.junit.Test;
import static org.junit.Assert.*;
public class ClassNameTest {
    @Test(expected = IllegalArgumentException.class)
    public void testLoginWithNull() {
        char[] u = {'T'};
        char[] p = {'P'};
        Login test = new Login(u, p);
        char[] testUser = null;
        char[] testPassword = null;
        //line here
        boolean answer = test.login(testUser, testPassword);
        assertTrue(answer);
    }
}
