package app.mumu.chinesechess;


/**
 * @author mumu
 * @date 2019/3/30
 */
public class Horse extends ChessPieces implements Run {

	public Horse(Camp color) {
		super("Horse", color,3);
	}

	@Override
	public void run() {
	}

	@Override
	public boolean isRule(MyPoint from, MyPoint dest) {
		return distance(from, dest) == 5;
	}

}
