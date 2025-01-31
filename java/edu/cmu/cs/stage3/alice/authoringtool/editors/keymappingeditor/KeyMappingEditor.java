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

package edu.cmu.cs.stage3.alice.authoringtool.editors.keymappingeditor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Stage3
 * @author
 * @version 1.0
 */

public class KeyMappingEditor extends javax.swing.JPanel implements edu.cmu.cs.stage3.alice.authoringtool.Editor {
	public static String viewerName = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("KeyMapping_Editor");

	private edu.cmu.cs.stage3.alice.core.navigation.KeyMapping keyMap;
    private int[] reverseKeyMap;

	public KeyMappingEditor() {
		jbInit();  // this is JBuilder's autogenerated method
	}



    public void setAuthoringTool(edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool at) {
    }

	public void setObject( edu.cmu.cs.stage3.alice.core.navigation.KeyMapping element ) {
		// always remember to clean up before editing a new Element

        reverseKeyMap = new int[18];
		if( this.keyMap != null ) {
		}

		this.keyMap = element;

		if( element == null ) {
		} else {
            loadKeyMap();
		}
        updateFields();
	}

	public Object getObject() {
		return keyMap;
	}

	public javax.swing.JComponent getJComponent() {
		return this;
	}

    private void loadKeyMap () {
        for (int i=0; i<keyMap.keyFunction.size(); i++)
            if (keyMap.keyFunction.getIntArrayValue()[i]!=0)
                if (keyMap.keyFunction.getIntArrayValue()[i]==edu.cmu.cs.stage3.alice.core.navigation.KeyMapping.NAV_STRAFE_MODIFIER)
                    reverseKeyMap[17]=i;
                else
                    reverseKeyMap[(int)(Math.log((double)keyMap.keyFunction.getIntArrayValue()[i])/Math.log(2))]=i;
    }

    private void saveKeyMap () {
        if (keyMap == null) return;

        keyMap.keyFunction.set(new int[java.awt.event.KeyEvent.KEY_LAST]);
        for (int i=0; i<17; i++)
            if (reverseKeyMap[i]!=0)
                keyMap.setFunction(reverseKeyMap[i],1<<i);
        if (reverseKeyMap[17]!=0)
            keyMap.setFunction(reverseKeyMap[17],edu.cmu.cs.stage3.alice.core.navigation.KeyMapping.NAV_STRAFE_MODIFIER);
    }

