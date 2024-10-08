package org.example

import com.mongodb.kotlin.client.coroutine.MongoClient

fun createMongoClient(): MongoClient = MongoClient.create("mongodb://localhost:27017")
