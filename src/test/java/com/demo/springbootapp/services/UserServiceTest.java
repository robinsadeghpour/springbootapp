package com.demo.springbootapp.services;

import com.demo.springbootapp.error.InvalidMailException;
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
    public static final User UPDATED_USER = new User("robin", "sadeghpour-faraj", "r.sadegh.f@gmail.com");

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
    void createNewUser() throws InvalidMailException {
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
        assertThrows(InvalidMailException.class, () -> underTest.createNewUser(USER));
    }

    @Test
    void updateUserInstance() throws InvalidMailException {
        User result = underTest.updateUserInstance(UPDATED_USER, USER);
        assertNotNull(result);
        assertEquals(USER.getId(), result.getId());
        assertEquals(UPDATED_USER.getName(), result.getName());
        assertEquals(UPDATED_USER.getVorname(), result.getVorname());
        assertEquals(UPDATED_USER.getMail(), result.getMail());
    }

    @Test
    void updateUserInstanceNonValidEmail() {
        when(validatorMock.validateEmail(any())).thenReturn(false);
        assertThrows(InvalidMailException.class, () -> underTest.updateUserInstance(UPDATED_USER, USER));
    }
}