package io.tintoy.actors

import akka.actor._

/**
 * Actor used to shut down the system when another actor is terminated.
 * @param triggerActor The actor whose termination should trigger system shutdown.
 */
class Terminator(triggerActor: ActorRef) extends Actor with ActorLogging {
	context.watch(triggerActor)

	/**
	 * Called when the actor receives a message.
	 * @return Nothing
	 */
	def receive = {
		case Terminated(_) =>
			log.info("{} has terminated, shutting down system...", triggerActor.path)
			context.system.shutdown()
	}
}
