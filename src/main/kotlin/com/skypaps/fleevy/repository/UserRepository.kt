package com.skypaps.fleevy.repository

import com.skypaps.fleevy.data.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findUserById(id: Long): User

    //fun doesEmailExist(email : String) : Boolean

    /*@Query(value = "SELECT * FROM task WHERE is_task_open = TRUE", nativeQuery = true)
    fun queryAllOpenTasks(): List<Task>*/
}