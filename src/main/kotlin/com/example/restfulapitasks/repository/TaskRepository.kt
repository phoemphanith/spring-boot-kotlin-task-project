package com.example.restfulapitasks.repository

import com.example.restfulapitasks.model.Task
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface TaskRepository: JpaRepository<Task, Long>