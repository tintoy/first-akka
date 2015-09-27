package io.tintoy.actors

import akka.actor.Actor

/**
 * Messages used by the Greet actor.
 */
object Greeter {
	/**
	 * Request a greeting from the greeter.
	 */
	case object Greet

	/**
	 * Notification from the greeter that it has finished its greeting.
	 */
	case object Done
}

/**
 * Actor that sends a greeting to its caller.
 */
class Greeter extends Actor {
	/**
	 * Called when the actor receives a message.
	 * @return Nothing
	 */
	def receive = {
		case Greeter.Greet =>
			println("Hello world")
			sender() ! Greeter.Done
	}
}
