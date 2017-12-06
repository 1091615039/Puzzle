package lc.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel jUserName = new JLabel("用户名:");
	private JLabel jPassword = new JLabel(" 密码 :");
	private JTextField tUserName = new JTextField("Liuc");
	private JPasswordField tPassword = new JPasswordField("1234");
	private JButton jloginbut = new JButton("登陆");
	private JButton jexitbut = new JButton("退出");

	public LoginFrame() {
		setTitle("登录窗口");
		setSize(240, 180);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		jUserName.setBounds(30, 20, 60, 20);
		add(jUserName);
		tUserName.setBounds(90, 20, 120, 20);
		add(tUserName);
		jPassword.setBounds(30, 40, 60, 60);
		add(jPassword);
		tPassword.setBounds(90, 60, 120, 20);
		add(tPassword);
		jloginbut.setBounds(40, 100, 60, 25);
		jloginbut.addActionListener(this);
		add(jloginbut);
		jexitbut.setBounds(130, 100, 60, 25);
		jexitbut.addActionListener(this);
		add(jexitbut);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jloginbut) {
			if (tUserName.getText().equals("Liuc") && new String(tPassword.getPassword()).equals("1234")) {
				dispose();
				GameFrame gameframe = new GameFrame();
				gameframe.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == jexitbut) {
			System.exit(0);
		}
	}
}
