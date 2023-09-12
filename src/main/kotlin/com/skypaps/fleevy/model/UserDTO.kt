package com.skypaps.fleevy.model

import com.skypaps.fleevy.data.Role
import java.time.LocalDateTime

data class UserDTO(
        val id: Long,
        var email: String,
        var firstName: String,
        var lastName: String,
        var createdOn: LocalDateTime,
        var role: Role)
