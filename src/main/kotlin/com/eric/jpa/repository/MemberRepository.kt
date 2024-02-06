package com.eric.jpa.repository

import com.eric.jpa.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository: JpaRepository<Member, UUID>