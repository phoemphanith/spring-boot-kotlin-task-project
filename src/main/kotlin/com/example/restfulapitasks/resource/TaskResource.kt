package com.example.restfulapitasks.resource

import com.example.restfulapitasks.model.Task
import com.example.restfulapitasks.services.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/tasks")
class TaskResource(private val service: TaskService) {
    @GetMapping
    fun index(): List<Task> = service.getTasks()
    @PostMapping
    fun store(@Validated @RequestBody task: Task): ResponseEntity<Task> = service.addTask(task)
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): ResponseEntity<Task> = service.getTaskById(id)
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Validated @RequestBody task: Task): ResponseEntity<Task> = service.updateTask(id, task)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> = service.deleteTask(id)
}