package Lecture25.abstracts;

import Lecture14.LinkedList;

public final class StackUsingLL extends AStack {
private LinkedList list;
	
	public StackUsingLL() {
		this.list = new LinkedList();
	}

	public void push(int item) {
		this.list.addFirst(item);
	}

	public int pop() throws Exception {
		int rv = 0;

		try {
			rv = this.list.removeFirst();
		} catch (Exception ex) {
			throw new Exception("Stack is empty");
		}
		return rv;
	}

	public int top() throws Exception {
		int rv = 0;

		try {
			rv = this.list.getFirst();
		} catch (Exception ex) {
			throw new Exception("Stack is empty");
		}

		return rv;
	}

	public int size() {
		return this.list.size();
	}

	public void display() {
		this.list.display();
	}

}
