## Union Find

### 关于 path compression 的补充说明

在 improvements 一节中我们谈到了对 Quick Union 算法的两种改进，一种是带权（Weighted Quick Union），另外一种是路径压缩（Quick Union + path compression），这两种改进的算法对于在 $N$ 个对象上的 $M$ 次 union-find 操作其 worst-case time 皆为 $N+M\log N$。

该节中还提到了 Weighted Quick Union + path compression，这种方法结合了带权和路径压缩（worst-case time 为 $N+M\lg^*$N），但是在编码上需要注意一些问题。

在实现 Quick Union + path compression 时，我们知道采用单程实现（半展平）只需要在 `root` 中添加一行代码即可：

```java
public int root(int p) {
    id[p] = id[id[p]];	
    p = id[p];
  }
  return p;
}
```

但在实现 Weighted Quick Union + path compression 时，`root` 按照以上实现会出现问题。

Weighted Quick Union 相比于 Quick Union 需要多维护一个整数数组 `sz`（`sz[i]` 表示的就是以 `i` 为根节点的树大小），而在 `root` 中如果我们对节点进行了更改，将节点 `p` 直接指向其祖父节点 `id[id[p]]`。

显然，以其父亲节点 `id[p]` 为根节点的树的大小是要变小的（由于丢失了以节点 `p` 为根节点的子树），故我们不能做到在 `root` 中只添加一行代码了，我们还需要一些代码来维护 `sz` 数组的正确性，如下：

```java
public int root(int p) {
		while (p != id[p]) {
			if (id[p] != id[id[p]]) {
				sz[id[p]] -= sz[p];
				id[p] = id[id[p]];	
			}
			p = id[p];
		}
		return p;
	}
```

如果不维护 `sz` 数组的正确性，结果会不会有什么不同呢？答案是，并不会。

在不维护 `sz` 正确性的情况下，`sz` 数组确实会出现问题，但各个连通分量其根节点在 `sz` 数组的值是正确的。

### Interview Questions: Union–Find (ungraded)

**1. Social network connectivity.** 

Given a social network containing `n` members and a log file containing `m` timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). 

Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be $m\log n$ or better and use extra space proportional to $n$.

*Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.*

**Solution**

使用 Weighted Quick Union，Quick Union + path compression 中的任何一个即可达到题目要求（`n` members 即有 `n` 个对象，`m` timestamps 即有 `m` 次 union 操作）

```java
package union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SocialNetworkConnectivity {

	private WeightedQuickUnionUF uf;
	private int timestamps;
	
	public SocialNetworkConnectivity(int N) {
		uf = new WeightedQuickUnionUF(N);
	}
	
	public void formFriendShip(int p, int q) {
		uf.union(p, q);
		timestamps++;
	}
	
	public boolean isAllConnected() {
		return uf.count() == 1;
	}
	
	public int getTimestamps() {
		return timestamps;
	}
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		int M = StdIn.readInt();
		
		SocialNetworkConnectivity snc = new SocialNetworkConnectivity(N);  
		
		for (int i = 0; i < M; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			snc.formFriendShip(p, q);
			if (snc.isAllConnected()) {
				StdOut.println("The earliest time is : " + snc.getTimestamps());
				break;
			}
		}
	}
	
}
```

**2. Union-find with specific canonical element** 

Add a method `find()` to the union-find data type so that `find(i)` returns the largest element in the connected component containing `i`. The operations, `union()`, `connected()`, and `find()` should all take logarithmic time or better.

For example, if one of the connected components is {1, 2, 6, 9}, then the `find()` method should return 9 for each of the four elements in the connected components.

**Solution**

给并查集算法添加一个 `find(i)` 函数，该函数能够返回 `i` 对象所在的连通分量中的最大值。要求 `union()`, `connected()`, and `find()` 操作最多只能是线性对数的复杂度，所以我们在 Weighted Quick Union，Quick Union + path compression 上任选一例来继续添加一个 `find` 方法。

注：由于原先实现的 Weighted Quick Union 我们已经实现了一个 `find()` 方法，故本题内容实现在 `findMax()` 方法中。

实现思路是额外再维护一个 `max[]` 数组，其中 `max[i]` 表示以 `i` 为根节点的树中最大的值，这样如果要获取 `i` 所在的连通分量中的最大值，只需要找到 `i` 的根节点 `iRoot`，`max[iRoot]` 即是结果。

