package app.mumu.chinesechess;

/**
 * @author mumu
 * @date 2019/3/30
 */
public class MyPoint {

	public MyPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public MyPoint(int x, int y, int v) {
		this.x = x;
		this.y = y;
		this.value = v;
	}

	public MyPoint() {
		this.x = 0;
		this.y = 0;
	}

	public boolean isable() {
		return x >= 1 && x <= 10 && y <= 9 && y >= 1;
	}

	public int x;
	public int y;
	public int value;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPoint other = (MyPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public MyPoint toRealNum() {
		return new MyPoint(this.x + 1, this.y + 1);
	}

	public String out() {
		return "(" + x + "," + y + ")";
	}
}
