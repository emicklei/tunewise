package tunewise;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * iTunesRepository reads the standard iTunes library formatted XML and manages all its tracks.
 * 
 * @author Ernest Micklei
 * @author Martin Euser
 */
public class iTunesRepository {
	public HashMap properties = new HashMap();
	public HashMap tracks = new HashMap(2000);
	public HashMap artists = new HashMap(100);
	public HashMap albums = new HashMap(100);
	public HashMap genres = new HashMap(100);
	public HashMap playlists = new HashMap(100);

	public void readFrom(String fileName) {
		iTunesXMLHandler s = new iTunesXMLHandler();
		s.repository = this;
		try {
			SAXParser p = SAXParserFactory.newInstance().newSAXParser();
			// p.setProperty("http://xml.org/sax/features/validation","false");
			InputStream is = new FileInputStream(fileName);
			p.parse(is, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void add(Track track) {
		List entries;
		// artists
		if (track.getArtist().length() > 0) {
			entries = (List) artists.get(track.getArtist());
			if (entries == null)
				artists.put(track.getArtist(), (entries = new ArrayList(100)));
			entries.add(track);
		}
		// albums
		if (track.getAlbum().length() > 0) {
			entries = (List) albums.get(track.getAlbum());
			if (entries == null)
				albums.put(track.getAlbum(), (entries = new ArrayList(100)));
			entries.add(track);
		}
		// genres
		if (track.getGenre().length() > 0) {
			entries = (List) genres.get(track.getGenre());
			if (entries == null)
				genres.put(track.getGenre(), (entries = new ArrayList(100)));
			entries.add(track);
		}
		// For playlist access
		tracks.put(track.getId(), track);
	}
	public List tracksForArtist(String artist) {
		return (List) artists.get(artist);
	}
	public List allGenres() {
		return Utils.asSorted(genres.keySet());
	}
	public List allArtists() {
		return Utils.asSorted(artists.keySet());
	}
	public List allAlbums() {
		return Utils.asSorted(albums.keySet());
	}
	public List allPlaylists() {
		return Utils.asSorted(playlists.keySet());
	}
	public List tracksWithGenre(String genre) {
		return (List) genres.get(genre);
	}
	public List tracksForAlbum(String album) {
		return (List) albums.get(album);
	}
	public List tracksForPlaylist(String playlist) {
		return new ArrayList();
	}
	public List artistsWithGenre(String genre) {
		Set artistsSet = new HashSet();
		List tracksWithGenre = (List) genres.get(genre);
		for (Iterator iter = tracksWithGenre.iterator(); iter.hasNext();) {
			Track element = (Track) iter.next();
			artistsSet.add(element.getArtist());
		}
		return Utils.asSorted(artistsSet);
	}
	public List albumsFromArtist(String artist) {
		return new ArrayList();
	}
}