package com.github.jeroenr.hexagonal.config

import com.mongodb.reactivestreams.client.MongoClients
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.data.mongodb.core.ReactiveMongoTemplate

open class TestMongoConfig {

    private val mongodExecutable: MongodExecutable? = null
    private var mongoTemplate: ReactiveMongoTemplate? = null

    @BeforeEach
    fun setMongoTemplate() {
        val starter = MongodStarter.getDefaultInstance()
        val port: Int = Network.getFreeServerPort()
        val ip = "localhost"
        val mongodConfig = ImmutableMongodConfig.builder().version(Version.Main.PRODUCTION)
            .net(Net(ip, port, Network.localhostIsIPv6()))
            .build()
        starter.prepare(mongodConfig).start()
        mongoTemplate = ReactiveMongoTemplate(MongoClients.create("mongodb://$ip:$port"), "OFMS")
    }

    @AfterEach
    fun clean() {
        mongodExecutable!!.stop()
    }
}
