## Assignment2 - Queues

> 作业网站：[Assignment2-Queues](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php)，包含本次作业的说明，FAQ，相关的有用资源文件。

[TOC]

### Deque 

> *Performance requirements.*  Your deque implementation must support **each deque operation (including construction) in *constant worst-case time***. A deque containing *n* items must use at most 48*n* + 192 bytes of memory. Additionally, your **iterator implementation must support each operation (including construction) in *constant worst-case time***.

对于 deque 的每个操作都要求 $O(1)$ 的时间复杂度。如果才用 resizing-array 实现，那么在需要 `resize` 操作时，显然操作的时间复杂度不满足要求，故应采用 linked-list 实现。

代码如下：

```java
package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * Performance requirements.  
 * 
 * Your deque implementation must support each deque operation 
 * (including construction) in constant worst-case time. 
 * 
 * < This means that only can choose linked-list implementation
 * < Since if use resizing-array implementation, sometimes we need to resize 
 * < the array, some ops like add and remove may not cost constant worst-case time.
 * 
 * A deque containing n items must use at most 48n + 192 bytes of memory. 
 * Additionally, your iterator implementation must support each operation 
 * (including construction) in constant worst-case time.
 *
 * < 48n + 192 bytes and hasNext(), remove(), next() all cost constant worst-case time.
 * < use 48n + 16 + 16 + 4 + 24 + 8 + 4(pad) = 48n + 72 bytes
 */


public class Deque<Item> implements Iterable<Item> {
	// 16(overhead) bytes
	
	private Node first, last;	// 8(ref) + 8(ref) = 16 bytes
	private int n;				// 4(int) bytes
	
	private class Node {
		// 16(overhead) + 8(inner class) = 24 bytes
		Item item;				// 8(ref) bytes
		Node prev;				// 8(ref) bytes
		Node next;				// 8(ref) bytes
		// 24 + 8 + 8 + 8 = 48 bytes -> 48n bytes for n items
	}							
	
	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		n = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return n == 0;
	}
	
	// return the number of items on the deque
	public int size() {
		return n;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) throw new IllegalArgumentException();
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		first.prev = null;
		if (!isEmpty()) {
			oldFirst.prev = first;
		} 
		else {
			last = first;
		}
		n++;
	}
	
	// add the item to the back
	public void addLast(Item item) {
		if (item == null) throw new IllegalArgumentException();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.prev = oldLast;
		if (!isEmpty()) {
			oldLast.next = last;
		}
		else {
			first = last;
		}
		n++;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		n--;
		if (n == 0) last = first;
		else first.prev = null;
		return item;
	}
	
	// remove and return the item from the back
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = last.item;
		last = last.prev;
		n--;
		if (n == 0) first = last;
		else last.next = null;
		return item;
	}
	
	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	
	private class ListIterator implements Iterator<Item> {
		// 16(overhead) + 8(inner class) = 24 bytes
		
		private Node current = first;	// 8(ref) bytes
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	// unit testing (required)
	public static void main(String[] args) {
		/**
		 * Unit testing
		 * 
		 * Your main() method must call directly every public constructor 
		 * and method to help verify that they work as prescribed  
		 * (e.g., by printing results to standard output).
		 */
		
		Deque<Integer> deque = new Deque<Integer>();
		
		for (int i = 0; i < 10; i += 2) {
			deque.addFirst(i);
			deque.addLast(i + 1);
		}
		Iterator<Integer> it = deque.iterator();
		while (it.hasNext()) {
			StdOut.print(it.next() + " ");
		}
		StdOut.println("size: " + deque.size());
		
		for (int i = 0; i < 3; i++) {
			deque.removeLast();
			deque.removeFirst();
		}
		it = deque.iterator();
		while (it.hasNext()) {
			StdOut.print(it.next() + " ");
		}
		StdOut.println("size: " + deque.size());
		
		for (int i = 0; i < 6; i += 2) {
			deque.addLast(i);
			deque.addFirst(i + 1);
		}
		it = deque.iterator();
		while (it.hasNext()) {
			StdOut.print(it.next() + " ");
		}
		StdOut.println("size: " + deque.size());
		
		for (int i = 0; i < 10; i++) {
			deque.removeFirst();
		}
		for (int i = 0; i < 5; i++) {
			deque.addLast(i);
		}
		for (int a : deque) {
			for (int b : deque) {
				StdOut.print(a + "-" + b + " ");
			}
			StdOut.println();
		}
		
	}	
}

```



### RandomizedQueue

