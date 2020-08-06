package app.mumu.chinesechess;


/**
 * @author mumu
 * @date 2019/3/30
 */
public class ChessPieces {

	protected String name;

	protected Camp color;

	protected int id;

	public ChessPieces(String name, Camp color, int id) {
		this.name = name;
		this.color = color;
		this.id = (color == Camp.RED) ? 7 + id : id;
	}

	public Camp getColor() {
		return color;
	}

	public void setColor(Camp color) {
		this.color = color;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return name.substring(0, 3) + color.toString().substring(0, 1).toUpperCase();
	}

	public static int distance(MyPoint from, MyPoint dest) {
		return (from.x - dest.x) * (from.x - dest.x) + (from.y - dest.y) * (from.y - dest.y);
	}
}
