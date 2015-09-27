package io.tintoy.actors

import akka.actor.Actor
import akka.actor.Props

/**
 * The actor that serves as the main application entry-point.
 * @note Triggers the greeter.
 * @see Greeter
 */
class HelloWorld extends Actor {
	/**
	 * Called during actor start-up.
	 */
	override def preStart(): Unit = {
		// Create our greeter.
		val greeter = context.actorOf(
			Props[Greeter],
			name = "Greeter"
		)

		greeter ! Greeter.Greet
	}

	/**
	 * Called when the actor receives a message.
	 * @return Nothing
	 */
	def receive = {
		case Greeter.Done =>
			context.stop(self)
	}
}
