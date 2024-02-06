package com.eric.jpa.repository

import com.eric.jpa.domain.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository: JpaRepository<Post, UUID>