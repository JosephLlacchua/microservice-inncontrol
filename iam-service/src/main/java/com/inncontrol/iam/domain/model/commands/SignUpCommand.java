package com.inncontrol.iam.domain.model.commands;



import com.inncontrol.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
