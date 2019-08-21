## Stacks And Queues

### Interview Questions: Stacks and Queues (ungraded)

#### **1. Queue with two stacks.** 

Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.

*Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.*

**Solution**

思路很简单，用到**栈逆序输出其中元素的特性**，一个栈 `inStack` 用作 `enqueue`，一个栈 `outStack` 用作 `dequeue`。在 `dequeue` 时先检查是否 `outStack` 中有元素，如果没有先将 `inStack` 中所有元素依次 `pop` 出再 `push` 到 `outStack` 中去，此时元素结果两次逆序，自然输出为顺序。

代码如下：

```java
package stacks_and_queues;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueWith2Stacks<Item> {
	
	private Stack<Item> inStack;
	private Stack<Item> outStack;
	
	public QueueWith2Stacks() {
		inStack = new Stack<Item>();
		outStack = new Stack<Item>();
	}
	
	public boolean isEmpty() {
		return inStack.isEmpty() && outStack.isEmpty();
	}
	
	public void enqueue(Item item) {
		inStack.push(item);
	}
	
	public Item dequeue() {
		
		if (isEmpty()) throw new NoSuchElementException();
		
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
		
		return outStack.pop();
	}
	
	public int size() {
		return inStack.size() + outStack.size();
	}
	
	public static void main(String[] args) {
		QueueWith2Stacks<String> queue = new QueueWith2Stacks<String>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-")) {
				StdOut.print(queue.dequeue() + " ");
			}
			else {
				queue.enqueue(s);
			}
		}
		StdOut.println("\n" + queue.size() + " item" + (queue.size() == 1 ? "" : "s") + " left in queue");
	}
	
}
```

#### **2. Stack with max.**

Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.

**Solution**

题目要求我们创建一个新的数据结构 `StackWithMax`，其不仅支持栈的操作，还**支持返回最大值操作**。

我们在课程中已经实现了 `Iterable` 和 `Iterator` 接口，也即我们可以遍历我们的栈，每次查询最大值都遍历一次栈显然是非常低效的实现。下面给出两种方法：

* 方法一：

  双栈。直接使用我们已经实现好的 `Stack` 类，`valStack` 用作存放所有的项，而 `maxStack` 用来跟踪记录最大值的变化。双栈法中每个操作的时间复杂度都是 $O(1)$ 的，其需要占用 $O(n)$ 的额外空间。

  ```java
  package stacks_and_queues;
  
  import java.util.Iterator;
  import java.util.NoSuchElementException;
  
  import edu.princeton.cs.algs4.Stack;
  import edu.princeton.cs.algs4.StdIn;
  import edu.princeton.cs.algs4.StdOut;
  
  public class StackWithMax {
  
  	private Stack<Double> valStack;
  	private Stack<Double> maxStack;
  	
  	public StackWithMax() {
  		valStack = new Stack<Double>();
  		maxStack = new Stack<Double>();
  	}
  	
  	
  	public boolean isEmpty() {
  		return valStack.isEmpty();
  	}
  	
  	public void push(Double item) {
  		valStack.push(item);
  		if (maxStack.isEmpty()) {
  			maxStack.push(item);
  		} else {
  			Double max = getMax();
  			if (max > item) {
  				maxStack.push(max);
  			} else {
  				maxStack.push(item);
  			}
  		}
  	}
  	
  	public Double pop() {
  		if (valStack.isEmpty()) throw new NoSuchElementException();
  		maxStack.pop();
  		return valStack.pop();
  	}
  	
  	public Double getMax() {
  		if (maxStack.isEmpty()) throw new NoSuchElementException();
  		Iterator<Double> iter = maxStack.iterator();
  		Double max = iter.next();
  		return max;
  	}
  	
  	public int size() {
  		return valStack.size();
  	}
  	
  	public static void main(String[] args) {
  		StackWithMax stack = new StackWithMax();
  		StdOut.println("Input '+ 4' to push 4 in stack OR '-' to pop from stack");
  		while (!StdIn.isEmpty()) {
  			String op = StdIn.readString();
  			if (op.equals("-")) stack.pop();
  			else if (op.equals("+")) {
  				String val = StdIn.readString();
  				stack.push(Double.parseDouble(val));
  			}
  			
  			StdOut.println("Max : " + stack.getMax());
  		}
  	}
  	
  }
  ```

