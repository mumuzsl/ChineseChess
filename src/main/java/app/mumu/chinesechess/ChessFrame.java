package app.mumu.chinesechess;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.*;

import app.mumu.chinesechess.random.*;

public class ChessFrame extends JFrame {

    public ChessBoard chessBoard;
    public ChessBoardPanel cbg;
    public JProgressBar[] jpbl;

//	public static void main(String[] args) {
//		ChessFrame cf = new ChessFrame();
//	}

    public ChessFrame(ChessBoard c) {
        super("象棋");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 10);
        setSize(Param.Fwidht, Param.Fheight);
        setResizable(false);
        cbg = new ChessBoardPanel(c);
        JMenuBar jMenuBar = new JMenuBar();
        cpButton jButton = new cpButton(cbg, "重开一局！");
        JLabel jLabel = new JLabel("赢率：");
        cbg.jl = jLabel;
        jMenuBar.add(jButton);
        jMenuBar.add(jLabel);
        jpbl = new JProgressBar[10];
        for (int i = 0; i < jpbl.length; i++) {
            jpbl[i] = new JProgressBar();
        }
        for (JProgressBar j : jpbl)
            jMenuBar.add(j);
        cbg.jpbl = jpbl;
        add(cbg);
        setJMenuBar(jMenuBar);
        setVisible(true);
    }

}

class ChessBoardPanel extends JPanel implements Runnable {

    public final int width = Param.CB_SIZE.width; // 宽度
    public final int height = Param.CB_SIZE.height; // 高度
    public final int rows = Param.ROWS; // 行数
    public final int cols = Param.COLS; // 列数
    public final int rad = Param.RADIUS; // 半径
    public final int dia = Param.DIA;// 直径
    public final Size cbsize = Param.CB_SIZE; // 棋盘大小
    public final MyPoint org = Param.ORG; // 棋盘坐标原点
    public final Size uSize; // 单位大小
    public final MyPoint uPoint; // 单位坐标
    public ArrayList<MyPoint> lp; //
    public ChessBoard cb;// 棋盘
    public ArrayList<MyPoint> lpp; //
    public ArrayList<Image> Im; //
    private int tn; //
    public JProgressBar[] jpbl; //
    public int vb, vr; //
    public JLabel jl; //

    private JLabel tipLabel;

    public void setTn(int tn) {
        this.tn = tn;
    }

    public ChessBoardPanel(ChessBoard c) {
        uSize = new Size(cbsize.width / cols, cbsize.height / rows);
        uPoint = new MyPoint(uSize.width - rad, uSize.height - rad);
        cb = c;
        lp = new ArrayList<MyPoint>();
        lpp = cb.outCB();
        Im = new ArrayList<>();
        init();
        setBackground(Color.WHITE);
        tipLabel = new JLabel();
        add(tipLabel);
        vb = 0;
        vr = 0;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MyPoint p = toPoint(e);
                if (toRow(p) == 5)
                    return;
                drawSelect(getGraphics(), p);
                if (!lp.contains(p))
                    lp.add(p);
                int s;
//                tip("");
                if ((s = lp.size()) == 2) {
                    try {
//						System.out.println((toNum(lp.get(s - 2)).out() + " " + toNum(lp.get(s - 1)).out()));
                        cb.move(toNum(lp.get(s - 2)), toNum(lp.get(s - 1)));
                        tip("");
                    } catch (Exception e1) {
//						e1.printStackTrace();
                        System.out.println(e1.getMessage());
                        tip(e1.getMessage());
                    }
                    lpp = cb.outCB();
                    repaint();
                    lp.clear();
                    if (cb.vflag != 0)
                        JOptionPane.showMessageDialog(getParent(), getWinner(cb.vflag), "结束", 1);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mouseClicked(e);
            }

        });
    }

    private void tip(String tip) {
        tipLabel.setText(tip);
    }

    public void move(int n) {
        MyPoint f = new MyPoint(), d = new MyPoint();
        int j;
        for (int i = 0; i < n; i++) {
            j = (int) ((double) i / n * 1000);
            jpbl[j / 100].setValue(j % 100);
//			System.out.println(j);
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
            f.x = MyRandom.randomInt(1, 11);
            f.y = MyRandom.randomInt(1, 10);
            d.x = MyRandom.randomInt(1, 11);
            d.y = MyRandom.randomInt(1, 10);
            try {
                cb.move(f, d);
            } catch (Exception e1) {
//				System.out.println(e1.getMessage());
            }
            lpp = cb.outCB();
            repaint();
            lp.clear();
            if (cb.vflag != 0) {
                System.out.println(getWinner(cb.vflag));
                getWinnerRatio(cb.vflag);
                reGame();
            }
        }

    }

    public void init() {

        Image GenBI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/GenB.jpg"));
        Image GuaBI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/GuaB.jpg"));
        Image BisBI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/BisB.jpg"));
        Image HorBI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/HorB.jpg"));
        Image RooBI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/RooB.jpg"));
        Image ConBI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/CanB.jpg"));
        Image PawBI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/PawB.jpg"));
        Image GenRI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/GenR.jpg"));
        Image GuaRI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/GuaR.jpg"));
        Image BisRI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/BisR.jpg"));
        Image HorRI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/HorR.jpg"));
        Image RooRI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/RooR.jpg"));
        Image ConRI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/CanR.jpg"));
        Image PawRI = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/chess_image/PawR.jpg"));

        Im.add(GenBI);
        Im.add(GuaBI);
        Im.add(BisBI);
        Im.add(HorBI);
        Im.add(RooBI);
        Im.add(ConBI);
        Im.add(PawBI);
        Im.add(GenRI);
        Im.add(GuaRI);
        Im.add(BisRI);
        Im.add(HorRI);
        Im.add(RooRI);
        Im.add(ConRI);
        Im.add(PawRI);
    }

    public final Font f = new Font("楷体", Font.BOLD, 70);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int uWidth = uSize.width;
        int uHeight = uSize.height;

        int y = org.x;
        int x = org.y;
        int i, j;

