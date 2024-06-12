package fp;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow;

/**
 * Simple demo of the Java 9 Reactive "Flow" API for pub-sub communication.
 */
public class FlowDemo {

    public static void main(String[] args) throws InterruptedException {

        // This is a Flow.Publisher implementation that handles submission/
		// forwarding, and has a close() method.
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

		// Our implementation of the Subscriber interface
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(getNumBuffers());
            }

            @Override
            public void onNext(String item) {
                System.out.println("Received: " + item);
                subscription.request(getNumBuffers());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };
        publisher.subscribe(subscriber);
		for (String mesg : messages) {
			System.out.println("Sending: " + mesg);
			publisher.submit(mesg);
		}
        publisher.close();
        Thread.sleep(1000);
    }

	/**
	 * Compute the number of buffers we want to receive, to
	 * manage "back pressure" - so we don't get sent more than
	 * we can handle.
	 * A placeholder for now - "1" will always work.
	 */
	static int getNumBuffers() {
		return 1;
	}

	static String[] messages = {
		"Hello, world of Java!",
		"Hope you're having a good day!",
		"Things are really hopping now",
		"The clock is ticking.",
		"Day is done. Goodbye!",
	};
}

