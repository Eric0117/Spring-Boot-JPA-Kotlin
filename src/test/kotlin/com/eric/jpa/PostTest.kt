package com.eric.jpa

import com.eric.jpa.domain.Member
import com.eric.jpa.domain.Post
import com.eric.jpa.repository.MemberRepository
import com.eric.jpa.repository.PostRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
class PostTest {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var postRepository: PostRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager


    @Test
    fun create_post() {
        // Given
        entityManager.clear()
        val user = memberRepository.save(Member(username = "Bennie Evans"))

        // When
        val actual = postRepository.save(Post(title = "epicuri", content = "graeco", writer = user))

        // Then
        assertEquals("epicuri", actual.title)
    }

}