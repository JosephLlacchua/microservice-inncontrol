package com.inncontrol.profile.domain.model.queries;


import com.inncontrol.profile.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
