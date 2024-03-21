package testWB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements KeyListener {
	private int[][] board; // Matriz para representar el tablero
	static int TILE_SIZE = 100; // Tamaño de cada celda del tablero
	static int BORDER_LEFT = 0; // Tamaño de cada celda del tablero

	public Board() {
		// Inicializa el tablero
		board = new int[4][4]; // Tablero de 4x4
		// Inicializa 2 valores
		randomCell();
		randomCell();
		// Agregar el KeyListener al JPanel
		addKeyListener(this);
		setFocusable(true); // Asegura que el JPanel pueda recibir eventos de teclado
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Dibuja el tablero y los números en cada celda
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// Dibuja el rectángulo de la celda
				g.setColor(Color.lightGray);
				g.fillRect(BORDER_LEFT + j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				// Dibuja el borde de la celda
				g.setColor(Color.darkGray);
				g.drawRect(BORDER_LEFT + j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				// Dibuja el número en la celda si es diferente de cero
				if (board[i][j] != 0) {
					g.setColor(Color.black);
					g.setFont(new Font("Arial", Font.BOLD, 24));
					String numberString = Integer.toString(board[i][j]);
					// Centra el número en la celda
					int x = BORDER_LEFT + j * TILE_SIZE
							+ (TILE_SIZE - g.getFontMetrics().stringWidth(numberString)) / 2;
					int y = i * TILE_SIZE + TILE_SIZE / 2 + g.getFontMetrics().getHeight() / 4;
					g.drawString(numberString, x, y);
				}
			}
		}
	}

	public void randomCell() {
		int i = (int) (Math.random() * 4); // Genera un número aleatorio entre 0 y 3 para i
		int j = (int) (Math.random() * 4); // Genera un número aleatorio entre 0 y 3 para j

		if (board[i][j] != 0) {
			randomCell(); // Si esta ocupado vuelve a llamar
		} else {
			board[i][j] = 2; // Si esta vacio le asigna el 2
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Captura la tecla presionada y ejecuta acciones según la tecla
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			moveNumbersUP();
			randomCell();
			repaint();
			break;
		case KeyEvent.VK_DOWN:
			// Acciones cuando se presiona la tecla de flecha hacia abajo
			break;
		case KeyEvent.VK_LEFT:
			// Acciones cuando se presiona la tecla de flecha hacia la izquierda
			break;
		case KeyEvent.VK_RIGHT:
			// Acciones cuando se presiona la tecla de flecha hacia la derecha
			break;
		default:
			// No hacer nada para otras teclas
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void moveNumbersUP() {
		int rowCounter = 0;
		for (int col = 0; col < 4; col++) {			//Al ser vertical nos movemos por la columna
			for (int row = 0; row < 4; row++) {	
				if (board[row][col] != 0) {		//Recorremos los 4 lugares buscando si hay distintos de 0
					if (rowCounter != row) {			//Se pone esta condicion porque si fuesen la misma posicion terminario borrando el numero por la logica usada
						board[rowCounter][col] = board[row][col];
						board[row][col] = 0;
					}
					rowCounter++;		//Independientemente de la condicion anterior, si el numero no es 0 se incrementa el contador de row
				}
			}
			rowCounter = 0;			//Reseteamos el contador cada vez que termina una columna
		}
	}
}
