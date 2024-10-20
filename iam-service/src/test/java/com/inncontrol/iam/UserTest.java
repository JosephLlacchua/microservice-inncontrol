package com.inncontrol.iam;

import com.inncontrol.iam.domain.model.aggregates.User;
import com.inncontrol.iam.domain.model.entities.Role;
import com.inncontrol.iam.domain.model.valueobjects.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sharon Antuanet Ivet Barrial Marin - u202114900
 * @version 1.0
 */

@SpringBootTest
public class UserTest {

    // Here we are testing the User class
    private User user;

    // Here we are setting up the user object
    @BeforeEach
    public void setUp() {
        user = new User("testuser", "password123");
    }

    // Here we are testing the addRole method
    @Test
    public void testAddRole() {
        Role role = new Role(Roles.EMPLOYEE);
        user.addRole(role);
        assertTrue(user.getRoles().contains(role));
    }

    // Here we are testing the addRoles method
    @Test
    public void testAddRoles() {
        Role role1 = new Role(Roles.EMPLOYEE);
        Role role2 = new Role(Roles.MANAGER);
        user.addRoles(List.of(role1, role2));
        assertTrue(user.getRoles().containsAll(List.of(role1, role2)));
    }

    // Here we are testing the removeRole method
    @Test
    public void testGetSerializedRoles() {
        Role role = new Role(Roles.EMPLOYEE);
        user.addRole(role);
        List<String> serializedRoles = user.getSerializedRoles();
        assertEquals(1, serializedRoles.size());
        assertEquals("EMPLOYEE", serializedRoles.get(0));
    }
}