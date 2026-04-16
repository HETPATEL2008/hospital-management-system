import com.hospital.User;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testConstructor_registerUser_validData() {

        User user = new User("AdminUser", "Admin@1234", "ADMIN");

        assertEquals("AdminUser", user.getUsername());
        assertEquals("Admin@1234", user.getPassword());
        assertEquals("ADMIN", user.getRole());
    }

    @Nested
    class RegisterUserConstructor {

        @Test
        void shortUsername_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new User("ad", "Admin@1234", "ADMIN"));
        }

        @Test
        void passwordWithNoSpecialChar_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new User("AdminUser", "Admin1234", "ADMIN"));
        }

        @Test
        void invalidRole_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new User("AdminUser", "Admin@1234", "DOCTOR"));
        }
    }

    @Test
    void testConstructor_fetchUser_validData() {

        User user = new User(101, "AdminUser", "Admin@1234", "ADMIN");

        assertEquals(101, user.getUserId());
        assertEquals("AdminUser", user.getUsername());
        assertEquals("Admin@1234", user.getPassword());
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    void testSetUserId() {

        User user = new User("AdminUser", "Admin@1234", "ADMIN");
        user.setUserId(101);

        assertEquals(101, user.getUserId());
        assertThrows(IllegalArgumentException.class, () -> user.setUserId(-5));
    }

    @Test
    void testSetUsername() {

        User user = new User("AdminUser", "Admin@1234", "ADMIN");
        user.setUsername("Admin");

        assertEquals("Admin", user.getUsername());
        assertThrows(IllegalArgumentException.class, () -> user.setUsername("No Space"));
    }

    @Test
    void testSetPassword() {

        User user = new User("AdminUser", "Admin@1234", "ADMIN");
        user.setPassword("Admin@1234");

        assertEquals("Admin@1234", user.getPassword());
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("Admin1234"));
    }

    @Test
    void testSetRole() {

        User user = new User("AdminUser", "Admin@1234", "ADMIN");
        user.setRole("ADMIN");

        assertEquals("ADMIN", user.getRole());
        assertThrows(IllegalArgumentException.class, () -> user.setRole("DOCTOR"));
    }

    @Test
    void testToString() {
        User user = new User("AdminUser", "Admin@1234", "ADMIN");
        assertFalse(user.toString().contains(user.getPassword()));
    }
}
