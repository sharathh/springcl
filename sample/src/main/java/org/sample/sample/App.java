package org.sample.sample;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// helloJava7("ab","cd");
		// exampleJava8();
		// ex2Java8();
		//ex3Java8();
		ex4Java8();
	}

	public static void helloJava7(String... names) {
		Observable.from(names).subscribe(new Action1<String>() {

			public void call(String s) {
				System.out.println("Hello " + s + "!");
			}

		}, new Action1<Throwable>() {
			public void call(Throwable error) {
				System.out.println("Error in synchronous observable");
			}
		}, new Action0() {
			public void call() {
				System.out.println("This observable is finished");
			}

		});
	}

	// Creating an Observable from an Existing Data Structure
	public static void exampleJava8() {
		// In Java 8
		Integer[] numbers = { 0, 1, 2, 3, 4, 5 };

		Observable numberObservable = Observable.from(numbers);

		numberObservable.subscribe((incomingNumber) -> System.out.println("incomingNumber " + incomingNumber),
				(error) -> System.out.println("Something went wrong" + ((Throwable) error).getMessage()),
				() -> System.out.println("This observable is finished"));
	}

	// Creating an Observable via the create() method
	public static void ex2Java8() {
		Observable.OnSubscribe<String> subscribeFunction = (s) -> {
			Subscriber subscriber = (Subscriber) s;

			for (int ii = 0; ii < 10; ii++) {
				if (!subscriber.isUnsubscribed()) {
					subscriber.onNext("Pushed value " + ii);
				}
			}

			if (!subscriber.isUnsubscribed()) {
				subscriber.onCompleted();
			}
		};

		Observable createdObservable = Observable.create(subscribeFunction);

		createdObservable.subscribe((incomingValue) -> System.out.println("incomingValue " + incomingValue),
				(error) -> System.out.println("Something went wrong" + ((Throwable) error).getMessage()),
				() -> System.out.println("This observable is finished"));
	}

	// A Filtered Asynchronous Observable

	public static void ex3Java8() {
		Observable.OnSubscribe<String> subscribeFunction = (s) -> asyncProcessingOnSubscribe(s);

		Observable asyncObservable = Observable.create(subscribeFunction);

		asyncObservable.skip(5).subscribe((incomingValue) -> System.out.println(incomingValue));

	}

	private static void asyncProcessingOnSubscribe(Subscriber s) {
		final Subscriber subscriber = (Subscriber) s;
		Thread thread = new Thread(() -> produceSomeValues(subscriber));
		thread.start();
	}

	private static void produceSomeValues(Subscriber subscriber) {
		for (int ii = 0; ii < 10; ii++) {
			if (!subscriber.isUnsubscribed()) {
				subscriber.onNext("Pushing value from async thread " + ii);
			}
		}
	}

	// Processing halted when error encountered

	public static void ex4Java8() {
		Observable.OnSubscribe<String> subscribeFunction = (s) -> produceValuesAndAnError(s);

		Observable createdObservable = Observable.create(subscribeFunction);

		createdObservable.subscribe((incomingValue) -> System.out.println("incoming " + incomingValue),
				(error) -> System.out.println("Something went wrong " + ((Throwable) error).getMessage()),
				() -> System.out.println("This observable is finished"));

	}

	private static void produceValuesAndAnError(Subscriber s) {
		Subscriber subscriber = (Subscriber) s;

		try {
			for (int ii = 0; ii < 50; ii++) {
				if (!subscriber.isUnsubscribed()) {
					subscriber.onNext("Pushed value " + ii);
				}

				if (ii == 5) {
					throw new Throwable("Something has gone wrong here");
				}
			}

			if (!subscriber.isUnsubscribed()) {
				subscriber.onCompleted();
			}
		} catch (Throwable throwable) {
			subscriber.onError(throwable);
		}
	}

}
