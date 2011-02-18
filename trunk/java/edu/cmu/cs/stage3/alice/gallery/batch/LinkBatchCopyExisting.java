package edu.cmu.cs.stage3.alice.gallery.batch;

public class LinkBatchCopyExisting extends LinkBatch {
	public static void main( String[] args ) {
		final String srcRootPath = args[ 0 ];
		final String dstRootPath = args[ 1 ];
		java.io.File srcDirectory  = new java.io.File( srcRootPath );
		LinkBatchCheckValidity linkBatchCheckValidity = new LinkBatchCheckValidity();
		linkBatchCheckValidity.forEachLink( srcDirectory, new LinkHandler() {
			public void handleLink( java.io.File src, String name ) {
				java.io.File dst = new java.io.File( dstRootPath, src.getPath().substring( srcRootPath.length() ) );

				java.io.File target = new java.io.File( dstRootPath + "/" + name );
				if( target.exists() ) {
					java.io.File dstParent = dst.getParentFile();
					if( dstParent.exists() ) {
						//pass
					} else {
						dstParent.mkdirs();
						System.out.println( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/LinkBatchCopyExisting").getString("creating_directories_for:_")+" " + dstParent );
					}
					try {
						System.out.println( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/LinkBatchCopyExisting").getString("copying_link:_")+" " + dst );
						java.io.FileOutputStream fos = new java.io.FileOutputStream( dst );
						fos.write( name.getBytes() );
						fos.flush();
					} catch( java.io.IOException ioe ) {
						ioe.printStackTrace();
					}
				} else {
					System.err.println( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/LinkBatchCopyExisting").getString("DOES_NOT_EXIST:_")+" " + src + " --> " + target );
				}
			}
		} );
		System.err.println( java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/gallery/batch/LinkBatchCopyExisting").getString("done") );
	}
}
