package testWB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("2048 Game");
        Board game = new Board();
        frame.getContentPane().add(game, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicia el JFrame maximizado
        frame.setSize(800, 800); // Tamaño del frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // ComponentListener para detectar cambios en el tamaño del frame
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int height = frame.getContentPane().getHeight();
                int width = frame.getContentPane().getWidth();

                if (height > width) { // Depende de si es más ancho que alto distribuye distinto los tamaños. El 4 es el ancho del tablero
                    Board.TILE_SIZE = (width - 10) / 4;
                } else {
                	Board.TILE_SIZE = (height - 10) / 4;
                }
                Board.BORDER_LEFT = (width / 2) - (Board.TILE_SIZE * 2);
                game.repaint(); // Vuelve a pintar el tablero cuando cambia el tamaño
            }
        });
	}

}
