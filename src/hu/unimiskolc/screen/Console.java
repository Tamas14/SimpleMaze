package hu.unimiskolc.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import hu.unimiskolc.game.SimpleMaze;
import hu.unimiskolc.object.Player.Direction;

public class Console extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JTextArea console;
	private final int rows = 24;
	private final int columns = 72;

	private String[] lines;

	public Console()
	{
		setIconImages(Arrays.asList(Main.getImages()));
		setTitle("Simple Maze");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					case 'W':
					{
						SimpleMaze.getPlayer().move(SimpleMaze.getGs(), Direction.UP);
						break;
					}
					case 'A':
					{
						SimpleMaze.getPlayer().move(SimpleMaze.getGs(), Direction.LEFT);
						break;
					}
					case 'S':
					{
						SimpleMaze.getPlayer().move(SimpleMaze.getGs(), Direction.DOWN);
						break;
					}
					case 'D':
					{
						SimpleMaze.getPlayer().move(SimpleMaze.getGs(), Direction.RIGHT);
						break;
					}
				}

				update();
			}
		});

		lines = new String[rows];

		console = new JTextArea(rows, columns);

		console.setForeground(new Color(68, 161, 160));
		console.setBackground(new Color(25, 30, 40));
		console.setLineWrap(true);
		console.setEditable(false);

		console.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				getFocus();
			}
		});

		update();

		add(console);

		pack();
		setVisible(true);
		getFocus();
		setLocationRelativeTo(null);
	}

	public void update()
	{
		String txt = "";

		for (int i = 0; i < rows; i++)
		{
			if (lines[i] == null)
				txt += "\n";
			else
				txt += lines[i] + "\n";
		}

		txt += "Made by: Tamás Csaba";
		console.setText(txt);
	}

	public void setLine(String str, int pos)
	{
		if (pos < 0 || pos > rows)
		{
			return;
		}

		lines[pos] = str;
	}

	public void setLine(String str, int pos, boolean update)
	{
		if (pos < 0 || pos > rows)
		{
			return;
		}

		lines[pos] = str;

		if (update)
			update();
	}

	public void printStats(int ellapsedTime, int steps, int stepDiff, double speed) throws BadLocationException
	{
		int menuSize = 75;

		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);

		Dimension prefSize = console.getPreferredSize();
		prefSize.setSize(prefSize.getWidth(), prefSize.getHeight() - menuSize);

		panel.setPreferredSize(console.getPreferredSize());

		JTextPane pane = new JTextPane();
		Document document = pane.getDocument();

		pane.setFont(new Font("Impact", Font.PLAIN, 24));
		pane.setBackground(Color.BLACK);
		pane.setForeground(new Color(0, 153, 255));
		pane.setParagraphAttributes(center, false);
		pane.setEditable(false);
		pane.setFocusable(false);
		pane.setPreferredSize(prefSize);
		pane.setBounds(0, 0, (int) prefSize.getWidth(), (int) prefSize.getHeight());

		newLine(document);
		printLine(document, "Gratulálok, kijutottál az útvesztőből!");
		newLine(document);
		printLine(document, "Eltelt idő: " + ellapsedTime + " másodperc");
		newLine(document);
		printLine(document, steps + " egységet tettél meg, amely "
				+ ((stepDiff == 0) ? "a legrövidebb út" : stepDiff + " lépéssel több a legrövidebb úttól"));
		newLine(document);

		String s_Speed = new DecimalFormat("#.###").format(speed);
		printLine(document, "Átlagos sebesség: " + s_Speed + " egység/másodperc");

		JPanel buttonHolder = new JPanel();
		buttonHolder.setBackground(Color.BLACK);
		buttonHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonHolder.setBounds(0, (int) prefSize.getHeight(), (int) prefSize.getWidth(), menuSize);

		JButton but1 = new JButton("Új játék");
		Main.applySettings(but1, new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{
				SimpleMaze.getCon().dispose();
				SimpleMaze.init();
			}
		}, new Dimension(150, 65));

		JButton but2 = new JButton("Kilépés");
		Main.applySettings(but2, new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{
				System.exit(1);
			}
		}, new Dimension(150, 65));

		but1.setFont(new Font("Impact", Font.PLAIN, 22));
		but2.setFont(new Font("Impact", Font.PLAIN, 22));

		buttonHolder.add(but1);
		buttonHolder.add(but2);

		remove(console);

		panel.add(pane);
		panel.add(buttonHolder);
		add(panel);
		pack();
	}

	public void newLine(Document document) throws BadLocationException
	{
		printLine(document, "");
	}

	public void printLine(Document document, String str) throws BadLocationException
	{
		document.insertString(document.getLength(), str + "\n", null);
	}

	public void getFocus()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				requestFocusInWindow();
			}
		});
	}

	public int getColumns()
	{
		return columns;
	}

	public int getRows()
	{
		return rows;
	}

}