> *Performance requirements.*  Your randomized queue implementation must support **each randomized queue operation (besides creating an iterator) in *constant <u>amortized</u> time*.** That is, any intermixed sequence of *m* randomized queue operations (starting from an empty queue) must take at most *cm* steps in the worst case, for some constant *c*. A randomized queue containing *n* items must use at most 48*n* + 192 bytes of memory. Additionally, your **iterator implementation must support operations `next()` and `hasNext()` in *constant worst-case time***; and **construction in *linear time***; you may (and will need to) use **a linear amount of extra memory per iterator**.

同样关注 Performance requirements，其中提到的 amortized 关键词已经一定程度上暗示我们是使用 resizing-array 实现。如果采用 linked-list 实现，那么在实现 randomized 时必然会出现这样的情况：随机选中的节点与我们的开始节点（不论在哪也不论有几个）有一定距离，要访问随机选中的节点我们无可避免的需要在链表中进行小范围的遍历，在最坏情况下，均摊成本并不会是 constant 的。

实际上，这个问题中更容易出错的是 `Iterator` 的实现，更具体地，**确保 `Iterator` 的独立性**：

> The order of two or more iterators to the same randomized queue must be *mutually independent*; each iterator must maintain its own random order.

也即，**我们必须要确保我们创建的 `iterator` 之间是独立的，它们虽然都针对于同一个 `RandomizedQueue`，但是在并发运行时并不会彼此影响。**

我最初的实现就犯了这个错误，如下：

```java
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		/**
		 * Iterator.  Each iterator must return the items in uniformly random order. 
		 * The order of two or more iterators to the same randomized queue must be 
		 * mutually independent; each iterator must maintain its own random order.
		 */
		return new RandomIterator();
	}

	private class RandomIterator implements Iterator<Item> {
		
		private int i;
		private int count;
		
		public RandomIterator() {
			if (tail < head) resize(rq.length);
      if (tail == 0) StdRandom.shuffle(rq, head, rq.length);
			else StdRandom.shuffle(rq, head, tail);
			i = head;
			count = n;
		}
		
		public boolean hasNext() {
			return count > 0;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			count--;
			return rq[(i++) % rq.length];
		}
		
	}
```

