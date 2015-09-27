package io.tintoy.actors

import akka.actor.Actor
import akka.actor.Props

class HelloWorld extends Actor {
	override def preStart(): Unit = {
		val greeter = context.actorOf(
			Props[Greeter],
			name = "Greeter"
		)

		greeter ! Greeter.Greet
	}

	def receive = {
		case Greeter.Done =>
			context.stop(self)
	}
}
