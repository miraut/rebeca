package edu.cmu.cs.stage3.alice.gallery.batch;

public class Batch0 extends Batch {
	public static void main( String[] args ) {
		final String srcRootPath = args[ 0 ];
		final String dstRootPath = args[ 1 ];
		
		//final String srcRootPath = "f:/galleryElementsWithPoses";
		//final String dstRootPath = "f:/galleryBatch0Temp";

		//final String srcRootPath = "f:/gallerySrc/Kitchen";
		//final String dstRootPath = null;

		java.io.File srcDirectory  = new java.io.File( srcRootPath );

		Batch0 batch0 = new Batch0();
		batch0.forEachElement( srcDirectory, new ElementHandler() {
			private java.util.Dictionary m_poseKeyMap = null;
			private void outln( String s ) {
				//System.out.println( s );
				//System.out.flush();
			}
			private void store( edu.cmu.cs.stage3.alice.core.Element element, java.io.File src ) {
				String dstPath = dstRootPath + src.getAbsolutePath().substring( srcRootPath.length() );
				java.io.File dstParent = new java.io.File( dstPath ).getParentFile();

				if( dstParent.exists() ) {
					//pass
				} else {
					dstParent.mkdirs();
					outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("creating_directories_for:_")+" " + dstParent );
				}

				String elementName = element.name.getStringValue();
				String fileName = Character.toUpperCase( elementName.charAt( 0 ) ) + elementName.substring( 1 ) + ".a2c"; 
				java.io.File dst = new java.io.File( dstParent, fileName );
				try {
					element.store( dst );
				} catch( java.io.IOException ioe ) {
					ioe.printStackTrace();
				} catch( Throwable t ) {
					t.printStackTrace();
				}
			}
			private void movePosesFromMisc( edu.cmu.cs.stage3.alice.core.Transformable transformable, java.io.File src ) {
				if( transformable.misc.size() > 0 ) {
					outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("MOVING_POSES_FROM_MISC:_")+" " + src );
					for( int i=0; i<transformable.misc.size(); i++ ) {
						Object miscI = transformable.misc.get( i );
						if( miscI instanceof edu.cmu.cs.stage3.alice.core.Pose ) {
							transformable.poses.add( miscI );
						} else {
							outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("\tNOT_A_POSE:_[") + i + "] " + miscI );
						}
					}
					transformable.misc.clear();
				}
			}
			private void unhookVehicle( edu.cmu.cs.stage3.alice.core.Transformable transformable, java.io.File src ) {
				if( transformable.vehicle.get() != null ) {
					outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("UNHOOKING_VEHICLE:_")+" " + src + " " + transformable.vehicle.get() );
					transformable.vehicle.set( null );
				}
			}
			private void fixPoses( edu.cmu.cs.stage3.alice.core.Element element, java.io.File src ) {
				if( m_poseKeyMap == null ) {
					m_poseKeyMap = new java.util.Hashtable();

					m_poseKeyMap.put( "butt", "bottom" );

					m_poseKeyMap.put( "tailmid", "tail" );
					m_poseKeyMap.put( "tailmid.tailend", "tail.tailTip" );
					m_poseKeyMap.put( "midwingright", "RightWing" );
					m_poseKeyMap.put( "midwingright.wingright", "RightWing.RightWingTip" );
					m_poseKeyMap.put( "midwingleft", "LeftWing" );
					m_poseKeyMap.put( "midwingleft.wingleft", "LeftWing.LeftWingTip" );
		
					m_poseKeyMap.put( "Cylinder01", "handle" );

					m_poseKeyMap.put( "body.left_thigh.left_top_leg.left_leg.Sphere07", "body.left_thigh.left_top_leg.left_leg.left_foot" );
					m_poseKeyMap.put( "body.left_thigh.left_top_leg.left_leg.Sphere07.left_right_toe", "body.left_thigh.left_top_leg.left_leg.left_foot.left_right_toe" );
					m_poseKeyMap.put( "body.left_thigh.left_top_leg.left_leg.Sphere07.left_middle_toe", "body.left_thigh.left_top_leg.left_leg.left_foot.left_middle_toe" );
					m_poseKeyMap.put( "body.left_thigh.left_top_leg.left_leg.Sphere07.left_left_toe", "body.left_thigh.left_top_leg.left_leg.left_foot.left_left_toe" );
					m_poseKeyMap.put( "body.left_thigh.left_top_leg.left_leg.Sphere07.left_back_toe", "body.left_thigh.left_top_leg.left_leg.left_foot.left_back_toe" );
					m_poseKeyMap.put( "body.right_thigh.right_top_leg.right_leg.Sphere19", "body.right_thigh.right_top_leg.right_leg.right_foot" );
					m_poseKeyMap.put( "body.right_thigh.right_top_leg.right_leg.Sphere19.right_right_toe", "body.right_thigh.right_top_leg.right_leg.right_foot.right_right_toe" );
					m_poseKeyMap.put( "body.right_thigh.right_top_leg.right_leg.Sphere19.right_middle_toe", "body.right_thigh.right_top_leg.right_leg.right_foot.right_middle_toe" );
					m_poseKeyMap.put( "body.right_thigh.right_top_leg.right_leg.Sphere19.right_left_toe", "body.right_thigh.right_top_leg.right_leg.right_foot.right_left_toe" );
					m_poseKeyMap.put( "body.right_thigh.right_top_leg.right_leg.Sphere19.right_back_toe", "body.right_thigh.right_top_leg.right_leg.right_foot.right_back_toe" );
				
					m_poseKeyMap.put( "r_m_leg", "RightMiddleLeg" );
					m_poseKeyMap.put( "mid_body.r_f_arm2", "mid_body.RightArm" );
					m_poseKeyMap.put( "l_b_leg", "LeftBackLeg" );
					m_poseKeyMap.put( "l_b_leg.l_b_leg2", "LeftBackLeg.LeftBackLeg2" );
					m_poseKeyMap.put( "l_b_leg.l_b_leg2.l_b_leg3", "LeftBackLeg.LeftBackLeg2.LeftBackLeg3" );
					m_poseKeyMap.put( "r_b_leg", "RightBackLeg" );
					m_poseKeyMap.put( "mid_body.r_f_arm2.r_f_arm", "mid_body.RightArm.RightForearm" );
					m_poseKeyMap.put( "mid_body.l_f_arm2.l_f_arm", "mid_body.LeftArm.LeftForearm" );
					m_poseKeyMap.put( "r_b_leg.r_b_leg2", "RightBackLeg.RightBackLeg2" );
					m_poseKeyMap.put( "r_b_leg.r_b_leg2.r_b_leg3", "RightBackLeg.RightBackLeg2.RightBackLeg3" );
					m_poseKeyMap.put( "mid_body.l_f_arm2", "mid_body.LeftArm" );
					m_poseKeyMap.put( "l_m_leg", "LeftMiddleLeg" );
				}


				edu.cmu.cs.stage3.alice.core.Pose[] poses = (edu.cmu.cs.stage3.alice.core.Pose[])element.getDescendants( edu.cmu.cs.stage3.alice.core.Pose.class );
				if( poses.length > 0 ) {
					//outln( "Pose descendants: " + src );
					for( int i=0; i<poses.length; i++ ) {
						//outln( "\t" + poses[ i ] );
						boolean isToBeRemoved = false;
						java.util.Enumeration enum0 = poses[ i ].poseMap.keys();
						if( enum0 != null ) {
							while( enum0.hasMoreElements() ) {
								String key = (String)enum0.nextElement();
								if( m_poseKeyMap.get( key ) != null ) {
									Object value = poses[ i ].poseMap.get( key );
									poses[ i ].poseMap.remove( key );
									outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("\t\tFIXING_KEY:_")+" " + key );
									key = (String)m_poseKeyMap.get( key );
									poses[ i ].poseMap.put( key, value );
								}
								edu.cmu.cs.stage3.alice.core.Element e = poses[ i ].getParent().getDescendantKeyedIgnoreCase( key );
								if( e == null ) { 
									outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("\t\tMISSING_KEY:_")+" " + key );
									isToBeRemoved = true;
								}
							}
						} else {
							isToBeRemoved = true;
						}
						
						if( isToBeRemoved ) {
							poses[ i ].removeFromParent();						
							outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("\t\tREMOVING_POSE:_")+" " + poses[ i ] );
						}
					}
				}
			}
			private void hardenPoses( edu.cmu.cs.stage3.alice.core.Element element, java.io.File src ) {
				edu.cmu.cs.stage3.alice.core.Pose[] poses = (edu.cmu.cs.stage3.alice.core.Pose[])element.getDescendants( edu.cmu.cs.stage3.alice.core.Pose.class );
				for( int i=0; i<poses.length; i++ ) {
					poses[ i ].HACK_harden();
				}
			}
			private void softenPoses( edu.cmu.cs.stage3.alice.core.Element element, java.io.File src ) {
				edu.cmu.cs.stage3.alice.core.Pose[] poses = (edu.cmu.cs.stage3.alice.core.Pose[])element.getDescendants( edu.cmu.cs.stage3.alice.core.Pose.class );
				for( int i=0; i<poses.length; i++ ) {
					poses[ i ].HACK_soften();
				}
			}

			private void fixName( edu.cmu.cs.stage3.alice.core.Element element, java.io.File src ) {
				StringBuffer sb = new StringBuffer();

				String oldName = element.name.getStringValue();
				int i = 0;
				char c0 = oldName.charAt( i++ );
				sb.append( Character.toLowerCase( c0 ) );
				while( i<oldName.length() ) {
					char c = oldName.charAt( i++ );
					if( c == '_' || c == ' ' || c == '-' ) {
						if( i<oldName.length() ) {
							c = Character.toUpperCase( oldName.charAt( i++ ) );
						}
					}
					sb.append( c );
				}
				
				String newName = sb.toString();
				if( oldName.equals( newName ) ) {
					//pass
				} else {
					try {
						outln( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("\tCHANGING_NAME:_")+" " + " "+oldName + " "+java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("_to_")+" " + newName );
						element.name.set( newName );
					} catch( edu.cmu.cs.stage3.alice.core.IllegalNameValueException inve ) {
						System.err.println( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("\tUNABLE_TO_CHANGE_NAME:_")+" "+ oldName + " "+java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("_to_")+" " + newName + " "+java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("_on_")+" " + src );
					}
				}
			}
			private void fixNames( edu.cmu.cs.stage3.alice.core.Element element, java.io.File src ) {
				edu.cmu.cs.stage3.alice.core.Transformable[] transformables = (edu.cmu.cs.stage3.alice.core.Transformable[])element.getDescendants( edu.cmu.cs.stage3.alice.core.Transformable.class );
				for( int i=0; i<transformables.length; i++ ) {
					fixName( transformables[ i ], src );
				}
			}
			
			public void handleElement( edu.cmu.cs.stage3.alice.core.Element element, java.io.File src ) {
				if( element instanceof edu.cmu.cs.stage3.alice.core.Sandbox ) {
					edu.cmu.cs.stage3.alice.core.Sandbox sandbox = (edu.cmu.cs.stage3.alice.core.Sandbox)element;
					if( sandbox instanceof edu.cmu.cs.stage3.alice.core.Transformable ) {
						edu.cmu.cs.stage3.alice.core.Transformable transformable = (edu.cmu.cs.stage3.alice.core.Transformable)sandbox;
						movePosesFromMisc( transformable, src );
						unhookVehicle( transformable, src );
					}
				}

				fixPoses( element, src );
				hardenPoses( element, src );
				fixNames( element, src );
				softenPoses( element, src );

				if( dstRootPath != null ) {
					store( element, src );
				}
			}
		} );
		System.err.println( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/Batch0").getString("done") );
	}
}
