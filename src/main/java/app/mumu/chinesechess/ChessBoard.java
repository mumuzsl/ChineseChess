package app.mumu.chinesechess;

import java.lang.Math;
import java.util.ArrayList;

/**
 * @author mumu
 * @date 2019/3/30
 */
public class ChessBoard {

	public static final Camp RED = Camp.RED;

	public static final Camp BLACK = Camp.BLACK;

	public ChessPieces[][] cb;

	// 下棋顺序的标志：0表示黑方，1表示红方。
	public int flag = 1;
	// 下棋输赢标志：0表示平局，1表示红方胜，2表示黑方胜
	public int vflag = 0;

	// 生产“将”和“帅”
	General[] generals;
	// 生产其他棋子，不包括“兵”和“卒”
	ChessPieces[][] chessPieces;
	// 生产“兵”和“卒”
	Pawn[] pawns;

	public ChessBoard() {
		cb = new ChessPieces[11][10];
		generals = new General[2];
		chessPieces = new ChessPieces[5][4];
		pawns = new Pawn[10];
	}

	public void init() {
		prodCP();
		int i, j;
		cb[1][5] = generals[0];
		cb[10][5] = generals[1];
		for (j = 4; j >= 1; j--) {
			cb[1][j] = chessPieces[4 - j][0];
			cb[1][10 - j] = chessPieces[4 - j][1];
			cb[10][j] = chessPieces[4 - j][2];
			cb[10][10 - j] = chessPieces[4 - j][3];
		}
		cb[3][2] = chessPieces[4][0];
		cb[3][8] = chessPieces[4][1];
		cb[8][2] = chessPieces[4][2];
		cb[8][8] = chessPieces[4][3];
		for (i = 0; i < 5; i++) {
			cb[4][2 * i + 1] = pawns[i];
			cb[7][2 * i + 1] = pawns[i + 5];
		}
		flag = 1;
		vflag = 0;
	}

	public synchronized void reGame() {
		cb = null;
		cb = new ChessPieces[11][10];
		init();
	}

	// 生产棋子
	public void prodCP() {

		int i, j;
		generals[0] = new General(BLACK);
		generals[1] = new General(RED);
		for (j = 0; j < 2; j++) {
			chessPieces[0][j] = new Guard(BLACK);
			chessPieces[0][j + 2] = new Guard(RED);
		}
		for (j = 0; j < 2; j++) {
			chessPieces[1][j] = new Bishop(BLACK);
			chessPieces[1][j + 2] = new Bishop(RED);
		}
		for (j = 0; j < 2; j++) {
			chessPieces[2][j] = new Horse(BLACK);
			chessPieces[2][j + 2] = new Horse(RED);
		}
		for (j = 0; j < 2; j++) {
			chessPieces[3][j] = new Rook(BLACK);
			chessPieces[3][j + 2] = new Rook(RED);
		}
		for (j = 0; j < 2; j++) {
			chessPieces[4][j] = new Cannon(BLACK);
			chessPieces[4][j + 2] = new Cannon(RED);
		}
		for (i = 0; i < 5; i++) {
			pawns[i] = new Pawn(BLACK);
			pawns[i + 5] = new Pawn(RED);
		}
	}

	public void out() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 9; j++)
				System.out.print(cb[i][j] + "\t");
			System.out.println();
		}
		System.out.println();
	}

	public boolean canMv() {
		return true;
	}

	public synchronized void move(MyPoint from, MyPoint dest) throws Exception {
		if (cb[from.x][from.y] == null)
			throw new Exception("没有可以移动的棋子！");
		if ((flag == 1 && cb[from.x][from.y].color == BLACK) || (flag == 0 && cb[from.x][from.y].color == RED))
			throw new Exception("顺序错误！");
		if (!(from.isable() && dest.isable()))
			throw new Exception("坐标无效！");
		if (cb[dest.x][dest.y] != null && cb[from.x][from.y].color == cb[dest.x][dest.y].color)
			throw new Exception("不能这样移动！");
		if (cb[from.x][from.y].getClass() == Cannon.class) {
			if (!IsCannRule(from, dest))
				throw new Exception("不符合" + cb[from.x][from.y] + "移动规则！");
		} else if (cb[from.x][from.y].getClass() == Horse.class) {
			if (!((cb[from.x][from.y] instanceof Run) && ((Run) cb[from.x][from.y]).isRule(from, dest))
					|| !IsHorsRule(from, dest))
				throw new Exception("不符合" + cb[from.x][from.y] + "移动规则！");
		} else if (cb[from.x][from.y].getClass() == Rook.class) {
			if (!IsRooRule(from, dest))
				throw new Exception("不符合" + cb[from.x][from.y] + "移动规则！");
		} else if (!((cb[from.x][from.y] instanceof Run) && ((Run) cb[from.x][from.y]).isRule(from, dest)))
			throw new Exception("不符合" + cb[from.x][from.y] + "移动规则！");

		if (cb[dest.x][dest.y] != null) {
			if (cb[dest.x][dest.y].getClass() == General.class && cb[dest.x][dest.y].color == RED)
				vflag = 2;
			else if (cb[dest.x][dest.y].getClass() == General.class && cb[dest.x][dest.y].color == BLACK)
				vflag = 1;
		}

		cb[dest.x][dest.y] = cb[from.x][from.y];
		cb[from.x][from.y] = null;
		flag = flag == 1 ? 0 : 1;
	}

	private boolean IsCannRule(MyPoint from, MyPoint dest) {
		int num = 0;
		if (from.x == dest.x) {
			for (int i = Math.min(from.y, dest.y) + 1; i < Math.max(from.y, dest.y); i++) {
				if (cb[from.x][i] != null)
					num++;
			}
			return (num == 0 || (num == 1 && cb[dest.x][dest.y] != null));
		} else if (from.y == dest.y) {
			for (int i = Math.min(from.x, dest.x) + 1; i < Math.max(from.x, dest.x); i++) {
				if (cb[i][from.y] != null)
					num++;
			}
			return ((num == 0 && cb[dest.x][dest.y] == null) || (num == 1 && cb[dest.x][dest.y] != null));
		}
		return false;
	}

	private boolean IsRooRule(MyPoint from, MyPoint dest) {
		if (from.x == dest.x) {
			for (int i = Math.min(from.y, dest.y) + 1; i < Math.max(from.y, dest.y); i++) {
				if (cb[from.x][i] != null)
					return false;
			}
			return true;
		} else if (from.y == dest.y) {
			for (int i = Math.min(from.x, dest.x) + 1; i < Math.max(from.x, dest.x); i++) {
				if (cb[i][from.y] != null)
					return false;
			}
			return true;
		}
		return false;
	}

	private boolean IsHorsRule(MyPoint from, MyPoint dest) {
		int x1 = from.x, x2 = dest.x, y1 = from.y, y2 = dest.y;
		if (Math.abs(y1 - y2) == 1) {
			return cb[(x1 + x2) / 2][y1] == null;
		} else if (Math.abs(x1 - x2) == 1) {
			return cb[x1][(y1 + y2) / 2] == null;
		}
		return false;
	}

	public ArrayList<MyPoint> outCB() {
		ArrayList<MyPoint> lp = new ArrayList<>();
		for (int i = 1; i <= 9; i++)
			for (int j = 1; j <= 10; j++) {
				if (cb[j][i] != null)
					lp.add(new MyPoint(i - 1, j - 1, cb[j][i].id));
			}
		return lp;
	}

}
