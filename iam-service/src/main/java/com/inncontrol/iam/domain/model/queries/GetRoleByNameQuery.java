package com.inncontrol.iam.domain.model.queries;


import com.inncontrol.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery (Roles roleName) {
}
