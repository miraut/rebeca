package edu.cmu.cs.stage3.alice.scenegraph.renderer.nativerenderer;

public abstract class RenderCanvas extends java.awt.Canvas {
	private int m_nativeInstance;
	private int m_nativeDrawingSurfaceInfo;
    protected abstract void createNativeInstance( RenderTargetAdapter renderTargetAdapter );
    protected abstract void releaseNativeInstance();

	protected abstract boolean acquireDrawingSurface();
	protected abstract void releaseDrawingSurface();
	protected abstract void swapBuffers();

    private OnscreenRenderTarget m_onscreenRenderTarget;
    private boolean m_giveUp = false;

    protected RenderCanvas( OnscreenRenderTarget onscreenRenderTarget ) {
		m_nativeDrawingSurfaceInfo = 0;
		m_onscreenRenderTarget = onscreenRenderTarget;
        createNativeInstance( m_onscreenRenderTarget.getAdapter() );

        //todo: is there a better way to get processKeyEvent to be invoked?
        addKeyListener( new java.awt.event.KeyAdapter() {} );
   }

    protected void finalize() throws Throwable {
        releaseNativeInstance();
        super.finalize();
    }

    //todo: is there a better way to do this?
    // pass event on to parent so swing will invoke menu shortcuts
    protected void processKeyEvent( java.awt.event.KeyEvent e ) {
        super.processKeyEvent( e );
        java.awt.Container parent = getParent();
        if( parent != null ) {
            parent.dispatchEvent( e );
        }
    }

    public boolean isFocusTraversable() {
        return true;
    }
    public void update( java.awt.Graphics graphics ) {
        paint( graphics );
    }
    public void paint( java.awt.Graphics graphics ) {
    	if( m_giveUp ) {
    		//pass
    	} else {
			int width = getWidth();
			int height = getHeight();
			if( width != 0 && height != 0 ) {
				try {
					m_onscreenRenderTarget.getAdapter().setDesiredSize( width, height );
					m_onscreenRenderTarget.clearAndRenderOffscreen();
					m_onscreenRenderTarget.update();
				} catch( RuntimeException re ) {
					Object[] buttonLabels = { java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/scenegraph/renderer/nativerenderer/RenderCanvas").getString("Retry"), java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/scenegraph/renderer/nativerenderer/RenderCanvas").getString("Give_up") };
					if( edu.cmu.cs.stage3.swing.DialogManager.showOptionDialog( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/scenegraph/renderer/nativerenderer/RenderCanvas").getString("A_error_has_occured_in_attempting_to_draw_the_scene.__Simply_retrying_might_fix_the_problem."), java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/scenegraph/renderer/nativerenderer/RenderCanvas").getString("Potential_Problem"), javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE, null, buttonLabels, buttonLabels[ 0 ] ) == javax.swing.JOptionPane.YES_OPTION ) {
						m_onscreenRenderTarget.getAdapter().reset();
						repaint();
					} else {
						m_giveUp = true;
						throw re;
					}
				}
			} else {
				repaint();
			}
    	}
    }
    public java.awt.Dimension getMinimumSize() {
        return new java.awt.Dimension( 0, 0 );
    }
}
