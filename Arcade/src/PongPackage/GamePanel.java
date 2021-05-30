package PongPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, IGame
{
	private static final long serialVersionUID = 1L;
	
	static final int WIDTH = 1000;
	static final int HEIGHT = (int)(WIDTH * (0.5555555));
	static final float BALLSIZE = 0.5f;
	static final Vector2 PADDLESIZE = new Vector2(0.5f, 2.0f);
	static final Color BACKGROUNDCOLOR = new Color(10 , 70 , 45);
	static final int MAXSCORE = 5;
	
	static final Color green = new Color(46, 204, 113);
	static final Color blue = new Color(124, 217, 206);
	static final Color orange = new Color(243, 111, 56);
	
	static final Dimension dimension = new Dimension(WIDTH, HEIGHT);

	Thread thread;
	Random random;
	JPanel startPanel;

	Paddle player;
	Paddle computer;
	Ball ball;
	Score score;
	ArrayList<GameObject> gameObjects;
	
	PausePanel pausePanel;
	GameOverPanel gameOverPanel;
	
	double timeScale = Time.getTimeScale();
	boolean randomColor = false;
	boolean isPaused = false;
	boolean wasPainted = false;
	boolean isRunning;
	
	public GamePanel(JPanel startPanel, JFrame frame)
	{
		Screen.updateScreen(dimension);

		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(dimension);
		this.setSize(dimension);
		this.setBackground(BACKGROUNDCOLOR);
		this.setLayout(null);
		this.addComponentListener(new CL());
		
		this.startPanel = startPanel;
		this.isRunning = true;
		
		pausePanel = new PausePanel(dimension, this, frame);
		pausePanel.setVisible(false);
		pausePanel.setSize(dimension);
		this.add(pausePanel);

		gameOverPanel = new GameOverPanel(dimension, this, frame);
		gameOverPanel.setVisible(false);
		gameOverPanel.setSize(dimension);
		this.add(gameOverPanel);
		
		initGame();
		
		thread = new Thread(this);
		thread.start();
	}

	public void stop()
	{
		isRunning = false;
	}

	
	private void initGame()
	{
		gameObjects = new ArrayList<>();
		random = new Random();
		player = new Paddle(-8.9f, 0f, PADDLESIZE, green, KeyEvent.VK_W, KeyEvent.VK_S);
		computer = new Paddle(8.9f, 0f, PADDLESIZE, blue);
		ball = new Ball(0, 0, BALLSIZE, orange);
		score = new Score(WIDTH, HEIGHT);

		gameObjects.add(ball);
		gameObjects.add(player);
		gameObjects.add(computer);
	}
	
	public void pauseGame()
	{
		GamePanel.this.timeScale = Time.getTimeScale();
		Time.setTimeScale(0.0f);
		pausePanel.setVisible(true);
		isPaused = true;
	}
	
	public void openStartMenu()
	{
		startPanel.setVisible(true);
		setVisible(false);
		isRunning = false;
	}
	
	public void resumeGame()
	{
		Time.setTimeScale(timeScale);
		isPaused = false;
	}

	@Override
	public void draw(Graphics g)
	{
		for(GameObject gameObject : gameObjects)
		{
			gameObject.draw(g);
		}

		score.draw(g);
		wasPainted = true;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		draw(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void move()
	{
		if(ball.position.x > -4.5f &&
				ball.velocity.x > 0 )
		{
			moveComputer();
		}

		for(GameObject gameObject : gameObjects)
		{
			gameObject.move();
		}
	}
	
	public void moveComputer()
	{
		if(ball.position.y != computer.position.y)
		{
			float dir = ball.position.y - computer.position.y;
			dir /= Math.abs(dir);
			computer.changeYVelocity(dir * 4f);
		}
	}
	
	public void checkCollision()
	{
		for(GameObject gameObject : gameObjects)
		{
			gameObject.checkCollision();
		}

		float ballSize = ball.getSize();
		
		if(ball.velocity.x < 0)
		{
			if(ball.position.x - ballSize / 2 < player.position.x + player.size.x / 2)
			{
				if(ball.position.y - ballSize / 2 < player.position.y + player.size.y / 2
					&& ball.position.y + ballSize / 2 > player.position.y - player.size.y / 2)
				{
					ball.velocity.x *= -1;
					ball.move();
					
					if(randomColor)
					{
						changeBallColor();
					}
				}
			}
		}
		
		if(ball.velocity.x > 0)
		{
			if(ball.position.x + ballSize / 2 > computer.position.x - computer.size.x / 2)
			{
				if(ball.position.y < computer.position.y + computer.size.y / 2
					&& ball.position.y > computer.position.y - computer.size.y / 2)
				{
					ball.velocity.x *= -1;
					computer.changeYVelocity(0);
					
					if(randomColor)
					{
						changeBallColor();
					}
				}
			}
		}

		if(ball.position.x > computer.position.x)
		{
			score.addPoint(1);

			if(score.getPoint(1) >= MAXSCORE)
			{
				isRunning = false;
				gameOverPanel.setVisible(true);
				gameOverPanel.resizeUI();
				gameOverPanel.setWinner("Player1", score.getPoint(1), score.getPoint(2));
				Time.setTimeScale(0);
			}

			resetBall();
			return;
		}
		if(ball.position.x < player.position.x)
		{
			score.addPoint(2);

			if(score.getPoint(2) >= MAXSCORE)
			{
				isRunning = false;
				gameOverPanel.setVisible(true);
				gameOverPanel.resizeUI();
				gameOverPanel.setWinner("Player2", score.getPoint(2), score.getPoint(1));
				Time.setTimeScale(0);
			}

			resetBall();
			return;
		}
	}
	
	public void resetBall()
	{
		ball.position.Set(0, 0);
		ball.velocity = new Vector2((int)Math.pow(-1, random.nextInt(2)),
				(int)Math.pow(-1, random.nextInt(2)));
		
		if(randomColor)
		{
			changeBallColor();
		}
	}
	
	public void changeBallColor()
	{	
		float r = random.nextFloat();
		float b = random.nextFloat();
		float g = random.nextFloat();
		
		ball.setColor(new Color(r,b,g));
	}

	public void gameLoop()
	{
		Screen.updateScreen(getSize());
		int fps = 0;
		int ticks = 0;
		double delta = 0;
		double maxFps = 0;
		double ns;

		if(maxFps > 0)
		{
			ns = 1 / maxFps;
		}
		else
		{
			ns = 0;
		}
		double timeToUpdate = 0;

		while(isRunning)
		{
			Time.updateTime();

			delta += Time.getRealDelta();
			timeToUpdate += Time.getRealDelta();

			if(timeToUpdate > ns)
			{
				if(Time.getTimeScale() > 0)
				{
					update();
					Time.updateWasDone();
				}
				timeToUpdate = 0.0;

				++ticks;

				if(wasPainted)
				{
					++fps;
					wasPainted = false;
				}
			}

			if(delta >= 1f)
			{
				delta = 0f;
				System.out.print("FPS: " + fps);
				System.out.println(" Ticks: " + ticks);
				fps = 0;
				ticks = 0;
			}

		}
	}
	
	public void run()
	{
		gameLoop();
	}
	
	public void update()
	{
		move();
		checkCollision();
		repaint();
	}
	
	public void UpdateGameInfo()
	{
		Dimension size = getSize();
		Screen.updateScreen(size);
		score.setSize(size);
		pausePanel.setSize(size);
	}
	
	public class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e) 
		{
			player.keyPressed(e);
			//computer.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) 
		{
			player.keyReleased(e);
			//computer.keyReleased(e);
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !isPaused)
			{
				GamePanel.this.pauseGame();
			}
		}
	}
	
	public class CL implements ComponentListener
	{

		@Override
		public void componentHidden(ComponentEvent e) 
		{
			GamePanel.this.timeScale = Time.getTimeScale();
			Time.setTimeScale(0f);
		}

		@Override
		public void componentMoved(ComponentEvent e) 
		{
			
		}

		@Override
		public void componentResized(ComponentEvent e) 
		{
			GamePanel.this.UpdateGameInfo();
			GamePanel.this.pausePanel.resizeUI();
		}

		@Override
		public void componentShown(ComponentEvent e) 
		{
			Time.setTimeScale(GamePanel.this.timeScale);
		}
		
	}
}

