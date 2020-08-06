package app.mumu.chinesechess;


/**
 * 数据类。
 * 窗口的数据都在这里设置，更加方便。
 *
 * @author mumu
 * @date 2019/3/30
 */
public final class Param {
	//宽度
	public static final int 	width 		= 800;
	//高度
	public static final int 	height   	= 900;
	//棋盘大小
	public static final Size 	CB_SIZE 	= new Size(width, height);
	//半径
	public static final int 	RADIUS 		= 30;
	//直径
	public static final int 	DIA 		= 2 * RADIUS;
	//坐标原点
	public static final MyPoint ORG 		= new MyPoint(10, 10);
	//窗口宽度
//	public static final int 	Fwidht 		= width + 3 * ORG.x;
	public static final int 	Fwidht 		= width + 25;
	//窗口高度
//	public static final int 	Fheight 	= height + 6 * ORG.y;
	public static final int 	Fheight 	= height + 80;
	//行数
	public static final int 	ROWS 		= 12;
	//列数
	public static final int 	COLS 		= 10;
	//单位大小
	public static final Size  	U_SIZE		= new Size(CB_SIZE.width / COLS, CB_SIZE.height / ROWS);
	//单位坐标
	public static final MyPoint U_POINT		= new MyPoint(U_SIZE.width - RADIUS, U_SIZE.height - RADIUS);
	//真实坐标原点
	public static final MyPoint REAL_ORG 	= new MyPoint(10, 10);
	//高度的一半
	public static final int 	HALF_HEIGHT  = 5 * U_SIZE.height;
}
