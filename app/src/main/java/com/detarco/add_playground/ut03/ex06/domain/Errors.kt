package com.detarco.add_playground.ut03.ex06.domain

sealed class Failure : Throwable() {
    object DataError : Failure()
    object NetworkConnectionError : Failure()
    object ServerError : Failure()

    object FileError : Failure()
    object XmlError : Failure()
    object DbError : Failure()
    object DomainError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}