package com.inncontrol.iam;


import com.inncontrol.iam.domain.model.entities.Role;
import com.inncontrol.iam.domain.model.valueobjects.Roles;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void getDefaultRole_ShouldReturnEmployeeRole() {
        Role defaultRole = Role.getDefaultRole();
        assertNotNull(defaultRole);
        assertEquals(Roles.EMPLOYEE, defaultRole.getName());
    }

    @Test
    void toRoleFromName_ShouldThrowExceptionForInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> Role.toRoleFromName("INVALID_ROLE"));
    }

    @Test
    void validateRoleSet_ShouldReturnDefaultRoleWhenEmpty() {
        List<Role> roles = Role.validateRoleSet(List.of());
        assertNotNull(roles);
        assertEquals(1, roles.size());
        assertEquals(Roles.EMPLOYEE, roles.get(0).getName());
    }

}