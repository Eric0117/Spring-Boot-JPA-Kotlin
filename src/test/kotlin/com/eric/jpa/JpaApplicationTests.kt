package com.eric.jpa

import com.eric.jpa.domain.Member
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = RANDOM_PORT)
class JpaApplicationTests(
    @LocalServerPort private val port: Int,
) {
    private val restTemplate: RestTemplate = RestTemplateBuilder().rootUri("http://localhost:$port").build()

    @Test
    fun test_create_member() {
        // Given
        val request = Member(
            username = "Bennie Evans"
        )

        // When
        val actual = restTemplate.postForEntity("/member", request, Member::class.java)

        // Then
        assertEquals(HttpStatus.OK, actual.statusCode)
        assertEquals("Bennie Evans", actual.body?.username)
    }

}
