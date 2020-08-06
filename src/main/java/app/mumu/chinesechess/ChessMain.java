package app.mumu.chinesechess;

import java.lang.Thread;

/**
 * @author mumu
 * @date 2019/3/30
 */
public class ChessMain {

	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.init();
		chessBoard.out();
//		chessBoard.outCB();
//		try {
//			chessBoard.move(new MyPoint(3, 8), new MyPoint(10, 8));
//		} catch (Exception e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		chessBoard.out();
		ChessFrame chessFrame = new ChessFrame(chessBoard);
		if (args.length >= 1) {
			System.out.println(args[0]);
			chessFrame.cbg.setTn(Integer.parseInt(args[0]));
			Thread t = new Thread(chessFrame.cbg);
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
