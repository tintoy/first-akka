package io.tintoy

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Terminated

import io.tintoy.actors.HelloWorld

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

class Terminator(ref: ActorRef) extends Actor with ActorLogging {
	context watch ref

	def receive = {
		case Terminated(_) =>
			log.info("{} has terminated, shutting down system...", ref.path)
			context.system.shutdown()
	}
}
