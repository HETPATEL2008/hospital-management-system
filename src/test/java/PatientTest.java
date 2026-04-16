import com.hospital.Patient;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Nested
    class RegisterPatientConstructor {

        // Name Validation
        @Test
        void nullName_shouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient(null, 25, "A+", "9876543210", "123 Main St"));
        }

        @Test
        void blankName_shouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("  ", 25, "A+", "9876543210", "123 Main St"));
        }

        @Test
        void nameTooLong_shouldThrowIllegalArgumentException() {
            String longName = "A".repeat(105);
            assertThrows(IllegalArgumentException.class, () -> new Patient(longName, 25, "A+", "9876543210", "123 Main St"));
        }

        @Test
        void nameWithInvalidChars_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John@1", 25, "A+", "9876543210", "123 Main St"));
        }

        // Age Validation
        @Test
        void negativeAge_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", -1, "A+", "9876543210", "123 Main St"));
        }

        @Test
        void ageTooHigh_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 131, "A+", "9876543210", "123 Main St"));
        }

        @Test
        void ageBoundaryZero_ShouldCreateSuccessfully() {
            Patient patient = new Patient("John Doe", 0, "A+", "9876543210", "123 Main St");
            assertEquals(0, patient.getAge());
        }

        @Test
        void ageBoundary130_ShouldCreateSuccessfully() {
            Patient patient = new Patient("John Doe", 130, "A+", "9876543210", "123 Main St");
            assertEquals(130, patient.getAge());
        }

        // Blood Group Validation
        @Test
        void nullBloodGroup_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, null, "9876543210", "123 Main St"));
        }

        @Test
        void invalidBloodGroup_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, "C+", "9876543210", "123 Main St"));
        }

        @Test
        void lowerCaseBloodGroup_shouldNormalizedToUpperCase() {
            Patient patient = new Patient("John Doe", 25, "a+", "9876543210", "123 Main St");
            assertEquals("A+", patient.getBloodGroup());
        }

        // Phone Validation
        @Test
        void nullPhone_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, "A+", null, "123 Main St"));
        }

        @Test
        void phoneTooShort_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, "A+", "98765", "123 Main St"));
        }

        @Test
        void phoneStartingWithInvalidDigit_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, "A+", "5123456789", "123 Main St"));
        }

        // Address Validation
        @Test
        void nullAddress_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, "A+", "9876543210", null));
        }

        @Test
        void blankAddress_ShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, "A+", "9876543210", "   "));
        }

        @Test
        void addressTooLong_ShouldThrowIllegalArgumentException() {
            String longAddress = "A".repeat(260);
            assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", 25, "A+", "9876543210", longAddress));
        }

        @Test
        void validData_shouldCreateSuccessfully() {
            Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
            assertEquals("John Doe", patient.getName());
            assertEquals(25, patient.getAge());
            assertEquals("A+", patient.getBloodGroup());
            assertEquals(("9876543210"), patient.getPhone());
            assertEquals("123 Main St", patient.getAddress());
        }

        @Test
        void validName_ShouldTrimWhitespace() {
            Patient patient = new Patient(" John Doe ", 25, "A+", "9876543210", "123 Main St");
            assertEquals("John Doe", patient.getName());
        }

        @Nested
        class FetchPatientConstructor {

            @Test
            void validData_ShouldSetAllFieldsCorrectly() {
                Patient patient = new Patient(1, "John Doe", 25, "A+", "9876543210", "123 Main St");
                assertEquals(1, patient.getPatientId());
                assertEquals("John Doe", patient.getName());
                assertEquals(25, patient.getAge());
                assertEquals("A+", patient.getBloodGroup());
                assertEquals("9876543210", patient.getPhone());
                assertEquals("123 Main St", patient.getAddress());
            }
        }

        @Nested
        class SetPatientId {

            @Test
            void validId_shouldSetSuccessfully() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                patient.setPatientId(101);
                assertEquals(101, patient.getPatientId());
            }

            @Test
            void zeroId_shouldThrowIllegalArgumentException() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                assertThrows(IllegalArgumentException.class, () -> patient.setPatientId(0));
            }

            @Test
            void negativeId_shouldThrowIllegalArgumentException() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                assertThrows(IllegalArgumentException.class, () -> patient.setPatientId(-5));
            }
        }

        @Nested
        class SetName {

            @Test
            void validName_shouldChangeSuccessfully() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                patient.setName("John Doe");
                assertEquals("John Doe", patient.getName());
            }

            @Test
            void invalidName_shouldThrowIllegalArgumentException() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                assertThrows(IllegalArgumentException.class, () -> patient.setName("John@1"));
            }
        }

        @Nested
        class setAge {

            @Test
            void validAge_shouldChangeSuccessfully() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                patient.setAge(25);
                assertEquals(25, patient.getAge());
            }

            @Test
            void invalidAge_shouldThrowIllegalArgumentException() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                assertThrows(IllegalArgumentException.class, () -> patient.setAge(-5));
            }
        }

        @Nested
        class SetBloodGroup {

            @Test
            void validBloodGroup_ShouldStoreAsUppercase() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                patient.setBloodGroup("b+");
                assertEquals("B+", patient.getBloodGroup());
            }

            @Test
            void invalidBloodGroup_ShouldThrowIllegalArgumentException() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                assertThrows(IllegalArgumentException.class, () -> patient.setBloodGroup("C+"));
            }
        }

        @Nested
        class SetPhone {

            @Test
            void validPhone_ShouldChangeSuccessfully() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                patient.setPhone("8765432109");
                assertEquals("8765432109", patient.getPhone());
            }

            @Test
            void invalidPhone_ShouldThrowIllegalArgumentException() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                assertThrows(IllegalArgumentException.class, () -> patient.setPhone("5123456789"));
            }
        }

        @Nested
        class SetAddress {

            @Test
            void validAddress_ShouldChangeSuccessfully() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                patient.setAddress("456 New Street");
                assertEquals("456 New Street", patient.getAddress());
            }

            @Test
            void invalidAddress_shouldThrowIllegalArgumentException() {
                Patient patient = new Patient("John Doe", 25, "A+", "9876543210", "123 Main St");
                String longAddress = "A".repeat(260);
                assertThrows(IllegalArgumentException.class, () -> patient.setAddress(longAddress));
            }
        }

        @Nested
        class toString {

            @Test
            void toString_shouldContainsRequiredFields() {
                Patient patient = new Patient(1, "John Doe", 25, "A+", "9876543210", "123 Main St");
                String result = patient.toString();
                assertTrue(result.contains("John Doe"));
                assertTrue(result.contains("25"));
                assertTrue(result.contains("A+"));
            }

            @Test
            void toString_shouldNotContainPhone() {
                Patient patient = new Patient(1, "John Doe", 25, "A+", "9876543210", "123 Main St");
                assertFalse(patient.toString().contains("9876543210"));
            }
        }
    }
}
