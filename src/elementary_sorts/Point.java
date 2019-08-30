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
