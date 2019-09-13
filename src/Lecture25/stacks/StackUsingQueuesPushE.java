package Lecture25.stacks;

import java.util.Queue;

import Lecture12A.queuesStory.DynamicQueue;

public class StackUsingQueuesPushE {
	private DynamicQueue queuePrimary;
	private DynamicQueue queueSecondary;

	public StackUsingQueuesPushE() {
		this.queuePrimary = new DynamicQueue();
		this.queueSecondary = new DynamicQueue();
	}

	public void push(int item) {
		this.queuePrimary.enqueue(item);
	}

	public int pop() throws Exception {
		int rv = 0;

		try {
			while (this.queuePrimary.size() != 1) {
				this.queueSecondary.enqueue(this.queuePrimary.dequeue());
			}

			rv = this.queuePrimary.dequeue();

			DynamicQueue temp = this.queuePrimary;
			this.queuePrimary = this.queueSecondary;
			this.queueSecondary = temp;
		} catch (Exception ex) {
			throw new Exception("Stack is empty");
		}
		return rv;
	}

	public int top() throws Exception {
		int rv = 0;

		try {
			while (this.queuePrimary.size() != 1) {
				this.queueSecondary.enqueue(this.queuePrimary.dequeue());
			}

			rv = this.queuePrimary.dequeue();
			this.queueSecondary.enqueue(rv);

			DynamicQueue temp = this.queuePrimary;
			this.queuePrimary = this.queueSecondary;
			this.queueSecondary = temp;
		} catch (Exception ex) {
			throw new Exception("Stack is empty");
		}

		return rv;
	}

	public int size() {
		return this.queuePrimary.size();
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void display() throws Exception {
		this.displayHelper();
		System.out.println("END");

		DynamicQueue temp = this.queuePrimary;
		this.queuePrimary = this.queueSecondary;
		this.queueSecondary = temp;
	}

	private void displayHelper() throws Exception {
		if (this.queuePrimary.isEmpty()) {
			return;
		}

		int item = this.queuePrimary.dequeue();
		this.queueSecondary.enqueue(item);

		this.displayHelper();

		System.out.print(item + " => ");
	}
}
