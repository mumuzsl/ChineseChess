package app.mumu.chinesechess;

/**
 * @author mumu
 * @date 2019/3/30
 */
public class Moveer {
	public boolean move(int i, int j) {
		if (i < 1 || j < 1 || i > 9 || j > 9)
			return false;
		
		return true;
	}
}
