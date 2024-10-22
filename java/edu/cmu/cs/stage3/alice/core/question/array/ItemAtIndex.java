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

package edu.cmu.cs.stage3.alice.core.question.array;

import edu.cmu.cs.stage3.alice.core.property.NumberProperty;

public class ItemAtIndex extends ArrayObjectQuestion {
	public final NumberProperty index = new NumberProperty( this, "index", new Integer( -1 ) );
	public Object getValue( edu.cmu.cs.stage3.alice.core.Array arrayValue ) {
        int i = index.intValue();
        int n = arrayValue.size();
        if( i >= 0 && i < n ) {
    		return arrayValue.itemValueAtIndex( i );
        } else {
            throw new edu.cmu.cs.stage3.alice.core.SimulationException( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/question/array/ItemAtIndex").getString("index_out_of_bounds_exception.__")+"  "  + i +" " +java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/question/array/ItemAtIndex").getString("_is_not_in_range_[0,") + n + ")", null, this );
        }
	}
}