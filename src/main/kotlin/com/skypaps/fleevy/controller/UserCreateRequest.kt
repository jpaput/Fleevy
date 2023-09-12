package com.skypaps.fleevy.controller

import com.skypaps.fleevy.data.Role
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

class UserCreateRequest(
        @NotBlank(message = "email can't be empty")
        var email: String,
        @NotBlank(message = "first_name can't be empty")
        var firstName: String,
        @NotBlank(message = "last_name  can't be empty")
        var lastName: String,
        @NotBlank(message = "created_on can't be empty")
        var createdOn: LocalDateTime,
        var role: Role
)
