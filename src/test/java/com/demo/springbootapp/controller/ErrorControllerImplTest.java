package com.demo.springbootapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ErrorControllerImplTest {

    private ErrorControllerImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ErrorControllerImpl();
    }

    private static Object[][] handleErrorDataProvider() {
        return new Object[][]{
                {"400", "error-400"},
                {"404", "error-404"},
                {"500", "error-500"},
                {"1", "error"},
                {null, "error"}
        };
    }

    @ParameterizedTest
    @MethodSource("handleErrorDataProvider")
    public void testHandleError(String errorCode, String expectedErrorString) {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);

        when(mockedRequest.getAttribute(any())).thenReturn(errorCode);

        String result = underTest.handleError(mockedRequest);

        assertEquals(result, expectedErrorString);

    }
}