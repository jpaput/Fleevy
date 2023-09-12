package com.skypaps.fleevy.controller

import com.skypaps.fleevy.data.Role

class UserUpdateRequest(
        var email: String?,
        var firstName: String?,
        var lastName: String?,
        var role: Role?
)
