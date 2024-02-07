package com.eric.jpa

import com.eric.jpa.domain.Member
import com.eric.jpa.domain.QMember
import com.eric.jpa.repository.MemberRepository
import com.querydsl.jpa.impl.JPAQuery
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
class QueryDslTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    //https://beaniejoy.tistory.com/84

    @BeforeEach
    fun beforeEach() {
        val members = listOf(
            Member(username = "Bennie Evans"),
            Member(username = "Edwardo Goff"),
            Member(username = "Lynn Evans")
        )

        memberRepository.saveAll(members)
    }

    @Test
    fun find_test() {
        val query: JPAQuery<Member> = JPAQuery<Member>(entityManager)
        val qMember = QMember("m")

        val memberList: List<Member> = query.from(qMember)
            .where(qMember.username.like("%Evans%"))
            .fetch()

        assertEquals(memberList.size, 2)
    }
}