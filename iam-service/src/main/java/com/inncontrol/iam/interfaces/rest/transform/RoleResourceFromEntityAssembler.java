package com.inncontrol.iam.interfaces.rest.transform;


import com.inncontrol.iam.domain.model.entities.Role;
import com.inncontrol.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
