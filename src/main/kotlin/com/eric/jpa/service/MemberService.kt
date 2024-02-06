package com.eric.jpa.service

import com.eric.jpa.domain.Member
import com.eric.jpa.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun create(): Member {
        val mem = Member(
            username = "Bennie Evans"
        )
        return memberRepository.save(mem)
    }
}