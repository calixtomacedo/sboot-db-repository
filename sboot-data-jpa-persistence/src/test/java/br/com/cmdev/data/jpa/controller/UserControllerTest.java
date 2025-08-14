package br.com.cmdev.data.jpa.controller;

import br.com.cmdev.data.jpa.dto.AddressRequest;
import br.com.cmdev.data.jpa.dto.AddressResponse;
import br.com.cmdev.data.jpa.dto.UserRequest;
import br.com.cmdev.data.jpa.dto.UserResponse;
import br.com.cmdev.data.jpa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock // Create a mock instance of the UserService
    private UserService service;

    @InjectMocks // Inject the mocks (like service) into UserController
    private UserController userController;

    private UserRequest userRequest;
    private UserResponse userResponse;
    private AddressRequest addressRequest;
    private AddressResponse addressResponse;

    @Captor // Captures arguments passed to mocked methods
    private ArgumentCaptor<Pageable> pageableCaptor;

    private List<UserResponse> mockUserList;


    @BeforeEach
    void setUp() {
        // Initialize common test data before each test
        addressRequest = new AddressRequest("test streat", "test number", "test neighborhood", "test city", "test state");
        userRequest = new UserRequest("test", "test@example.com", "password", "USER", true, addressRequest);

        addressResponse = new AddressResponse(1L, "test streat", "test number", "test neighborhood", "test city", "test state");
        userResponse = new UserResponse(123L, "test", "test@example.com", "USER", true, "2025-01-01T00:00:00", "2025-01-01T00:00:00", addressResponse);

        UserResponse user1 = new UserResponse(123L, "test", "test@example.com", "USER", true, "2025-01-01T00:00:00", "2025-01-01T00:00:00", addressResponse); // Replace with actual UserResponse creation
        UserResponse user2 = new UserResponse(1234L, "tests", "tests@example.com", "ADMIN", false, "2025-01-02T00:00:00", "2025-01-02T00:00:00", addressResponse);
        mockUserList = List.of(user1, user2);

    }

    @Test
    void saveUserShouldReturnCreatedStatusAndUserResponse() {
        when(service.saveUser(any(UserRequest.class))).thenReturn(userResponse);
        ResponseEntity responseEntity = userController.saveUser(userRequest);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()); // Check status code
        assertNotNull(responseEntity.getBody());
        assertEquals(userResponse, responseEntity.getBody()); // Check response body
        verify(service, times(1)).saveUser(userRequest);
    }

    @Test
    void saveUserShouldHandleServiceException() {
        String errorMessage = "Database error";
        when(service.saveUser(any(UserRequest.class))).thenThrow(new RuntimeException(errorMessage));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.saveUser(userRequest);
        });
        assertEquals(errorMessage, exception.getMessage());
        verify(service, times(1)).saveUser(userRequest);
    }






    @Test
    void getAllUsers_shouldReturnOkWithUsers_whenUsersExist() {
        // Arrange
        int page = 0;
        int size = 5;
        Pageable expectedPageable = (Pageable) PageRequest.of(page, size, Sort.by("name"));

        // Mock the service call to return a list of users
        when(service.getAllUsers(any(Pageable.class))).thenReturn(mockUserList);

        // Act
        ResponseEntity<List<UserResponse>> response = userController.getAllUsers(page, size);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check for 200 OK status
        assertNotNull(response.getBody());
        assertEquals(mockUserList.size(), response.getBody().size()); // Check if the body contains the expected list size
        assertEquals(mockUserList, response.getBody()); // Check if the body contains the expected list

        // Verify that the service method was called exactly once with the correct Pageable
        verify(service, times(1)).getAllUsers(pageableCaptor.capture());
        Pageable actualPageable = pageableCaptor.getValue();
        assertEquals(expectedPageable.getPageNumber(), actualPageable.getPageNumber());
        assertEquals(expectedPageable.getPageSize(), actualPageable.getPageSize());
        assertEquals(expectedPageable.getSort(), actualPageable.getSort());
    }

    @Test
    void getAllUsers_shouldReturnNoContent_whenServiceReturnsEmptyList() {
        // Arrange
        int page = 1;
        int size = 10;
        // Mock the service call to return an empty list
        when(service.getAllUsers(any(Pageable.class))).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<UserResponse>> response = userController.getAllUsers(page, size);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Check for 204 No Content status
        assertNull(response.getBody()); // Body should be null for No Content

        // Verify that the service method was called
        verify(service, times(1)).getAllUsers(any(Pageable.class));
    }

    @Test
    void getAllUsers_shouldReturnNoContent_whenServiceReturnsNull() {
        // Arrange
        int page = 0;
        int size = 10;
        // Mock the service call to return null
        when(service.getAllUsers(any(Pageable.class))).thenReturn(null);

        // Act
        ResponseEntity<List<UserResponse>> response = userController.getAllUsers(page, size);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Check for 204 No Content status
        assertNull(response.getBody()); // Body should be null

        // Verify that the service method was called
        verify(service, times(1)).getAllUsers(any(Pageable.class));
    }

    @Test
    void getAllUsers_shouldUseDefaultPagination_whenParamsNotProvided() {
        // Arrange
        int defaultPage = 0;
        int defaultSize = 10;
        Pageable expectedPageable = PageRequest.of(defaultPage, defaultSize, Sort.by("name"));

        // Mock the service call (return value doesn't matter much for this specific check)
        when(service.getAllUsers(any(Pageable.class))).thenReturn(mockUserList);

        // Act
        // Call the method using default values (achieved by not passing them,
        // but for direct unit test, we pass the default values explicitly)
        userController.getAllUsers(defaultPage, defaultSize);

        // Assert
        // Verify that the service method was called with the correct default Pageable
        verify(service).getAllUsers(pageableCaptor.capture());
        Pageable actualPageable = pageableCaptor.getValue();
        assertEquals(expectedPageable.getPageNumber(), actualPageable.getPageNumber());
        assertEquals(expectedPageable.getPageSize(), actualPageable.getPageSize());
        assertEquals(expectedPageable.getSort(), actualPageable.getSort());
    }

}