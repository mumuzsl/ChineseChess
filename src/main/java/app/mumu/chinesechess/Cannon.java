package app.mumu.chinesechess;

/**
 * @author mumu
 * @date 2019/3/30
 */
public class Cannon extends ChessPieces implements Run {

    public Cannon(Camp color) {
        super("Cannon", color, 5);
    }

    @Override
    public void run() {
    }

    @Override
    public boolean isRule(MyPoint from, MyPoint dest) {
        return false;
    }

    public boolean isRule(MyPoint from, MyPoint dest, ChessBoard cb) {
        return false;
    }
}
