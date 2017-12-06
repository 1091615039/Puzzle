package lc.main;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGame = new JMenu();
	private JMenuItem menuItemGameStart = new JMenuItem();
	private JMenuItem menuItemGameExit = new JMenuItem();

	private JMenu menuHelp = new JMenu();
	private JMenuItem displayOriginal = new JMenuItem();

	private JMenu menuAbout = new JMenu();
	private JMenuItem menuItemAbout = new JMenuItem();

	private JButton[][] jbut = null;

	public GameFrame() {
		this(3, 3);
	}

	public GameFrame(int row, int col) {
		setTitle("拼图游戏");
		setSize(370, 370);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initMenu();

		setLayout(new GridLayout(row, col));
		jbut = new JButton[row][col];
		for (int i = 0; i < jbut.length; i++) {
			for (int j = 0; j < jbut[i].length; j++) {
				jbut[i][j] = new JButton();
				add(jbut[i][j]);
				jbut[i][j].addActionListener(this);
			}
		}
		unorderImage();
	}

	public void initMenu() {
		menuItemGameStart.setText("开始游戏");
		menuItemGameStart.addActionListener(this);
		menuItemGameExit.setText("结束游戏");
		menuItemGameExit.addActionListener(this);
		menuGame.setText("选项");
		menuGame.add(menuItemGameStart);
		menuGame.add(menuItemGameExit);

		menuHelp.setText("操作");
		displayOriginal.setText("显示结果");
		displayOriginal.addActionListener(this);
		menuHelp.add(displayOriginal);

		menuAbout.setText("关于");
		menuItemAbout.setText("About");
		menuItemAbout.addActionListener(this);
		menuAbout.add(menuItemAbout);

		menuBar.add(menuGame);
		menuBar.add(menuHelp);
		menuBar.add(menuAbout);
		setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItemGameStart) {
			unorderImage();
		} else if (e.getSource() == menuItemGameExit) {
			System.exit(0);
		} else if (e.getSource() == displayOriginal) {
			initImage();
		} else if (e.getSource() == menuItemAbout) {
			new AboutFrame().setVisible(true);
		}
		int s_x = -1, s_y = -1;
		int b_x = -1, b_y = -1;
		for (int i = 0; i < jbut.length; i++) {
			for (int j = 0; j < jbut[i].length; j++) {
				if (e.getSource() == jbut[i][j]) {
					s_x = i;
					s_y = j;
				}
				if (jbut[i][j].getIcon() == null) {
					b_x = i;
					b_y = j;
				}
			}
		}

		if ((s_y == b_y && Math.abs(s_x - b_x) == 1) || (s_x == b_x && Math.abs(s_y - b_y) == 1)) {
			jbut[b_x][b_y].setIcon(jbut[s_x][s_y].getIcon());
			jbut[s_x][s_y].setIcon(null);
		}

	}

	public void initImage() {
		for (int i = 0; i < jbut.length; i++) {
			for (int j = 0; j < jbut[i].length; j++) {
				Icon icon = new ImageIcon("imgs/" + (jbut.length * i + j + 1) + ".gif");
				jbut[i][j].setIcon(icon);
			}
		}
		jbut[jbut.length - 1][0].setIcon(null);
	}

	public void unorderImage() {
		initImage();
		int x = jbut.length - 1;
		int y = 0;
		RandomP r = new RandomP();
		for (int i = 0; i < 20 + Math.random() * 50; i++) {
			int result = r.next(x, y);
			switch (result) {
			case RandomP.TOP:
				jbut[x][y].setIcon(jbut[x - 1][y].getIcon());
				jbut[x - 1][y].setIcon(null);
				x = x - 1;
				break;
			case RandomP.DOWN:
				jbut[x][y].setIcon(jbut[x + 1][y].getIcon());
				jbut[x + 1][y].setIcon(null);
				x = x + 1;
				break;
			case RandomP.LEFT:
				jbut[x][y].setIcon(jbut[x][y - 1].getIcon());
				jbut[x][y - 1].setIcon(null);
				y = y - 1;
				break;
			case RandomP.RIGHT:
				jbut[x][y].setIcon(jbut[x][y + 1].getIcon());
				jbut[x][y + 1].setIcon(null);
				y = y + 1;
				break;
			}

		}
	}
}
