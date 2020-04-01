package anon_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class AnonTest {
	private static final int MAX_SIZE = 10;
	
	private Anonymous queue;
	private Random rng = new Random();
	
	@BeforeEach
	void setUp() {
		queue = new Anonymous();
	}
	
	@Test
	void enqueueDequeueTest() {
		float[] controlValues = new float[MAX_SIZE];
		
		for (int i = 0; i < MAX_SIZE; i++) {
			float tmp = rng.nextFloat();
			
			controlValues[i] = tmp;
			queue.en(tmp);
		}
		
		for (int i = 0; i < controlValues.length; i++) {
			Assertions.assertEquals(controlValues[i], queue.de());
		}
	}
	
	@Test
	void firstValueOverwrittenTest() {
		float controlValue = 3.14f;
		
		queue.en(controlValue);
		
		for (int i = 0; i < MAX_SIZE; i++) {
			float tmp = rng.nextFloat();
			queue.en(tmp);
		}
		
		Assertions.assertNotEquals(controlValue, queue.de());
	}
	
	//Tests the post contract for the method
	@Test
	void incModuloTest() {
		int valuesToTest = MAX_SIZE * 100;
		
		for (int i = 0; i < valuesToTest; i++) {
			Assertions.assertEquals(
					(i + 1) % MAX_SIZE,
					queue.inc(i));
		}
	}

	@Test
	void emptyQueueTest() {
		Assertions.assertTrue(queue.isEmpty());
	}
	
	@Test
	void nonEmptyQueueTest() {
		float enqueValue = 3.14f;
		
		queue.en(enqueValue);
		
		Assertions.assertFalse(queue.isEmpty());
	}
	
	@Test
	void fullQueue() {
		for (int i = 0; i < MAX_SIZE; i++) {
			queue.en(rng.nextFloat());
		}
		
		Assertions.assertTrue(queue.isFull());
	}
	
	@Test
	void nonFullQueue() {
		int amountOfValues = MAX_SIZE - 1;
		
		for (int i = 0; i < amountOfValues; i++) {
			queue.en(rng.nextFloat());
		}
		
		Assertions.assertFalse(queue.isFull());
	}

}
