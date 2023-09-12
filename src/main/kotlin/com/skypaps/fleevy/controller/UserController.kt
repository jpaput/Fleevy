package com.skypaps.fleevy.controller

import com.skypaps.fleevy.model.UserDTO
import com.skypaps.fleevy.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class UserController(private val service : UserService) {

    @GetMapping("all-users")
    fun getAllUsers(): ResponseEntity<List<UserDTO>> = ResponseEntity(service.getAllUsers(), HttpStatus.OK)

    @GetMapping("user/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> =
            ResponseEntity(service.getUserById(id), HttpStatus.OK)

    @PostMapping("create")
    fun createUser(
            @Valid @RequestBody createRequest: UserCreateRequest
    ): ResponseEntity<UserDTO> = ResponseEntity(service.createUser(createRequest), HttpStatus.OK)

    @PatchMapping("update/{id}")
    fun updateUser(
            @PathVariable id: Long,
            @Valid @RequestBody updateRequest: UserUpdateRequest
    ): ResponseEntity<UserDTO> = ResponseEntity(service.updateUser(id, updateRequest), HttpStatus.OK)

    @DeleteMapping("delete/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> =
            ResponseEntity(service.deleteUser(id), HttpStatus.OK)
}
