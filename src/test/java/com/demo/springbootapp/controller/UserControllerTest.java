package com.demo.springbootapp.controller;

import com.demo.springbootapp.error.NoUsersException;
import com.demo.springbootapp.error.UserNotFoundException;
import com.demo.springbootapp.model.User;
import com.demo.springbootapp.repository.UserRepository;
import com.demo.springbootapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserControllerTest {
    public static final User USER = new User("robin", "sadeghpour", "r.sadegh.f@gmail.com");

    @InjectMocks
    private UserController underTest;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private UserService userServiceMock;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        when(userRepositoryMock.findAll()).thenReturn(of(USER));

        ResponseEntity<List<User>> result = underTest.getAllUsers(null);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertNotNull(result.getBody());
        assertEquals(result.getBody().size(), 1);
        assertSame(USER, result.getBody().get(0));

        verify(userRepositoryMock).findAll();
    }

    private static Object[][] testGetAllUsersByNameDataProvider() {
        User user2 = new User("robin", "vorname", "mail");
        return new Object[][]{
                {of(USER), "robin", of(USER)},
                {of(USER, user2), "robin", of(USER, user2)},
        };
    }

    @ParameterizedTest
    @MethodSource("testGetAllUsersByNameDataProvider")
    public void testGetAllUsersByName(List<User> users, String name, List<User> expectedResult) {
        when(userRepositoryMock.findByName(any())).thenReturn(users);

        ResponseEntity<List<User>> result = underTest.getAllUsers(name);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(expectedResult.size(), result.getBody().size());

        verify(userRepositoryMock).findByName(same(name));
    }

    private static Object[][] testGetAllUsersEmptyDataProvider() {
        return new Object[][]{
                {null},
                {"name"},
        };
    }

    @ParameterizedTest
    @MethodSource("testGetAllUsersEmptyDataProvider")
    public void testGetAllUsersEmpty(String name) {
        if (name == null) {
            when(userRepositoryMock.findAll()).thenReturn(of());
        } else {
            when(userRepositoryMock.findByName(any())).thenReturn(of());
        }

        assertThrows(NoUsersException.class, () -> underTest.getAllUsers(name));

        if (name == null) {
            verify(userRepositoryMock).findAll();
        } else {
            verify(userRepositoryMock).findByName(same(name));
        }
    }

    @Test
    public void testGetUserById() {
        when(userRepositoryMock.findById(any())).thenReturn(Optional.of(USER));

        ResponseEntity<User> result = underTest.getUserById(USER.getId());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertSame(USER, result.getBody());

        verify(userRepositoryMock).findById(same(USER.getId()));
    }

    @Test
    public void testGetUserByIdEmpty() {
        when(userRepositoryMock.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> underTest.getUserById(USER.getId()));

        verify(userRepositoryMock).findById(same(USER.getId()));
    }

    @Test
    public void testCreateUser() {
        when(userRepositoryMock.save(any())).thenReturn(USER);
        when(userServiceMock.createNewUser(any())).thenReturn(USER);

        ResponseEntity<User> result = underTest.createUser(USER);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertSame(USER, result.getBody());

        verify(userRepositoryMock).save(any(User.class));
        verify(userServiceMock).createNewUser(same(USER));
    }

    @Test
    public void testUpdateUser() {
        when(userRepositoryMock.findById(any())).thenReturn(Optional.of(USER));
        when(userRepositoryMock.save(any())).thenReturn(USER);
        when(userServiceMock.updateUserInstance(any(), any())).thenReturn(USER);

        ResponseEntity<User> result = underTest.updateUser(USER.getId(), USER);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(USER, result.getBody());

        verify(userRepositoryMock).findById(same(USER.getId()));
        verify(userRepositoryMock).save(same(USER));
        verify(userServiceMock).updateUserInstance(same(USER), same(USER));
    }

    @Test
    public void testUpdateUserEmpty() {
        when(userRepositoryMock.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> underTest.updateUser(USER.getId(), USER));

        verify(userRepositoryMock).findById(same(USER.getId()));
        verifyNoMoreInteractions(userRepositoryMock);
        verifyNoInteractions(userServiceMock);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepositoryMock).deleteById(any());

        ResponseEntity<HttpStatus> result = underTest.deleteUser(USER.getId());
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());

        verify(userRepositoryMock).deleteById(same(USER.getId()));
    }

    @Test
    public void testDeleteAllUsers() {
        doNothing().when(userRepositoryMock).deleteAll();

        ResponseEntity<HttpStatus> result = underTest.deleteAllUsers();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());

        verify(userRepositoryMock).deleteAll();
    }
}