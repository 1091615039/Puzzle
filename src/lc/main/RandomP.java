package lc.main;
import java.util.ArrayList;
import java.util.Random;
/*
 * 随机化图片类
 */
public class RandomP {
	/*
	 * 初始化四个方向
	 */
	public static final int TOP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private int notvalue = -1;

	private Random r = new Random();
	private int rows = 0, cols = 0;
	private ArrayList<Integer> list = new ArrayList<Integer>();

	public RandomP() {
		this(3, 3);
	}

	public RandomP(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}
	/*
	 * 随机打乱一步
	 */
	public int next(int x, int y) {
		/*
		 * 判断下一步可移动方向
		 */
		if (x == 0 && y == 0) {
			list.add(DOWN);
			list.add(RIGHT);
		} else if (x == 0 && y == cols - 1) {
			list.add(DOWN);
			list.add(LEFT);
		} else if (x == rows - 1 && y == 0) {
			list.add(TOP);
			list.add(RIGHT);
		} else if (x == rows - 1 && y == cols - 1) {
			list.add(TOP);
			list.add(LEFT);
		} else if (x == 0) {
			list.add(DOWN);
			list.add(LEFT);
			list.add(RIGHT);
		} else if (cols - 1 == x) {
			list.add(TOP);
			list.add(LEFT);
			list.add(RIGHT);
		} else if (y == 0) {
			list.add(TOP);
			list.add(DOWN);
			list.add(RIGHT);
		} else if (rows - 1 == y) {
			list.add(TOP);
			list.add(DOWN);
			list.add(LEFT);
		} else {
			list.add(TOP);
			list.add(DOWN);
			list.add(LEFT);
			list.add(RIGHT);
		}
		if (notvalue != -1) {
			list.remove(new Integer(notvalue));
		}
		int result = list.get(r.nextInt(list.size()));
		/*
		 * 保证打乱一步之后 下一步不回到原位置
		 */
		switch (result) {
		case TOP:
			notvalue = DOWN;
			break;
		case DOWN:
			notvalue = TOP;
			break;
		case LEFT:
			notvalue = RIGHT;
			break;
		case RIGHT:
			notvalue = LEFT;
			break;
		}
		list.clear();
		return result;
	}
}
