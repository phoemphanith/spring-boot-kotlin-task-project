package com.example.restfulapitasks.services

import com.example.restfulapitasks.model.Task
import com.example.restfulapitasks.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TaskService(private val service: TaskRepository) {
    fun getTasks(): List<Task> = service.findAll().toList()
    fun addTask(task: Task): ResponseEntity<Task> = ResponseEntity.ok(service.save(task))
    fun getTaskById(id: Long): ResponseEntity<Task> = service.findById(id).map{
        task -> ResponseEntity.ok(task)
    }.orElse(ResponseEntity.notFound().build())
    fun updateTask(id: Long, task: Task): ResponseEntity<Task> = service.findById(id).map{
        currentTask ->
        val updateTask: Task = currentTask.copy(
            title = task.title,
            description = task.description,
            status = task.status,
            startDate = task.startDate,
            priority = task.priority,
            dueDate = task.dueDate
        )
        ResponseEntity.ok().body(service.save(updateTask))
    }.orElse(ResponseEntity.notFound().build())
    fun deleteTask(id: Long): ResponseEntity<Void> = service.findById(id).map {
        task ->
        service.delete(task)
        ResponseEntity<Void>(HttpStatus.ACCEPTED)
    }.orElse(ResponseEntity.notFound().build())
}