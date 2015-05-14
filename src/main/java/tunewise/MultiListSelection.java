/**
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2005 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 */
package tunewise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * MultiListSelection is
 * 
 * @author E.M.Micklei
 */
public class MultiListSelection {
	private String genre;
	private String artist;
	private String album;
	private String playlist;

	/**
	 * @return Returns the album.
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * @param album
	 *        The album to set.
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	/**
	 * @return Returns the artist.
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist
	 *        The artist to set.
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return Returns the genre.
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre
	 *        The genre to set.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * @return Returns the playlist.
	 */
	public String getPlaylist() {
		return playlist;
	}
	/**
	 * @param playlist
	 *        The playlist to set.
	 */
	public void setPlaylist(String playlist) {
		this.playlist = playlist;
	}
	/**
	 * @return true if an album is s
	 */
	public boolean hasAlbum() {
		return album != null;
	}
	/**
	 * @return
	 */
	public boolean hasArtist() {
		return artist != null;
	}
	/**
	 * @return
	 */
	public boolean hasGenre() {
		return genre != null;
	}
	/**
	 * @return
	 */
	public boolean hasPlaylist() {
		return playlist != null;
	}
	public void update(Browser browser) {
		// Interpret the receiver's state and update the lists of the browser accordingly
		MultiListItems multi = new MultiListItems();
		this.update(browser.getRepository(), multi);
		multi.update(browser);
	}
	public boolean isEmpty() {
		return album == null && artist == null && genre == null && playlist == null;
	}
	public void update(iTunesRepository repos, MultiListItems items) {
		if (this.isEmpty()) {
			items.setGenres(repos.genres.keySet());
			items.setArtists(repos.artists.keySet());
			items.setAlbums(repos.albums.keySet());
			items.setPlaylists(repos.playlists.keySet());
			items.setTracks(new ArrayList());
			return;
		}
		if (this.hasAlbum()) {
			List tracksWithAlbum = (List) repos.albums.get(this.getAlbum());
			for (Iterator iter = tracksWithAlbum.iterator(); iter.hasNext();) {
				Track element = (Track) iter.next();
				items.addTrack(element);
			}
			return;
		}
		if (this.hasArtist()) {
			List tracksWithArtist = (List) repos.artists.get(this.getArtist());
			for (Iterator iter = tracksWithArtist.iterator(); iter.hasNext();) {
				Track element = (Track) iter.next();
				items.addAlbum(element.getAlbum());
				items.addTrack(element);
			}
			return;
		}
		if (this.hasGenre()) {
			List tracksWithGenre = (List) repos.genres.get(this.getGenre());
			for (Iterator iter = tracksWithGenre.iterator(); iter.hasNext();) {
				Track element = (Track) iter.next();
				items.addArtist(element.getArtist());
				items.addAlbum(element.getAlbum());
				items.addTrack(element);
			}
			items.emptyAlbums();
			return;
		}
	}
}
