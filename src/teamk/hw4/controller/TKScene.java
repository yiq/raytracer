package teamk.hw4.controller;

import java.awt.event.*;

import javax.media.opengl.*;


/**
 * A OpenGL scene for the GasketDriver
 * 
 * Objects of the descendants of this class should contains everything 
 * required to render an OpenGL scene at the request of a TKGasketDriver 
 * object. Also, a scene will handle it's own keyboard events.
 * 
 * @author Yi Qiao
 *
 */
public abstract class TKScene implements KeyListener {
	
	/**
	 * To render the scene
	 * 
	 * @param gl The GL2 context to use when rendering the scene
	 */
	public abstract void render(GL2 gl);
	
	/**
	 * To update the scene states
	 * 
	 * @param timeElapsed Time elapsed since last updateAnimation request, in milliseconds
	 */
	public abstract void updateAnimation(long timeElapsed);

	
	// YQScene implements awt.event.KeyListener, which allows the scene itself
	// to process key events. The YQGasketDriver class will automatically
	// register the scene as a KeyEventListener to the displaying window.
	// Subclasses of YQScene should override the default event handlers, process
	// any events they are interested in, and pass any unprocessed events up
	// the responder chain. 
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Default implementation: Do Nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Default implementation: Do Nothing
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Default implementation: Do Nothing		
	}
}
