package com.demo.springbootapp.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {
    private Validator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Validator();
    }

    private static Object[][] eMailDataProvider() {
        return new Object[][]{
                {"test@mail.com", true},
                {"test-test@mail.com", true},
                {"test.test@mail.com", true},
                {"test", false},
                {"test@", false},
                {"test@.com", false},
                {".com", false},
                {"test@mail", false},
                {"test.com", false},
                {"null", false},
        };
    }

    @ParameterizedTest
    @MethodSource("eMailDataProvider")
    public void testValidateEmail(String mail, boolean expected) {
        boolean result = underTest.validateEmail(mail);
        assertEquals(expected, result);
    }
}