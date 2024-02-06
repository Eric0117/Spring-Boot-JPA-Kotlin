package com.eric.jpa.service

import com.eric.jpa.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
) {
}