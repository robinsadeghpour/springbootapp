package com.demo.springbootapp.services;

import com.demo.springbootapp.model.User;
import com.demo.springbootapp.support.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceTest {

    public static final User USER = new User("robin", "sadeghpour", "r.sadegh.f@gmail.com");

    @InjectMocks
    private UserService underTest;
    @Mock
    private Validator validatorMock;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        when(validatorMock.validateEmail(any())).thenReturn(true);
    }

    @AfterEach
    public void verifyMock() {
        verify(validatorMock).validateEmail(same(USER.getMail()));
    }

    @Test
    void createNewUser() throws Exception {
        User result = underTest.createNewUser(USER);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(USER.getName(), result.getName());
        assertEquals(USER.getVorname(), result.getVorname());
        assertEquals(USER.getMail(), result.getMail());
    }

    @Test
    void createNewUserNonValidEmail() {
        when(validatorMock.validateEmail(any())).thenReturn(false);
        assertThrows(Exception.class, () -> underTest.createNewUser(USER));
    }

    @Test
    void updateUserInstance() throws Exception {
        User result = underTest.updateUserInstance(USER, USER);
        assertSame(USER, result);
    }

    @Test
    void updateUserInstanceNonValidEmail() {
        when(validatorMock.validateEmail(any())).thenReturn(false);
        assertThrows(Exception.class, () -> underTest.createNewUser(USER));
    }
}