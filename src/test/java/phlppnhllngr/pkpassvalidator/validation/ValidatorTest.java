package phlppnhllngr.pkpassvalidator.validation;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ValidatorTest {

    @Test
    void test() throws Exception {
        byte[] passContent = getClass().getResourceAsStream("/bad.pkpass").readAllBytes();
        ValidationResult result = new Validator().validate(passContent);
        assertNotNull(result);
    }

}
