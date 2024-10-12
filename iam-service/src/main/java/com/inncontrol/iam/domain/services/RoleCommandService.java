package com.inncontrol.iam.domain.services;


import com.inncontrol.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
