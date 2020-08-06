package app.mumu.chinesechess;


/**
 * @author mumu
 * @date 2019/3/30
 */
public class Pawn extends ChessPieces implements Run {

	public Pawn(Camp color) {
		super("Pawn", color, 6);
	}

	@Override
	public void run() {
	}

	@Override
	public boolean isRule(MyPoint from, MyPoint dest) {
		if (distance(from, dest) == 1) {
			if (color == Camp.BLACK) {
				return dest.x > from.x || (dest.x == from.x && dest.x >= 6);
			}

			if (color == Camp.RED) {
				return dest.x < from.x || (dest.x == from.x && dest.x <= 5);
			}
		}
		return false;
	}
}
