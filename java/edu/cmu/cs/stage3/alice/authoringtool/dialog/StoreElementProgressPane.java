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

package edu.cmu.cs.stage3.alice.authoringtool.dialog;

public class StoreElementProgressPane extends edu.cmu.cs.stage3.progress.ProgressPane {
	private edu.cmu.cs.stage3.alice.core.Element m_element;
	private java.io.File m_file;
	private java.util.Dictionary m_filnameToByteArrayMap;
	private boolean m_wasSuccessful = false;
	public StoreElementProgressPane( String title, String preDescription ) {
		super( title, preDescription );
	}
	
	public boolean wasSuccessful() {
		return m_wasSuccessful;
	}
	protected void construct() throws edu.cmu.cs.stage3.progress.ProgressCancelException {
		m_wasSuccessful = false;
		try {
			m_element.store( m_file, this, m_filnameToByteArrayMap );
			m_wasSuccessful = true;
		} catch( edu.cmu.cs.stage3.progress.ProgressCancelException pce ) {
			throw pce;
		} catch( Throwable t ) {
			StringBuffer sb = new StringBuffer();
			sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("An_error_has_occurred_while_attempting_to_save_your_world.\n") );
			sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("This_is_a_critical_situation_that_needs_to_be_dealt_with_immediately.\n\n") );
			if( t instanceof java.io.IOException ) {
				sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("This_may_be_the_result_of_not_having_enough_space_on_the_target_drive.\n") );
				sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("If_possible,\n____attempt_to_save_your_world_to_a_different_drive,_or\n____free_up_some_space_and_\"Save_As\"_to_a_different_file.\n\n") );
				sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("NOTE:_If_unsuccessful,_please") );
			} else {
				sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("NOTE:_Please") );
			}
			sb.append( " "+java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("_check_for_a_directory_co-located_with_\nyour_world_named_\"Backups_of_<YourWorldNameHere>\"_which_\nshould_contain_previously_saved_versions_of_your_world.\n") );

			sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("\nWe_at_the_Alice_Team_apologize_for_any_work_you_have_lost_and_\nimplore_you_to_PLEASE_SUBMIT_THIS_BUG_REPORT.\n") );
			sb.append( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/authoringtool/dialog/StoreElementProgressPane").getString("\nPlease_accept_our_sincerest_apologies.__The_Alice_Team.") );
			edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool.showErrorDialog( sb.toString(), t );
		}
	}
	public void setElement( edu.cmu.cs.stage3.alice.core.Element element ) {
		m_element = element;
	}
	public void setFile( java.io.File file ) {
		m_file = file;
	}
	public void setFilnameToByteArrayMap( java.util.Dictionary filnameToByteArrayMap ) {
		m_filnameToByteArrayMap = filnameToByteArrayMap;
	}
}
