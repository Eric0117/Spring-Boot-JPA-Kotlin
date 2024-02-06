package com.eric.jpa.controller

import com.eric.jpa.domain.Member
import com.eric.jpa.service.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService,
) {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun getMember(): Member {
        return memberService.create()
    }

}