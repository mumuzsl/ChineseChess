package app.mumu.chinesechess;


/**
 * @author mumu
 * @date 2019/3/30
 */
public class Guard extends ChessPieces implements Run {

//	public Guard() {
//		
//	}

	public Guard(Camp color) {
		super("Guard", color,1);
	}

	@Override
	public void run() {
	}

	@Override
	public boolean isRule(MyPoint from, MyPoint dest) {
		if (distance(from, dest) == 2) {
			if (color == Camp.BLACK && dest.x <= 3 && dest.y >= 4 && dest.y <= 6)
				return true;
			if (color == Camp.RED && dest.x >= 8 && dest.y >= 4 && dest.y <= 6)
				return true;
		}
		return false;
	}

}
