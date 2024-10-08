package org.example

import kotlin.test.Test

class CreateMongoLibraryTest {
    @Test
    fun createMongoLibrary_throwsNoException() {
        assert(runCatching { createMongoClient() }.isSuccess)
    }
}