* 方法二：

  网上 :surfer: 在 [GeeksforGeeks](https://www.geeksforgeeks.org/find-maximum-in-a-stack-in-o1-time-and-o1-extra-space/) 找到了一种只花费 $O(1)$ 额外空间的方法。该方法额外只维护了一个 `max` 值：

  `push(x)`：

  * 如果栈为空，将 `x` 入栈且令 `max=x`
  * 否则不为空，将 `x` 与 `max` 对比
    * 如果 `x<=max`，则将 `x` 入栈，`max` 不变
    * 否则 `x>max`，则将 `2x-max` 入栈，且令 `max=x`

  `pop()`：假设当前栈顶元素为 `y`

  * 如果 `y<=max`，则弹出 `y` 并返回 `y`， `max` 不变
  * 如果 `y>max`，则弹出 `y` 但返回 `max`，再令 `max=2max-y`

  上述算法 `push` 中的 `2x-max` 也可以改成 `n*x-max(n>=2)`，比如 `3x-max`，但相应地也要在 `pop` 中有所改动：`max=n*max-y`。理解这一点也就理解了方法二。

  对于这种方法，如果发现当前的栈顶元素值 `y` 比最大值 `max` 还大，就说明当前的栈顶元素 `y` 实际上就是在当初入栈的时候更新了 `max` 值的元素。

  ```java
  package stacks_and_queues;
  
  import java.util.Iterator;
  import java.util.NoSuchElementException;
  
  import edu.princeton.cs.algs4.Stack;
  import edu.princeton.cs.algs4.StdIn;
  import edu.princeton.cs.algs4.StdOut;
  
  public class StackWithMaxLessSpace {
  	private Stack<Double> val;
  	private double max;
  	
  	public StackWithMaxLessSpace() {
  		val = new Stack<Double>();
  	}
  	
  	public boolean isEmpty() {
  		return val.isEmpty();
  	}
  	
  	public void push(Double item) {
  		if (val.isEmpty()) {
  			val.push(item);
  			max = item;
  		} else {
  			if (item <= max) val.push(item);
  			else {
  				val.push(2*item-max);
  				max = item;
  			}
  		}
  	}
  	
  	public Double pop() {
  		if (val.isEmpty()) throw new NoSuchElementException();
  		Double item = val.pop();
  		if (item <= max) {
  			return item;
  		}
  		else {
  			Double realItem = max;
  			max = 2*max-item;
  			return realItem;
  		}
  	}
  	
  	public Double getMax() {
  		if (val.isEmpty()) throw new NoSuchElementException();
  		return max;
  	}
  	
  	public int size() {
  		return val.size();
  	}
  
  	public static void main(String[] args) {
  		StackWithMaxLessSpace stack = new StackWithMaxLessSpace();
  		StdOut.println("Input '+ 4' to push 4 in stack OR '-' to pop from stack");
  		while (!StdIn.isEmpty()) {
  			String op = StdIn.readString();
  			if (op.equals("-")) stack.pop();
  			else if (op.equals("+")) {
  				String val = StdIn.readString();
  				stack.push(Double.parseDouble(val));
  			}
  			
  			StdOut.println("Max : " + stack.getMax());
  		}
  	}
  	
  }
  ```

#### **3. Java generics.** 

Explain why Java prohibits generic array creation.

*Hint:* to start, you need to understand that Java arrays are *covariant* but Java generics are not: that is, `String[]` is a subtype of `Object[]`, but `Stack<String>` is not a subtype of `Stack<Object>`.

关于这个问题网上有很多相关的回答，比如 [What's the reason I can't create generic array types in Java? - StackOverflow](https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java) 以及 [java为什么不支持泛型数组？](https://www.zhihu.com/question/20928981)。

看了以后依然是不太懂，个人理解如下（感觉错误连篇）：

"Java arrays are covariant" 的意思即，Java 中的数组对**自身包含的元素是有要求**的：如果有数组 `T[]`，那么该数组包含的元素要么类型是 `T`，要么类型是 `T` 的子类型。Java 为了确保类型安全，对数组要求其必须明确知道内部元素的类型，而且会”记住“这个类型，每次往数组里插入新元素都会进行类型检查以查看是否匹配。

那让数组记住泛型有什么问题呢？问题就在于 Java 的泛型是使用擦除（Erasure）实现的，在运行时类型参数会被擦除掉，这样的话，所谓的 `Stack<String>` 和 `Stack<Integer>` 到运行时都不过是 `Stack<Object>`。因此，如果要创建一个 `Stack<String>` 的数组，实际上我们创建的是一个 `Stack<Object>` 的数组，而 `Stack<String>` 又并非是 `Stack<Object>` 的子类型，那么我们想往这个数组中加入一个 `Stack<String>` 的元素会报错，这就很奇怪了，由于擦除，我们竟然不能将 `Stack<String>` 元素插入到我们创建的认为是 `Stack<String>` 的数组中去，那创建泛型数组本身就失去了意义。

#### 参考

* [How to implement a queue using two stacks? - StackOverflow](https://stackoverflow.com/questions/69192/how-to-implement-a-queue-using-two-stacks)
* [716. Max Stack - LeetCode](https://leetcode.com/articles/max-stack/)
* [Find maximum in a stack in O(1) time and O(1) extra space](https://www.geeksforgeeks.org/find-maximum-in-a-stack-in-o1-time-and-o1-extra-space/)
* [What's the reason I can't create generic array types in Java? - StackOverflow](https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java) 
* [java为什么不支持泛型数组？](https://www.zhihu.com/question/20928981)

