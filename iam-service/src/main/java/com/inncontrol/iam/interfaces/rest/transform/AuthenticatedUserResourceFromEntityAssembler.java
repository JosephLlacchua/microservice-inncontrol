package com.inncontrol.iam.interfaces.rest.transform;


import com.inncontrol.iam.domain.model.aggregates.User;
import com.inncontrol.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), user.getSerializedRoles(), token);
    }
}
