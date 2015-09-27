package io.tintoy

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Terminated

import io.tintoy.actors.{Terminator, HelloWorld}

/**
 * My first native (Scala-based) Akka application.
 */
object FirstAkkaApp extends App {
	val system = ActorSystem.create("FirstAkkaApp")
	var helloWorld = system.actorOf(
		Props[HelloWorld],
		name = "HelloWorld"
	)

	// Shut down the system when the HelloWorld actor stops.
	system.actorOf(
		Props(
			classOf[Terminator],
			helloWorld
		),
		"Terminator"
	)
}

