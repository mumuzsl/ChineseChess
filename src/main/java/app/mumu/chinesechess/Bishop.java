package app.mumu.chinesechess;


/**
 * @author mumu
 * @date 2019/3/30
 */
public class Bishop extends ChessPieces implements Run {

    public Bishop(Camp color) {
        super("Bishop", color, 2);
    }

    @Override
    public void run() {
    }

    @Override
    public boolean isRule(MyPoint from, MyPoint dest) {
        if (distance(from, dest) == 8) {
            return (color == Camp.BLACK && dest.x <= 5)
                    || (color == Camp.RED && dest.x >= 6);
        }
        return false;
    }
}
