package app.mumu.chinesechess;

/**
 * @author mumu
 * @date 2019/3/30
 */
public interface Run {

	void run();

	boolean isRule(MyPoint from,MyPoint dest);

}
