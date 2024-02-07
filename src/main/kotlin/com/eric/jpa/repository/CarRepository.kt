package com.eric.jpa.repository

import com.eric.jpa.domain.Car
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarRepository: JpaRepository<Car, UUID>