package anon_test;

public class Anonymous {
	final private int MAXSIZE = 10;
	private int last;
	private int first;
	private int size;
	private float[] storage;

	public Anonymous() {
		storage = new float[MAXSIZE];
		first = last = size = 0;
	}

	public void en(float v) {
		storage[last] = v;
		last = inc(last);
		size++;
	}

	public float de() {
		float tmp = storage[first];
		first = inc(first);
		size--;
		return tmp;
	}

	public int inc(int aPos) {
		return ++aPos % MAXSIZE;
	}

	public boolean isFull() {
		return size == MAXSIZE;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}