```java
package union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FindMaxUF {
	
	private int[] id;
	private int[] sz;
	private int[] max;
	private int count;
	
	public FindMaxUF(int N) {
		StdOut.println(this.getClass());
		count = N;
		id = new int[N];
		sz = new int[N];
		max = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
			max[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}
	
	public int findMax(int p) {
		return max[find(p)];
	}
	
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) {
			return ;
		}
		if (sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
			if (max[pRoot] > max[qRoot]) {
				max[qRoot] = max[pRoot];
			}
		}
		else {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
			if (max[qRoot] > max[pRoot]) {
				max[pRoot] = max[qRoot];
			}
		}
		count--;
	}
	
	public static void main(String[] args) {
		
		int N = StdIn.readInt();
		int M = StdIn.readInt();
		
		FindMaxUF uf = new FindMaxUF(N);
		
		for (int i = 0; i < M; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) {
				continue;
			}
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		
		StdOut.println();
		
		for (int i = 0; i < N; i++) {
			StdOut.print(uf.findMax(i) + " ");
		}
		
	}	
}
```

**3. Successor with delete** 

Given a set of `n` integers `S = { 0, 1, ... , n-1}` and a sequence of requests of the following form:

- Remove `x` from `S`
- Find the *successor* of `x`: the smallest `y` in `S` such that $y \ge x$.

design a data type so that all operations (except construction) take logarithmic time or better in the worst case.

**Solution**

这道题最直观的方法也许是创建一个长度为 `n` 的 boolean 数组 `isRemove[]`，一旦删除了 `i`，就将 `isRemove[i]` 设为 `true`，在询问 `x` 的后继时，从索引 `x` 开始往后遍历 `isRemove[]`，一旦遇到 `false`，即找到后继。但这种方法在 wort case 下寻找后继可能需要 `n` 的复杂度。

既然出现在这里，那么就知道这道题还能用 Union-Find 来求解。

思路是对那些被 remove 的数做 union，如果我们 remove 了 `x`，那么我们会查看是否 `x-1` 和 `x+1` 也被 remove 了（故还是需要维护一个 `isRemove[]` 数组，但并不对它进行遍历），如果是，那么进行 union。

在查询 `x` 的后继时，我首先查看 `x` 是否被 remove 了，如果没有，那么显然 `x` 就是结果；如果被移除了，那么我们查询 `x` 所在的 union 中的最大值，最大值再加 1 就是结果（注意，第 2 问的 `findMax` 派上了用场）

```java
package union_find;

import edu.princeton.cs.algs4.StdOut;

public class SuccessorWithDelete {
	
	private FindMaxUF uf;
	private boolean[] isRemove;
	
	public SuccessorWithDelete(int N) {
		uf = new FindMaxUF(N);
		isRemove = new boolean[N];
	}
	
	public void remove(int x) {
		if (isRemove[x]) {
			return ;
		}
		isRemove[x] = true;
		if (x > 0 && isRemove[x-1]) {
			uf.union(x-1, x);
		}
		if (x < isRemove.length - 1 && isRemove[x+1]) {
			uf.union(x, x+1);
		}
	}
	
	public int find(int x) {
		
		if (!isRemove[x]) {
			return x;
		}
		else {
			int max = uf.findMax(x);
			if (max == isRemove.length - 1) {
				return -1;
			} else {
				return max + 1;
			}
		}
		
	}
	
	public static void main(String[] args) {
		SuccessorWithDelete swd = new SuccessorWithDelete(10);
		swd.remove(6);
		StdOut.println("Successor of 6 is : " + swd.find(6));
		swd.remove(5);
		StdOut.println("Successor of 5 is : " + swd.find(5));
		swd.remove(3);
		StdOut.println("Successor of 3 is : " + swd.find(3));
		swd.remove(4);
		StdOut.println("Successor of 4 is : " + swd.find(4));
		swd.remove(7);
		StdOut.println("Successor of 7 is : " + swd.find(7));
		StdOut.println("Successor of 3 is : " + swd.find(3));
		StdOut.println("Successor of 2 is : " + swd.find(2));
	}
	
}

```