注意到，每次要对 `RandomizedQueue` 执行遍历，我先对可变数组进行修正，确保 `head=0`（包含在 `resize`）中，然后对可变数组中的非空部分进行 `StdRandom.shuffle`（其实就是 Knuth-Shuffle，可参考[有哪些算法惊艳到了你？ - 刘宇波的回答 - 知乎](https://www.zhihu.com/question/26934313/answer/743798587)），打乱以后，在遍历时再返回的元素就是随机的。

看起来很好，但在多个 `iterator` 并发同时执行的时候就会出现问题。可以参考 [Q&A](https://coursera.cs.princeton.edu/algs4/assignments/queues/faq.php) 中给出的 `iterator` 的并发运行代码：

```java
int n = 5;
RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
for (int i = 0; i < n; i++)
    queue.enqueue(i);
for (int a : queue) {
    for (int b : queue)
        StdOut.print(a + "-" + b + " ");
    StdOut.println();
}
```

**在外层遍历的时候（记其为 a-遍历），我们对 `RandomizedQueue` 进行了 shuffle，而后在内层遍历的时候（记其为 b-遍历），还未等 a-遍历遍历完，我们又重新对 `RandomizedQueue` 进行了 shuffle。**

所以上面这段代码的结果将会是，b-遍历正确输出运行（比如第一行：2,0,3,4,1），但是 a-遍历会输出一些乱七八糟的东西（0,0,0,2,3），有些本应该在 `RandomizedQueue` 中的元素竟然都没输出：

```
0-2 0-0 0-3 0-4 0-1 
0-3 0-1 0-2 0-4 0-0 
2-4 2-1 2-0 2-3 2-2 
3-3 3-2 3-1 3-4 3-0 
0-1 0-2 0-3 0-0 0-4 
```

容易想到也许我们可以另外创建一个 Item 的数组，把可变数组中的元素都拷贝进来，对这个数组进行 shuffle。这的确解决了问题，现在每次要进行遍历，我们都会额外先创建一个数组，这样的话即使多个 `iterator` 同时执行，那么 shuffle 操作也仅针对各自的新创建的数组。

但吹毛求疵的话，**更好的方法应该是创建一个随机下标数组**，这个数组中保存了已经打乱了的 1~n 下标。这样的方法占用更少的空间。如下：

```java
// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		/**
		 * Iterator.  Each iterator must return the items in uniformly random order. 
		 * The order of two or more iterators to the same randomized queue must be 
		 * mutually independent; each iterator must maintain its own random order.
		 */
		return new RandomIterator();
	}
	
	private class RandomIterator implements Iterator<Item> {
		
		private int[] randomIdxs;
		private int i;
		
		public RandomIterator() {
			
			randomIdxs = new int[n];
			for (int j = 0; j < n; j++) {
				randomIdxs[j] = j;
			}
			StdRandom.shuffle(randomIdxs);

			i = 0;
		}
		
		public boolean hasNext() {
			return i != randomIdxs.length;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return rq[(head + randomIdxs[i++]) % rq.length];
		}
		
	}
```

`RandomizedQueue` 的完整代码如下：

```java
package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * 
 * Performance requirements.  
 * 
 * Your randomized queue implementation must support each randomized queue 
 * operation (besides creating an iterator) in constant amortized time. 
 * That is, any intermixed sequence of m randomized queue operations 
 * (starting from an empty queue) must take at most cm steps in the worst case, 
 * for some constant c. 
 * 
 * < iterator's construction cost time! keyword "amortized" implies that 
 * < we may use resizing-array implementation better. and use linked-list
 * < implementation for randomized may cost linear time: we have to traverse 
 * < from the our begining node in the list.
 * 
 * A randomized queue containing n items must use at most 
 * 48n + 192 bytes of memory. 
 * 
 * Additionally, your iterator implementation must support operations next() 
 * and hasNext() in constant worst-case time; and construction in linear time; 
 * you may (and will need to) use a linear amount of extra memory per iterator.
 * 
 * < iterator's construction take linear time! and may need O(n) extra memory
 * 
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] rq;	
	private int head;
	private int tail;
	private int n;
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		rq = (Item[]) new Object[1];
		head = 0;
		tail = 0;
		n = 0;
	}
	
	// is the randomized queue empty?
	public boolean isEmpty() {
		return n == 0;
	}
	
	// return the number of items on the randomized queue
	public int size() {
		return n;
	}
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			copy[i] = rq[(head + i) % rq.length];
		}
		rq = copy;
		head = 0;
		tail = n;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new IllegalArgumentException();
		if (n == rq.length) resize(2 * rq.length);
		rq[tail] = item;
		tail = (tail + 1) % rq.length;
		n++;
	}
	
	private int getRandomIdx() {
		return (head + StdRandom.uniform(n)) % rq.length;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		int randomIdx = getRandomIdx();
		Item item = rq[randomIdx];
		rq[randomIdx] = rq[head];
		rq[head] = null;
		head = (head + 1) % rq.length;
		n--;
		if (n > 0 && n == rq.length/4) resize(rq.length/2);
		return item;
	}
	
	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException();
		return rq[getRandomIdx()];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		/**
		 * Iterator.  Each iterator must return the items in uniformly random order. 
		 * The order of two or more iterators to the same randomized queue must be 
		 * mutually independent; each iterator must maintain its own random order.
		 */
		return new RandomIterator();
	}
	
	private class RandomIterator implements Iterator<Item> {
		
		private int[] randomIdxs;
		private int i;
		
		public RandomIterator() {
			
			randomIdxs = new int[n];
			for (int j = 0; j < n; j++) {
				randomIdxs[j] = j;
			}
			StdRandom.shuffle(randomIdxs);

			i = 0;
		}
		
		public boolean hasNext() {
			return i != randomIdxs.length;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return rq[(head + randomIdxs[i++]) % rq.length];
		}
		
	}
	
	// unit testing (required)
	public static void main(String[] args) {
		/**
		 * Unit testing.  
		 * 
		 * Your main() method must call directly every public 
		 * constructor and method to verify that they work as prescribed 
		 * (e.g., by printing results to standard output).
		 */
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		for (int i = 0; i < 5; i++) {
			rq.enqueue(i);
		}
		Iterator<Integer> it = rq.iterator();
		while (it.hasNext()) StdOut.print(it.next() + " ");
		StdOut.println(" size: " + rq.size());
		
		it = rq.iterator();
		while (it.hasNext()) StdOut.print(it.next() + " ");
		StdOut.println(" size: " + rq.size());
		
		for (int a : rq) {
			for (int b : rq) {
				StdOut.print(a + "-" + b + " ");
			}
			StdOut.println();
		}
		
		for (int i = 0; i < 5; i++) {
			StdOut.println("Dequeue: " + rq.dequeue());
			
			if (i < 3) {
				it = rq.iterator();
				while (it.hasNext()) StdOut.print(it.next() + " ");
				StdOut.println(" size: " + rq.size());
			}
		}
		
		StdOut.println("Queue empty? " + rq.isEmpty());
		
		for (int i = 0; i < 5; i++) {
			rq.enqueue(i);
		}
		
		int[] count = new int[5];
		
		for (int i = 0; i < 10000; i++) {
			count[rq.sample()]++;
		}
		
		for (int i = 0; i < 5; i++) {
			StdOut.print(count[i] + " ");
		}
		
	}
	
}

```

实际上，上面我的 `RamdomizedQueue` 实现还是搞复杂了一点，因为最开始我是按着 Queue 去做的：`enqueue` 时在 `last` 处加入，`dequeue` 时随机挑选 `head~last` 中的某处弹出，但却忘了很关键的一点：**其实 Stack 和 Queue 的外在区别仅仅是弹出的元素不同而已**。我们完全也可以按着 Stack 去做：`enqueue` 时在 `top` 压入元素，`dequeue` 时随机挑选 `0~top` 中的某处弹出，可参考他人实现：[RandomizedQueue.java](https://github.com/Maecenas/Algorithms-algs4-cos226-Princeton-Coursera/blob/master/src/main/java/assignment2/RandomizedQueue.java)  by Maecenas。

### Permutation

> *Performance requirements.*  The running time of `Permutation` must be linear in the size of the input. You may use only a constant amount of memory plus either one `Deque` or `RandomizedQueue` object of maximum size at most *n*. (For an **<u>extra challenge</u>** and a small amount of extra credit, **use only one `Deque` or `RandomizedQueue` object of maximum size at most *k***.)

题目要求，输入一定数量的字符串，给定 k，要求返回输入字符串的一个 k-排列。

问题很容易转化为从输入的所有字符串中不放回地随机抽取 k 个字符串，所以最简单地，把输入中所有字符串 `enqueue` 到一个 `RandomizedQueue` 对象中去，然后 `dequeue` k 次即可。

**本题唯一的难点在于最后面的 extra challenge：仅使用最多包含 k 个元素的 `RandomizedQueue` 完成本题。**如果有 10000 个字符串输入，给定 `k=5`，我们如何使用最大空间为 5 的 `RandomizedQueue` 完成本题？在这样的空间限制下，我们不再能把 10000 个字符串全 `enqueue` 进去再 `dequeue`，也许我们应该在适当的时候 `dequeue`，但接踵而来的问题是怎样我们才能确保在一系列 `enqueue` 和 `dequeue` 操作后，留在 `RandomizedQueue` 中的最后 5 个元素确实符合随机的定义（每个字符串都有恒等的可能最后保留在 `RandomizedQueue` 中）呢？

其实这是典型的可以用**蓄水池抽样算法**解决的问题：从包含 n 个项目的集合 S 中选取 k 个样本，其中 n 为很大（典型地，不可能全放到主存）或未知的数。关于蓄水池抽样算法，这里不多做赘述，[水塘抽样（Reservoir Sampling） - PENG的文章 - 知乎](https://zhuanlan.zhihu.com/p/29178293) 以及 [水塘抽样 - 维基百科](https://zh.wikipedia.org/wiki/%E6%B0%B4%E5%A1%98%E6%8A%BD%E6%A8%A3) 中已经讲得非常清楚了。

代码如下：

```java
package queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 
 * Performance requirements.  
 * 
 * The running time of Permutation must be linear in the size of the input. 
 * You may use only a constant amount of memory plus either one Deque or 
 * RandomizedQueue object of maximum size at most n. 
 * 
 * (For an extra challenge and a small amount of extra credit, use only 
 * one Deque or RandomizedQueue object of maximum size at most k.)
 *
 */

public class Permutation {
	/**
	 * Command-line argument.  
	 * 
	 * You may assume that 0 ≤ k ≤ n, where n is the number of string on 
	 * standard input. Note that you are not given n.
	 */
	public static void main(String[] args) {
		
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		
		if (k == 0) return;
		
		
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String s = StdIn.readString();
			if (i < k) rq.enqueue(s);
			else {
				int r = StdRandom.uniform(i + 1);
				if (r < k) {
					rq.dequeue();
					rq.enqueue(s);
				}
			}
		}
		while (!rq.isEmpty()) {
			StdOut.println(rq.dequeue());
		}
	}
}

```



