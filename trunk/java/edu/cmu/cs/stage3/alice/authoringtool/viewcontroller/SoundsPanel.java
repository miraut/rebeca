/*
 * Copyright (c) 1999-2003, Carnegie Mellon University. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * 3. Products derived from the software may not be called "Alice",
 *    nor may "Alice" appear in their name, without prior written
 *    permission of Carnegie Mellon University.
 * 
 * 4. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    "This product includes software developed by Carnegie Mellon University"
 */

package edu.cmu.cs.stage3.alice.authoringtool.viewcontroller;

/**
 * @author Jason Pratt
 */
public class SoundsPanel extends edu.cmu.cs.stage3.alice.authoringtool.util.ExpandablePanel {
	protected edu.cmu.cs.stage3.alice.core.property.ObjectArrayProperty sounds;
	protected javax.swing.JPanel contentPanel = new javax.swing.JPanel();
	protected java.util.HashMap soundGuiCache = new java.util.HashMap();
	protected javax.swing.JButton importSoundButton = new javax.swing.JButton( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/viewcontroller/SoundsPanel").getString("import_sound") );
	protected javax.swing.JButton recordSoundButton = new javax.swing.JButton( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/viewcontroller/SoundsPanel").getString("record_sound") );
	protected edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool authoringTool;
	protected RefreshListener refreshListener = new RefreshListener();

	public SoundsPanel( edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool authoringTool ) {
		this.authoringTool = authoringTool;
		guiInit();
	}

	private void guiInit() {
		setTitle( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/viewcontroller/SoundsPanel").getString("Sounds") );
		contentPanel.setLayout( new java.awt.GridBagLayout() );
		setContent( contentPanel );
		importSoundButton.setBackground( new java.awt.Color( 240, 240, 255 ) );
		importSoundButton.setMargin( new java.awt.Insets( 2, 4, 2, 4 ) );
		importSoundButton.addActionListener(
			new java.awt.event.ActionListener() {
				public void actionPerformed( java.awt.event.ActionEvent ev ) {
					//edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool.getHack().promptUserForImportedSound( sounds.getOwner().getSandbox() );
					authoringTool.setImportFileFilter( "Sound Files" );
					authoringTool.importElement( null, SoundsPanel.this.sounds.getOwner() );
				}
			}
		);
		recordSoundButton.setBackground( new java.awt.Color( 240, 240, 255 ) );
		recordSoundButton.setMargin( new java.awt.Insets( 2, 4, 2, 4 ) );
		recordSoundButton.addActionListener(
			new java.awt.event.ActionListener() {
				public void actionPerformed( java.awt.event.ActionEvent ev ) {
					edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool.getHack().promptUserForRecordedSound( sounds.getOwner().getSandbox() );
//					edu.cmu.cs.stage3.alice.authoringtool.editors.soundeditor.SoundRecorder recorder = new edu.cmu.cs.stage3.alice.authoringtool.editors.soundeditor.SoundRecorder( sounds.getOwner().getSandbox() );
//					edu.cmu.cs.stage3.alice.authoringtool.AuthoringToolResources.centerComponentOnScreen( recorder );
//					recorder.setVisible( true );
//					edu.cmu.cs.stage3.alice.core.Sound sound = recorder.getSound();
//					if( sound != null ) {
//						sounds.getOwner().addChild( sound );
//						sounds.add( sound );
//					}
				}
			}
		);
		setOpaque( false );
		contentPanel.setOpaque( false );

		importSoundButton.setToolTipText( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/viewcontroller/SoundsPanel").getString("<html><font_face=arial_size=-1>Load_a_Sound_File_into_this_World.<p><p>You_can_play_a_sound_when_the_world_runs_by_using_an_Object's_<b>PlaySound</b>_method.</font></html>") );
		recordSoundButton.setToolTipText( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/viewcontroller/SoundsPanel").getString("<html><font_face=arial_size=-1>Record_a_Sound.<p><p>Use_a_microphone_or_play_a_sound_file_while_recording_to_capture_a_sound.<p>You_can_play_a_sound_when_the_world_runs_by_using_an_Object's_<b>PlaySound</b>_method.</font></html>") );
	}

	public void setSounds( edu.cmu.cs.stage3.alice.core.property.ObjectArrayProperty sounds ) {
		if( this.sounds != null ) {
			this.sounds.removeObjectArrayPropertyListener( refreshListener );
		}

		this.sounds = sounds;

		if( sounds != null ) {
			sounds.addObjectArrayPropertyListener( refreshListener );
		}

		refreshGUI();
	}

	public void refreshGUI() {
		contentPanel.removeAll();

		if( sounds != null ) {
			int count = 0;
			for( int i = 0; i < sounds.size(); i++ ) {
				final edu.cmu.cs.stage3.alice.core.Sound sound = (edu.cmu.cs.stage3.alice.core.Sound)sounds.get( i );
				javax.swing.JComponent gui = (javax.swing.JComponent)soundGuiCache.get( sound );
				if( gui == null ) {
					gui = edu.cmu.cs.stage3.alice.authoringtool.util.GUIFactory.getGUI( sound );
					soundGuiCache.put( sound, gui );
				}
				if( gui != null ) {
					contentPanel.add( gui, new java.awt.GridBagConstraints( 0, count++, 1, 1, 1.0, 0.0, java.awt.GridBagConstraints.WEST, java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets( 0, 2, 0, 2 ), 0, 0 ) );
				} else {
					edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool.showErrorDialog( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/viewcontroller/SoundsPanel").getString("Unable_to_create_gui_for_sound:_")+" " + sound, null );
				}
			}

			contentPanel.add( importSoundButton, new java.awt.GridBagConstraints( 0, count++, 1, 1, 1.0, 0.0, java.awt.GridBagConstraints.WEST, java.awt.GridBagConstraints.NONE, new java.awt.Insets( 4, 2, 4, 2 ), 0, 0 ) );
			contentPanel.add( recordSoundButton, new java.awt.GridBagConstraints( 0, count++, 1, 1, 1.0, 0.0, java.awt.GridBagConstraints.WEST, java.awt.GridBagConstraints.NONE, new java.awt.Insets( 4, 2, 4, 2 ), 0, 0 ) );
			java.awt.Component glue = javax.swing.Box.createGlue();
			contentPanel.add( glue, new java.awt.GridBagConstraints( 0, count++, 1, 1, 1.0, 1.0, java.awt.GridBagConstraints.WEST, java.awt.GridBagConstraints.BOTH, new java.awt.Insets( 2, 2, 2, 2 ), 0, 0 ) );
		}
		revalidate();
		repaint();
	}

	protected class RefreshListener implements edu.cmu.cs.stage3.alice.core.event.ObjectArrayPropertyListener {
		public void objectArrayPropertyChanging( edu.cmu.cs.stage3.alice.core.event.ObjectArrayPropertyEvent ev ) {}
		public void objectArrayPropertyChanged( edu.cmu.cs.stage3.alice.core.event.ObjectArrayPropertyEvent ev ) {
			SoundsPanel.this.refreshGUI();
		}
	}
}