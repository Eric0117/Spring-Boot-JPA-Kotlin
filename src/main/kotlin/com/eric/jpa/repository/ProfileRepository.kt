package com.eric.jpa.repository

import com.eric.jpa.domain.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProfileRepository: JpaRepository<Profile, UUID>