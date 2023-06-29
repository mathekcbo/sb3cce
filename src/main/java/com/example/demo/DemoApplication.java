package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	static final int NUM_OF_THREADS = 4;

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		final Thread[] threads = new Thread[NUM_OF_THREADS];
		for (int i = 0; i < NUM_OF_THREADS; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					final ASingletonBean singletonBean = context.getBean(ASingletonBean.class);
					singletonBean.doIt();
				}
			});
		}

		for (int i = 0; i < NUM_OF_THREADS; i++) {
			threads[i].start();
		}

		for (int i = 0; i < NUM_OF_THREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
