package com.inncontrol.profile.domain.services;


import com.inncontrol.profile.domain.model.aggregates.Profile;
import com.inncontrol.profile.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
