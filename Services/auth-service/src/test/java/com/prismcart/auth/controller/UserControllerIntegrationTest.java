//package com.prismcart.auth.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.prismcart.auth.dto.LoginRequest;
//import com.prismcart.auth.dto.LoginResponse;
//import com.prismcart.auth.dto.RegisterRequest;
//import com.prismcart.auth.entity.Role; // Make sure this import matches your Role enum's package
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Integration test for the complete authentication and user profile retrieval flow.
// * It ensures that registration, login, and token-based access to protected endpoints work together.
// *
// * @SpringBootTest loads the full application context, making it a complete integration test.
// * @AutoConfigureMockMvc provides a MockMvc instance to perform requests against the application.
// * @Transactional ensures that each test method runs in a transaction that is rolled back at the end,
// * preventing tests from polluting the database state for subsequent tests.
// */
//@SpringBootTest
////(properties = {"spring.profiles.active=test"})
//@AutoConfigureMockMvc
//@Transactional
//@ActiveProfiles("test")
//public class UserControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void shouldRegisterLoginAndAccessProtectedEndpoint() throws Exception {
//        // --- Step 1: Register a new user ---
//        // Create a unique username for each test run to avoid conflicts
//        String username = "testuser" + System.currentTimeMillis();
//        String email = username + "@example.com";
//        String password = "securePassword123";
//
//        // Build the registration request DTO
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setUserName(username);
//        registerRequest.setEmail(email);
//        registerRequest.setPassword(password);
//        registerRequest.setRole(Role.USER); // Assuming Role is an enum with a USER value
//
//        // Perform the POST request to the /register endpoint
//        mockMvc.perform(post("/api/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(registerRequest)))
//                // Expect a 200 OK status
//                .andExpect(status().isOk())
//                // Expect the success message in the response body
//                .andExpect(content().string("User registered successfully!"));
//
//        // --- Step 2: Log in with the newly created user ---
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername(username);
//        loginRequest.setPassword(password);
//
//        // Perform the POST request to the /login endpoint and store the result
//        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequest)))
//                // Expect a 200 OK status
//                .andExpect(status().isOk())
//                // Expect the response JSON to contain a 'token' field
//                .andExpect(jsonPath("$.token").exists())
//                .andReturn();
//
//        // Extract the JWT from the login response
//        String responseBody = loginResult.getResponse().getContentAsString();
//        LoginResponse loginResponse = objectMapper.readValue(responseBody, LoginResponse.class);
//        String jwtToken = loginResponse.getToken();
//
//        // --- Step 3: Access a protected endpoint using the JWT ---
//        // Perform a GET request to the /api/user/me endpoint
//        mockMvc.perform(get("/api/user/me")
//                        // Include the JWT in the Authorization header
//                        .header("Authorization", "Bearer " + jwtToken))
//                // Expect a 200 OK status, indicating successful authentication and authorization
//                .andExpect(status().isOk())
//                // Verify the user profile details in the response body match the registered user
//                .andExpect(jsonPath("$.userName").value(username))
//                .andExpect(jsonPath("$.email").value(email))
//                .andExpect(jsonPath("$.role").value(Role.USER.name()));
//    }
//}