    private void updateFields() {
        if (reverseKeyMap[0]==0)
            MF_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            MF_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[0]));
        if (reverseKeyMap[1]==0)
            MB_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            MB_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[1]));
        if (reverseKeyMap[2]==0)
            ML_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            ML_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[2]));
        if (reverseKeyMap[3]==0)
            MR_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            MR_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[3]));
        if (reverseKeyMap[4]==0)
            MU_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            MU_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[4]));
        if (reverseKeyMap[5]==0)
            MD_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            MD_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[5]));
        if (reverseKeyMap[6]==0)
            TL_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            TL_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[6]));
        if (reverseKeyMap[7]==0)
            TR_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            TR_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[7]));
        if (reverseKeyMap[8]==0)
            TU_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            TU_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[8]));
        if (reverseKeyMap[9]==0)
            TD_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            TD_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[9]));
        if (reverseKeyMap[10]==0)
            RL_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            RL_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[10]));
        if (reverseKeyMap[11]==0)
            RR_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            RR_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[11]));
        if (reverseKeyMap[16]==0)
            HU_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            HU_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[16]));
        if (reverseKeyMap[17]==0)
            SM_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        else
            SM_key.setText(java.awt.event.KeyEvent.getKeyText(reverseKeyMap[17]));

    }

    private void setKey(int action, int key) {
        for (int i=0; i<18; i++)
            if (reverseKeyMap[i]==key)
                reverseKeyMap[i]=0;
        reverseKeyMap[action]=key;
        saveKeyMap();
        updateFields();
    }

	///////////////////////////////////////////////
	// AuthoringToolStateListener interface
	///////////////////////////////////////////////

	// these methods are used by the Authoringtool to communicate important events

	public void stateChanging( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldLoading( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldUnLoading( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldStarting( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldStopping( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldPausing( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldSaving( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void stateChanged( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldLoaded( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldUnLoaded( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldStarted( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldStopped( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldPaused( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}
	public void worldSaved( edu.cmu.cs.stage3.alice.authoringtool.event.AuthoringToolStateChangedEvent ev ) {}

    // autogenerated by JBuilder
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel MF_label = new JLabel();
    TitledBorder titledBorder1;
    JPanel jPanel1 = new JPanel();
    GridLayout gridLayout2 = new GridLayout();
    BorderLayout borderLayout1 = new BorderLayout();
    JButton MF_key = new JButton();
    JLabel MB_label = new JLabel();
    JButton MB_key = new JButton();
    JLabel ML_label = new JLabel();
    JButton ML_key = new JButton();
    JLabel MR_label = new JLabel();
    JButton MR_key = new JButton();
    JLabel MU_label = new JLabel();
    JButton MU_key = new JButton();
    JLabel MD_label = new JLabel();
    JButton MD_key = new JButton();
    JLabel TL_label = new JLabel();
    JButton TL_key = new JButton();
    JLabel TR_label = new JLabel();
    JButton TR_key = new JButton();
    JLabel TU_label = new JLabel();
    JButton TU_key = new JButton();
    JLabel TD_label = new JLabel();
    JButton TD_key = new JButton();
    JLabel RL_label = new JLabel();
    JButton RL_key = new JButton();
    JLabel RR_label = new JLabel();
    JButton RR_key = new JButton();
    JLabel HU_label = new JLabel();
    JButton HU_key = new JButton();
    JLabel SM_label = new JLabel();
    JButton SM_key = new JButton();
    private void jbInit() {
        titledBorder1 = new TitledBorder("");
        jLabel1.setBorder(BorderFactory.createEtchedBorder());
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Action"));
        jLabel2.setBorder(BorderFactory.createEtchedBorder());
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Key"));
        MF_label.setForeground(Color.blue);
        MF_label.setHorizontalAlignment(SwingConstants.CENTER);
        MF_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Move_Forward"));
        jPanel1.setLayout(gridLayout2);
        gridLayout2.setRows(19);
        gridLayout2.setColumns(2);
        this.setLayout(borderLayout1);
        borderLayout1.setHgap(10);
        borderLayout1.setVgap(10);
        MF_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        MF_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,0);
            }
        });
        MB_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Move_Backward"));
        MB_label.setHorizontalAlignment(SwingConstants.CENTER);
        MB_label.setForeground(Color.blue);
        MB_label.setToolTipText("");
        MB_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        MB_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,1);
            }
        });
        ML_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,2);
            }
        });
        MR_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,3);
            }
        });
        MU_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,4);
            }
        });
        MD_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,5);
            }
        });
        TL_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,6);
            }
        });
        TR_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,7);
            }
        });
        TU_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,8);
            }
        });
        TD_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,9);
            }
        });
        RL_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,10);
            }
        });
        RR_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,11);
            }
        });
        HU_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,16);
            }
        });

        TL_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Turn_Left"));
        TL_label.setHorizontalAlignment(SwingConstants.CENTER);
        TL_label.setForeground(Color.blue);
        TL_label.setToolTipText("");
        TL_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        TR_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Turn_Right"));
        TR_label.setHorizontalAlignment(SwingConstants.CENTER);
        TR_label.setForeground(Color.blue);
        TR_label.setToolTipText("");
        TD_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        TD_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Turn_Forward"));
        TD_label.setHorizontalAlignment(SwingConstants.CENTER);
        TD_label.setForeground(Color.blue);
        TD_label.setToolTipText("");
        TD_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        TU_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Turn_Backward"));
        TU_label.setHorizontalAlignment(SwingConstants.CENTER);
        TU_label.setForeground(Color.blue);
        TU_label.setToolTipText("");
        TU_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        ML_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Move_Left"));
        ML_label.setHorizontalAlignment(SwingConstants.CENTER);
        ML_label.setForeground(Color.blue);
        ML_label.setToolTipText("");
        ML_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        MR_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Move_Right"));
        MR_label.setHorizontalAlignment(SwingConstants.CENTER);
        MR_label.setForeground(Color.blue);
        MR_label.setToolTipText("");
        MR_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        MU_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Move_Up"));
        MU_label.setHorizontalAlignment(SwingConstants.CENTER);
        MU_label.setForeground(Color.blue);
        MU_label.setToolTipText("");
        MU_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        MD_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Move_Down"));
        MD_label.setHorizontalAlignment(SwingConstants.CENTER);
        MD_label.setForeground(Color.blue);
        MD_label.setToolTipText("");
        MD_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));





        HU_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Stand_Up"));
        HU_label.setHorizontalAlignment(SwingConstants.CENTER);
        HU_label.setForeground(Color.blue);
        HU_label.setToolTipText("");
        HU_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        RL_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Roll_Left"));
        RL_label.setHorizontalAlignment(SwingConstants.CENTER);
        RL_label.setForeground(Color.blue);
        RL_label.setToolTipText("");
        RL_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        RR_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Roll_Right"));
        RR_label.setHorizontalAlignment(SwingConstants.CENTER);
        RR_label.setForeground(Color.blue);
        RR_label.setToolTipText("");
        RR_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));

        jPanel1.setPreferredSize(new Dimension(294, 430));
        this.setMinimumSize(new Dimension(294, 500));
        this.setPreferredSize(new Dimension(294, 500));
        SM_label.setToolTipText("");
        SM_label.setForeground(Color.blue);
        SM_label.setHorizontalAlignment(SwingConstants.CENTER);
        SM_label.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Strafe_Modifier"));
        SM_key.setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("<NONE>"));
        SM_key.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                key_mouseClicked(e,17);
            }
        });
        this.add(jPanel1, BorderLayout.NORTH);
        jPanel1.add(jLabel1, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(MF_label, null);
        jPanel1.add(MF_key, null);
        jPanel1.add(MB_label, null);
        jPanel1.add(MB_key, null);
        jPanel1.add(TL_label, null);
        jPanel1.add(TL_key, null);
        jPanel1.add(TR_label, null);
        jPanel1.add(TR_key, null);
        jPanel1.add(TD_label, null);
        jPanel1.add(TD_key, null);
        jPanel1.add(TU_label, null);
        jPanel1.add(TU_key, null);
        jPanel1.add(ML_label, null);
        jPanel1.add(ML_key, null);
        jPanel1.add(MR_label, null);
        jPanel1.add(MR_key, null);
        jPanel1.add(MU_label, null);
        jPanel1.add(MU_key, null);
        jPanel1.add(MD_label, null);
        jPanel1.add(MD_key, null);
        jPanel1.add(HU_label, null);
        jPanel1.add(HU_key, null);
        jPanel1.add(RL_label, null);
        jPanel1.add(RL_key, null);
        jPanel1.add(RR_label, null);
        jPanel1.add(RR_key, null);
        jPanel1.add(SM_label, null);
        jPanel1.add(SM_key, null);
    }

    void key_mouseClicked(final MouseEvent e, final int action) {
        ((JButton)e.getSource()).setText(java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/editors/keymappingeditor/KeyMappingEditor").getString("Press_a_key..."));
        ((JButton)e.getSource()).addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
                    setKey(action,0);
                else
                    setKey(action,ke.getKeyCode());
                //System.out.println(ke.getKeyCode());
                ((JButton)e.getSource()).removeKeyListener( this );
            }
        });
        ((JButton)e.getSource()).addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(FocusEvent fe) {
                updateFields();
                ((JButton)e.getSource()).removeFocusListener( this );
            }
        });

    }
    void SM_key_mouseClicked(MouseEvent e) {

    }
    void SM_key_mousePressed(MouseEvent e) {

    }
    void SM_key_mouseReleased(MouseEvent e) {

    }
    void SM_key_mouseEntered(MouseEvent e) {

    }
    void SM_key_mouseExited(MouseEvent e) {

    }
}