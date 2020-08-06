package app.mumu.chinesechess;


/**
 * @author mumu
 * @date 2019/3/30
 */
public class Rook extends ChessPieces implements Run {

	public Rook(Camp color) {
		super("Rook", color, 4);
	}

	@Override
	public void run() {

	}

	@Override
	public boolean isRule(MyPoint from, MyPoint dest) {
		if (from.x == dest.x || dest.y == from.y)
			return true;
		return false;
	}

}
