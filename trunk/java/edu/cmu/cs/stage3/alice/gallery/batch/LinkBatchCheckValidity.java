package edu.cmu.cs.stage3.alice.gallery.batch;

public class LinkBatchCheckValidity extends LinkBatch {
	public static void main( String[] args ) {
		final String srcRootPath = args[ 0 ];
		java.io.File srcDirectory  = new java.io.File( srcRootPath );
		LinkBatchCheckValidity linkBatchCheckValidity = new LinkBatchCheckValidity();
		linkBatchCheckValidity.forEachLink( srcDirectory, new LinkHandler() {
			public void handleLink( java.io.File src, String name ) {
				java.io.File dst = new java.io.File( srcRootPath + "/" + name );
				if( dst.exists() ) {
					//System.out.println( src + " --> " + dst );
				} else {
					System.err.println( src + " --> " + dst );
				}
			}
		} );
	}
}
