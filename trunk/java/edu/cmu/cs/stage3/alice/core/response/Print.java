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

package edu.cmu.cs.stage3.alice.core.response;

import edu.cmu.cs.stage3.alice.core.property.BooleanProperty;
import edu.cmu.cs.stage3.alice.core.property.ObjectProperty;
import edu.cmu.cs.stage3.alice.core.property.StringProperty;
import edu.cmu.cs.stage3.alice.core.property.TransformableProperty;

public class Print extends edu.cmu.cs.stage3.alice.core.Response {
	public final StringProperty text = new StringProperty( this, "text", null );
	public final ObjectProperty object = new ObjectProperty( this, "object", null, Object.class ) {
		protected boolean getValueOfExpression() {
			return true;
		}
	};
	public final BooleanProperty addNewLine = new BooleanProperty( this, "addNewLine", Boolean.TRUE );

	protected Number getDefaultDuration() {
		return new Double( 0 );
	}

	public String getPrefix() {
		String t = text.getStringValue();
		if( t != null ) {
			return null;
		} else {
			Object o = object.get();
			if( o != null ) {
				if( o instanceof edu.cmu.cs.stage3.alice.core.Element ) {
					return java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("the_value_of_")+" " + ((edu.cmu.cs.stage3.alice.core.Element)o).getTrimmedKey() + " "+java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("_is_")+" ";
				} else {
					return java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("the_value_of_")+" " + o +" "+ java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("_is_")+" ";
				}
			} else {
				return null;
			}
		}
	}

	public static String outputtext = null;
	public class RuntimePrint extends RuntimeResponse {
		public void update( double t ) {
			super.update( t );
			outputtext = null;
			String s = Print.this.text.getStringValue();
			Object o = Print.this.object.get();	
			// Time
			if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.TimeElapsedSinceWorldStart)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("TIME_ELAPSED_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.Year)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("YEAR_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.MonthOfYear)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("MONTH_OF_YEAR_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.DayOfYear)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("DAY_OF_YEAR_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.DayOfMonth)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("DAY_OF_MONTH_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.DayOfWeek)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("DAY_OF_WEEK_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.DayOfWeekInMonth)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("DAY_OF_WEEK_IN_MONTH_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.IsAM)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("IS_AM_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.IsPM)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("IS_PM_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.HourOfAMOrPM)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("HOUR_OF_AM_OR_PM_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.HourOfDay)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("HOUR_OF_DAY_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.MinuteOfHour)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("MINUTES_OF_HOUR_IS_")+" ";
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.time.SecondOfMinute)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("SECONDS_OF_MINUTE_IS_")+" ";
			// Mouse
			else if	(o instanceof edu.cmu.cs.stage3.alice.core.question.mouse.DistanceFromLeftEdge)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("MOUSE_DISTANCE_FROM_LEFT_EDGE_IS_")+" ";
			else if	(o instanceof edu.cmu.cs.stage3.alice.core.question.mouse.DistanceFromTopEdge)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("MOUSE_DISTANCE_FROM_TOP_EDGE_IS_")+" ";
			//Ask User
			else if (o instanceof edu.cmu.cs.stage3.alice.core.question.ask.AskUserForNumber)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("ASK_USER_FOR_A_NUMBER_IS_")+" ";
			else if	(o instanceof edu.cmu.cs.stage3.alice.core.question.ask.AskUserYesNo)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("ASK_USER_FOR_YES_OR_NO_IS_")+" ";
			else if	(o instanceof edu.cmu.cs.stage3.alice.core.question.ask.AskUserForString)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("ASK_USER_FOR_A_STRING_IS_")+" ";
			//Random
			else if	(o instanceof edu.cmu.cs.stage3.alice.core.question.RandomNumber)
				outputtext = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("RANDOM_NUMBER_IS_")+" ";
			
			Object value = Print.this.object.getValue();
			
			String valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("None");
			if( value instanceof edu.cmu.cs.stage3.alice.core.Element ) {
				valueText = ((edu.cmu.cs.stage3.alice.core.Element)value).getTrimmedKey();
			} else if ( value instanceof edu.cmu.cs.stage3.alice.scenegraph.Color ) {
				double blue = ((edu.cmu.cs.stage3.alice.scenegraph.Color) value).getBlue();
				double green = ((edu.cmu.cs.stage3.alice.scenegraph.Color) value).getGreen();
				double red = ((edu.cmu.cs.stage3.alice.scenegraph.Color) value).getRed();
				if ( blue == 1 && green == 1 && red == 1)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("WHITE");
				else if ( blue == 0 && green == 0 && red == 0)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("BLACK");
				else if ( blue == 0 && green == 0 && red == 1)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("RED");
				else if ( blue == 0 && green == 1 && red == 0)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("GREEN");
				else if ( blue == 1 && green == 0 && red == 0)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("BLUE");
				else if ( blue == 0 && green == 1 && red == 1)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("YELLOW");
				else if ( blue == 0.501960813999176 && green == 0 && red == 0.501960813999176)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("PURPLE");
				else if ( blue == 0 && green == 0.6470588445663452 && red == 1)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("ORANGE");
				else if ( blue == 0.686274528503418 && green == 0.686274528503418 && red == 1)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("PINK");
				else if ( blue == 0.16470588743686676 && green == 0.16470588743686676 && red == 0.6352941393852234)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("BROWN");
				else if ( blue == 1 && green == 1 && red == 0)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("CYAN");
				else if ( blue == 1 && green == 0 && red == 1)
					valueText = "magenta";
				else if ( blue == 0.501960813999176 && green == 0.501960813999176 && red == 0.501960813999176)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("GRAY");
				else if ( blue == 0.7529411911964417 && green == 0.7529411911964417 && red == 0.7529411911964417)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("LIGHT_GRAY");
				else if ( blue == 0.250980406999588 && green == 0.250980406999588 && red == 0.250980406999588)
					valueText = java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/response/Print").getString("DARK_GRAY");
				else {
					valueText = value.toString();
					valueText = valueText.substring(valueText.indexOf("Color") , valueText.length());
				}
			} else if( value != null ) {
				valueText = value.toString();
			}

			String output;
			if( s != null ) {
				if( o != null ) {
					output = s + valueText;
				} else {
					output = s;
				}
			} else {
				if( o != null ) {
					output = Print.this.getPrefix();
					if (outputtext != null)
						output = output.substring(0,(output.indexOf("__")-1))+" "+outputtext+valueText;
					else
						output = output + valueText;
				} else {
					output = valueText;
				}
			}
			if( Print.this.addNewLine.booleanValue() ) {
				System.out.println( output );
			} else {
				System.out.print( output );
			}
		}
	}
}
