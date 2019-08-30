## Elementary Sorts

### Interview Questions: Elementary Sorts (ungraded)

#### **1. Intersection of two sets.** 

Given two arrays a[] and b[], each containing $n$ distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].

*Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.*

> subquadratic : Describing an algorithm that runs in greater than linear, but less than quadratic time

**Solution**

题目要求 subquadratic 的时间复杂度，在 Elementary Sort 算法中，只有 Shell Sort 突破了 subquadratic 的下界，故本题先使用 Shell Sort 对 a[] 和 b[] 进行排序，再从左至右对已经有序的 a[] 和 b[] 中元素进行比对，以求得交集。

```java
package elementary_sorts;

public class Point implements Comparable<Point> {
	
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object x) {
		if (this == x) return true;
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Point that = (Point) x;
		if (this.x != that.x) return false;
		if (this.y != that.y) return false;
		return true;
	}
	
	@Override
	public int compareTo(Point that) {
		if (this.x < that.x) return -1;
		if (this.x > that.x) return 1;
		if (this.y < that.y) return -1;
		if (this.y > that.y) return 1;
		return 0;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
```

```java
package elementary_sorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Intersection {
	
	public static Point[] genPointSet(int N, int lo, int hi) {
		Point[] ret = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = StdRandom.uniform(lo, hi);
			int y = StdRandom.uniform(lo, hi);
			ret[i] = new Point(x, y);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int N = 10;
		Point[] a = genPointSet(N, 1, 6);
		Point[] b = genPointSet(N, 1, 6);
		
		Shell.sort(a);
		Shell.sort(b);
		
		for (int i = 0; i < N; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
		for (int i = 0; i < N; i++) {
			StdOut.print(b[i] + " ");
		}
		StdOut.println();
		
		int i = 0;
		int j = 0;
		int count = 0;
		
		while (i < N && j < N) {
			if (a[i].equals(b[j])) {
				i++;
				j++;
				count++;
			}
			else if (a[i].compareTo(b[j]) < 0) {
				i++;
			}
			else {
				j++;
			}
		}
		
		StdOut.println("Intersection : " + count);
	}
	
}
```



#### **2. Permutation.** 

Given two integer arrays of size $n$, design a subquadratic algorithm to determine whether one is a permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.

**Solution**

同样使用 Shell Sort 以满足 subquadratic 的时间复杂度要求，对两个整数数组进行排序，然后检查排序后两个整数数组是否在相应位置上总是元素相同即可。

```java
package elementary_sorts;

import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	
	public static boolean hasSameEntries(Integer[] a, Integer[] b) {
		Shell.sort(a);
		Shell.sort(b);
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		Integer[] a = {1, 2, 3, 4, 5, 6, 7};
		Integer[] b = {2, 1, 5, 7, 4, 3, 6};
		Integer[] c = {1, 3, 5, 7, 9, 11, 13};
		
		StdOut.println("a & b : " + hasSameEntries(a, b));
		StdOut.println("b & c : " + hasSameEntries(b, c));
		
	}
	
}

```



#### **3. Dutch national flag** 

Given an array of $n$ buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:

- `swap(i, j)` : swap the pebble in bucket $i$ with the pebble in bucket $j$.
- `color(i)` : determine the color of the pebble in bucket $i$.

The performance requirements are as follows:

- At most $n$ calls to `color()`.
- At most $n$ calls to `swap()`.
- Constant extra space.

**Solution**

题意是，给定 $n$ 个桶，每个桶中都放置了一块小石头，小石头的颜色是红，白，蓝中的一种。现在要求根据小石头的颜色进行排序，使得所有红色的小石头在左边（前面），白色的小石头在中间，蓝色的小石头在右边（后面）。性能要求是：`swap(i, j)` 可以交换 `i` 桶和 `j` 桶中的小石头，`color(i)` 可以查看 `i` 桶中小石头的颜色，但这两个操作都只能调用 $n$ 次，且只允许额外再占用常量的空间。

