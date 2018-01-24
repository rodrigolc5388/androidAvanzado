package com.rodrigolc.madridshops.domain.model

// Interface segregation
// SOLID

interface ReadAgregate<T> {
    fun count(): Int
    fun all(): List<T>
    fun get(position: Int): T
}

interface WriteAgregate<T> {
    fun add(element: T)
    fun delete(position: Int)
    fun delete(element: T)
}

interface Aggregate<T>: ReadAgregate<T>, WriteAgregate<T>