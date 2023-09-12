package com.skypaps.fleevy.service

import com.skypaps.fleevy.controller.UserCreateRequest
import com.skypaps.fleevy.controller.UserUpdateRequest
import com.skypaps.fleevy.data.User
import com.skypaps.fleevy.exception.BadRequestException
import com.skypaps.fleevy.exception.UserNotFoundException
import com.skypaps.fleevy.model.UserDTO
import com.skypaps.fleevy.repository.UserRepository
import org.springframework.data.util.ReflectionUtils
import org.springframework.stereotype.Service
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class UserService(private val repository : UserRepository) {

    private fun convertEntityToDto(user: User): UserDTO {
        return UserDTO(
                user.id,
                user.email,
                user.firstName,
                user.lastName,
                user.createdOn,
                user.role
        )
    }


    private fun assignValuesToEntity(user: User, userCreateRequest: UserCreateRequest) {
        user.email = userCreateRequest.email
        user.firstName = userCreateRequest.firstName
        user.lastName = userCreateRequest.lastName
        user.createdOn = userCreateRequest.createdOn
        user.role = userCreateRequest.role
    }

    private fun checkForUserId(id: Long) {
        if (!repository.existsById(id)) {
            throw UserNotFoundException("User with ID: $id does not exist!")
        }
    }

    fun getAllUsers(): List<UserDTO> =
            repository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList())

    fun getUserById(id: Long): UserDTO {
        checkForUserId(id)
        val user: User = repository.findUserById(id)
        return convertEntityToDto(user)
    }

    fun createUser(createRequest: UserCreateRequest): UserDTO {
        /*if (repository.doesEmailExist(createRequest.email)) {
            throw BadRequestException("There is already an user with email: ${createRequest.email}")
        }*/
        val user = User()
        assignValuesToEntity(user, createRequest)
        val savedUser = repository.save(user)
        return convertEntityToDto(savedUser)
    }

    fun updateUser(id: Long, updateRequest: UserUpdateRequest): UserDTO {
        checkForUserId(id)
        val existingUser: User = repository.findUserById(id)

        for (prop in UserUpdateRequest::class.memberProperties) {
            if (prop.get(updateRequest) != null) {
                ReflectionUtils.findRequiredField(User::class.java, prop.name).let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingUser, prop.get(updateRequest))
                }
            }
        }

        val savedUser: User = repository.save(existingUser)
        return convertEntityToDto(savedUser)
    }

    fun deleteUser(id: Long): String {
        checkForUserId(id)
        repository.deleteById(id)
        return "User with id: $id has been deleted."
    }
}