在这样的性能要求下，容易想到的是：从前往后遍历这 $n$ 个桶，如果桶中的小石头为红色，那么将该石头放到前面的桶中去；如果桶中的小石头为蓝色，那么将该石头放到后面的桶中去；如果桶中的小石头为白色，那么查看下一个桶。

```java
lo = 0; hi = N - 1; i = 0

while (i <= hi) {
	color = a[i].color;
	if (color == red) {
		swap(lo, i);
		lo++;
		i++;
	}
	else if (color == blue) {
		swap(i, hi);
		hi--;
		i++;
	}
	else {
		i++;
	}
}
```

但还存在一个问题：如果当前桶中的小石头不是白色，我们显然需要进行 `swap` 把当前桶中的小石头放到合适的地方，关键是 `swap` 后当前桶中的新小石头是什么颜色呢？如果交换回来的新小石头不是白色，显然我们需要还需要做更多操作，然后再查看下一个桶。

这里需要思考**恒定式**是什么，排序终究是一个局部有序到整体有序的过程。

* `[~, lo)` 必须为红色
* `[lo, i)` 必然为白色
* `[i, hi]` 是还未处理的小石头
* `(hi, ~]` 右边必须为蓝色

假设当前已满足以上四点，现在查看下一个桶。

* 当前桶如果是白色 `a[i] == white`，那我们继续查看下一个桶`i++`，以上四点依然不变
* 当前桶如果是红色 `a[i] == red`，进行 `swap(lo, i)`，显然根据恒定式第二点可知，`a[lo] == white`。交换后 `a[lo] ==  red`，恒定式第二点被破坏，需要 `lo++`；交换后 `a[i] == white`，无需再做过多处理，`i++` 以维护恒定式第三点
* 当前桶如果是蓝色 `a[i] == blue`，进行 `swap(i, hi)`。和是红色的情况不同，我们不知道 `a[hi]` 到底是什么颜色。所以如果进行交换，交换后 `a[hi] == blue `，恒定式第三点被破坏，需要 `hi--`；交换后 `a[i] == ?` ，由于不确定交换后桶中新小石头的颜色，所以可以认为当前桶 `i` 属于还未处理，故不需要 `i++`。



略作修改，把伪代码中的 `hi--` 后面的那条语句 `i++` 去掉即可。

```java
package elementary_sorts;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DutchNationalFlag {
	
	private Counter colorCalls;
	private Counter swapCalls;
	private int[] buckets;
	private int N;
	
	public DutchNationalFlag(int[] b) {
		N = b.length;
		buckets = new int[N];
		for (int i = 0; i < N; i++) {
			buckets[i] = b[i];
		}
		
		colorCalls = new Counter("color");
		swapCalls = new Counter("swap");
	}
	
	public int color(int i) {
		colorCalls.increment();
		return buckets[i];
	}
	
	public void swap(int i, int j) {
		swapCalls.increment();
		int tmp = buckets[i];
		buckets[i] = buckets[j];
		buckets[j] = tmp;
	}
	
	public void sort() {
		int lo = 0;
		int hi = N-1;
		int i = 0;
		
		while (i <= hi) {
			int color = color(i);
			if (color == 1) {	// red
				swap(lo, i);
				lo++;
				i++;
			} 
			else if (color == 3) {	// blue
				swap(i, hi);
				hi--;
			}
			else {	// white
				i++;
			}
		}
	}
	
	public String getColorCalls() {
		return colorCalls.toString();
	}
	
	public String getSwapCalls() {
		return swapCalls.toString();
	}
	
	public void showBuckets() {
		for (int i = 0; i < N; i++) {
			StdOut.print(buckets[i] + " ");
		}
		StdOut.println();
	}
	
	
	
	public static void main(String[] args) {
		
		int N = 1000;
		int[] testCase = new int[N];
		for (int i = 0; i < N; i++) {
			testCase[i] = StdRandom.uniform(1, 4);
		}
		
		StdOut.println(testCase.length);
		DutchNationalFlag dnf = new DutchNationalFlag(testCase);
		
		dnf.showBuckets();
		
		dnf.sort();
		
		dnf.showBuckets();
		StdOut.println(dnf.getColorCalls());
		StdOut.println(dnf.getSwapCalls());

	}
	
}

```