//		System.out.println("dfas");
        g2.drawString("楚河", 2 * uWidth, 475);
        g2.drawString("汉界", 7 * uWidth - 70, 475);
        setFont(f);
        g.drawLine(x + 4 * uWidth, y + uHeight, x + 6 * uWidth, y + 3 * uHeight);
        g.drawLine(x + 6 * uWidth, y + uHeight, x + 4 * uWidth, y + 3 * uHeight);
        g.drawLine(x + 4 * uWidth, y + 9 * uHeight, x + 6 * uWidth, y + 11 * uHeight);
        g.drawLine(x + 6 * uWidth, y + 9 * uHeight, x + 4 * uWidth, y + 11 * uHeight);

        // 画横线
        for (i = 0; i <= rows; i++) {
            if (i == 6 || i == 0 || i == rows)
                continue;
            g.drawLine(x + uWidth, y + i * uHeight, x + width - uWidth, y + i * uHeight);
        }
        // 画竖线
        for (i = 0; i <= cols; i++) {
            if (i == 0 || i == cols)
                continue;
            g.drawLine(x + i * uWidth, y + uHeight, x + i * uWidth, y + Param.HALF_HEIGHT);
            g.drawLine(x + i * uWidth, y + Param.HALF_HEIGHT + 2 * uHeight, x + i * uWidth, y + height - uHeight);
        }

        for (MyPoint mp : lpp) {
            if (mp.y >= 5)
                g.drawImage(Im.get(mp.value), x + uPoint.x + mp.x * uWidth, y + uPoint.y + (mp.y + 1) * uHeight, dia,
                        dia, (ImageObserver) this);
            else
                g.drawImage(Im.get(mp.value), x + uPoint.x + mp.x * uWidth, y + uPoint.y + mp.y * uHeight, dia, dia,
                        (ImageObserver) this);
        }

    }

    public void drawSelect(Graphics g, MyPoint p) {
        g.drawRect(p.y, p.x, 60, 60);
    }

    public MyPoint toPoint(MouseEvent e) {
        MyPoint p = toNum(e);
        int yy = Param.REAL_ORG.x + uPoint.x + p.y * uSize.width;
        int xx = Param.REAL_ORG.y + uPoint.y + p.x * uSize.height;
        return new MyPoint(xx, yy);
    }

    public MyPoint toPoint(MyPoint p) {
        int yy = Param.REAL_ORG.x + uPoint.x + p.y * uSize.width;
        int xx = Param.REAL_ORG.y + uPoint.y + p.x * uSize.height;
        return new MyPoint(xx, yy);
    }

    public MyPoint toNum(MouseEvent e) {
        return new MyPoint((e.getY() - 50 - Param.RADIUS) / (uSize.height),
                (e.getX() - 20 - Param.RADIUS) / (uSize.width));
    }

    public MyPoint toNum(MyPoint p) {
        int x = (p.x - Param.REAL_ORG.y - uPoint.y) / uSize.height;
        int y = (p.y - Param.REAL_ORG.x - uPoint.x) / uSize.width + 1;
        x = x < 5 ? x + 1 : x;
        return new MyPoint(x, y);
    }

    public int toRow(MyPoint p) {
        return (p.x - Param.REAL_ORG.y - uPoint.y) / uSize.height;
    }

    public String getWinner(int v) {
        if (v == 1) {
            return "红方获胜！";
        } else if (v == 2) {
            return "黑方获胜！";
        }
        return null;
    }

    public void getWinnerRatio(int v) {
        if (v == 1) {
            vr++;
        } else if (v == 2) {
            vb++;
        }
        jl.setText("红方获胜次数：" + vr + "黑方获胜次数：" + vb + " ");
    }

    public void reGame() {
        cb.reGame();
        lpp = cb.outCB();
        repaint();
    }

    @Override
    public void run() {
        move(tn);
    }
}

class cpMenu extends JMenu {

    public cpMenu(ChessBoardPanel cbp, String arg0) {
        super(arg0);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("dfas");
                cbp.reGame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

        });
    }
}

class cpButton extends JButton {
    public cpButton(ChessBoardPanel cbp, String text) {
        super(text);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//				System.out.println("dfas");
                cbp.reGame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

        });
    }
}