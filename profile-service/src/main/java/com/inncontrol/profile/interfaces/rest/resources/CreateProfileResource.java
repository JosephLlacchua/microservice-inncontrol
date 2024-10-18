package com.inncontrol.profile.interfaces.rest.resources;

public record CreateProfileResource(
        String firstName,
        String lastName,
        String phoneNumber,
        String email
        ) { }
