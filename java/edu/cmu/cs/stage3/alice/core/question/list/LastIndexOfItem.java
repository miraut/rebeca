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

package edu.cmu.cs.stage3.alice.core.question.list;

import edu.cmu.cs.stage3.alice.core.property.NumberProperty;
import edu.cmu.cs.stage3.alice.core.property.ObjectProperty;

public class LastIndexOfItem extends ListNumberQuestion {
	public final ObjectProperty item = new ObjectProperty( this, "item", null, Object.class );
	public final NumberProperty startFromIndex = new NumberProperty( this, "startFromIndex", null );
	private static Class[] s_supportedCoercionClasses = { FirstIndexOfItem.class };
	public Class[] getSupportedCoercionClasses() {
		return s_supportedCoercionClasses;
	}
	protected int getValue( edu.cmu.cs.stage3.alice.core.List listValue ) {
		int index = startFromIndex.intValue( listValue.size()-1 );
        return listValue.lastIndexOfItemValue( item.getValue(), index );
	}
}