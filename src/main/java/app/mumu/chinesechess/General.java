package app.mumu.chinesechess;



/**
 * @author mumu
 * @date 2019/3/30
 */
public class General extends ChessPieces implements Run {

	public General(Camp color) {
		super("General", color,0);
	}

	@Override
	public void run() {
	}

	@Override
	public boolean isRule(MyPoint from, MyPoint dest) {
		if (distance(from, dest) == 1) {
			if (color == Camp.BLACK && dest.x <= 3 && dest.y >= 4 && dest.y <= 6)
				return true;
			if (color == Camp.RED && dest.x >= 8 && dest.y >= 4 && dest.y <= 6)
				return true;
		}
		return false;
	}

}
