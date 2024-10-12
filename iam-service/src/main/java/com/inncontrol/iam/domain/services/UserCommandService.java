package com.inncontrol.iam.domain.services;


import com.inncontrol.iam.domain.model.aggregates.User;
import com.inncontrol.iam.domain.model.commands.RefreshTokenCommand;
import com.inncontrol.iam.domain.model.commands.SignInCommand;
import com.inncontrol.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<User> handle(SignInCommand command);
    Optional<User> handle(RefreshTokenCommand command);
}
