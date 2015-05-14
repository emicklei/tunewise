package tunewise.test;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import tunewise.Track;
import tunewise.iTunesRepository;

/**
 * RepositoryTest is 
 * 
 * @author E.M.Micklei
 */
public class RepositoryTest extends TestCase {

	static iTunesRepository repos;
	static {
		long ms = System.currentTimeMillis();
		repos = new iTunesRepository();
		repos.readFrom("itunes.xml");
		long now = System.currentTimeMillis();
		System.out.println("read iTunes.xml in ms="+(now-ms));		
	}
	
	public void testAlbumsFromArtist() {
		List result = repos.albumsFromArtist("The Corrs");
		this.dump(result);
	}
	
	private void dump(List tracks){
		for (Iterator iter = tracks.iterator(); iter.hasNext();) {
			Track element = (Track) iter.next();
			System.out.println(element);
		}

	}
}
