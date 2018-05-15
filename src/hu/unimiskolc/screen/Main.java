package hu.unimiskolc.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hu.unimiskolc.game.SimpleMaze;

public class Main extends JFrame
{
	private static BufferedImage[] images;
	private static final long serialVersionUID = 1L;
	private static Font defFont;

	public static void main(String[] args)
	{
		new Main();
	}

	public Main()
	{
		loadImages();
		setIconImages(Arrays.asList(Main.getImages()));
		setTitle("Simple Maze");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		defFont = new Font("Impact", Font.PLAIN, 32);

		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setPreferredSize(new Dimension(480, 320));
		mainpanel.setBackground(new Color(15, 15, 15));
		mainpanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

		JLabel title = new JLabel("Simple Maze");
		title.setFont(defFont);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);

		JLabel footer = new JLabel("Made By: Tamás Csaba");
		footer.setForeground(Color.WHITE);
		footer.setFont(defFont.deriveFont(Font.TRUETYPE_FONT, 14f));

		JPanel midPanel = new JPanel();
		midPanel.setBackground(new Color(20, 20, 20));
		midPanel.setLayout(new GridBagLayout());

		JButton startGameButton = new JButton("Új játék");
		applySettings(startGameButton, new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{
				((AbstractButton) e.getComponent()).setContentAreaFilled(true);
				dispose();
				SimpleMaze.init();
			}
		}, new Dimension(200, 100));

		midPanel.add(startGameButton);

		mainpanel.add(title, BorderLayout.NORTH);
		mainpanel.add(midPanel, BorderLayout.CENTER);
		mainpanel.add(footer, BorderLayout.PAGE_END);

		add(mainpanel);
		pack();
		setVisible(true);

		setLocationRelativeTo(null);
	}

	public void loadImages()
	{
		try
		{
			images = new BufferedImage[] { ImageIO.read(getClass().getClassLoader().getResourceAsStream("icon128.png")),
					ImageIO.read(getClass().getClassLoader().getResourceAsStream("icon64.png")) };
		} catch (IOException e)
		{
			System.out.println("Smthing happened, cannot load icons.");
		}
	}

	public static void applySettings(JButton button, MouseAdapter listener, Dimension dimension)
	{
		Color hovered = new Color(51, 51, 51);
		Color normal = new Color(100, 100, 100);

		button.setPreferredSize(dimension);
		button.setFont(defFont.deriveFont(Font.TRUETYPE_FONT, 24f));
		button.setBorder(BorderFactory.createLineBorder(normal, 2, true));

		button.setFocusPainted(false);
		button.setBackground(normal);
		button.setForeground(normal);
		button.setContentAreaFilled(false);

		button.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent evt)
			{
				button.setContentAreaFilled(true);
				button.setForeground(hovered);
			}

			public void mouseExited(MouseEvent evt)
			{
				button.setContentAreaFilled(false);
				button.setForeground(normal);
			}

			public void mousePressed(MouseEvent e)
			{
				button.setContentAreaFilled(false);
			}
		});

		button.addMouseListener(listener);
	}

	public static BufferedImage[] getImages()
	{
		return images;
	}
}
