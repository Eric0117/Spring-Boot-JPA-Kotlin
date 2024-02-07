package com.eric.jpa.repository

import com.eric.jpa.domain.Company
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CompanyRepository: JpaRepository<Company, UUID>