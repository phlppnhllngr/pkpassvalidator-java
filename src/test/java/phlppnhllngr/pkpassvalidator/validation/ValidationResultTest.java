package phlppnhllngr.pkpassvalidator.validation;

import org.testng.annotations.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ValidationResultTest {

    @Test
    void test_equals_contract() {
        EqualsVerifier.simple().forClass(ValidationResult.class).verify();
    }

    @Test
    void test_tostring() {
        ToStringVerifier.forClass(ValidationResult.class).withFailOnExcludedFields(true).verify();
    }

}
