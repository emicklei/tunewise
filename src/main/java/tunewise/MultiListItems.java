/**
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2005 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 */
package tunewise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MultiListItems is
 * 
 * @author E.M.Micklei
 */
public class MultiListItems {
	private Set genres = null;
	private Set artists = null;
	private Set albums = null;
	private Set playlists = null;
	private List tracks = null;

	public void addGenre(String genre) {
		if (genres == null)
			genres = new HashSet(100);
		genres.add(genre);
	}
	/**
	 * @param artist
	 */
	public void addArtist(String artist) {
		if (artists == null)
			artists = new HashSet(100);
		artists.add(artist);
	}
	/**
	 * @param album
	 */
	public void addAlbum(String album) {
		if (albums == null)
			albums = new HashSet(100);
		albums.add(album);
	}
	/**
	 * @param track
	 */
	public void addTrack(Track track) {
		if (tracks == null)
			tracks = new ArrayList(100);
		tracks.add(track);
	}
public void update(Browser browser) {
		if (genres != null)
			browser.showGenres(Utils.asSorted(genres));
		if (artists != null)
			browser.showArtists(Utils.asSorted(artists));
		if (albums != null)
			browser.showAlbums(Utils.asSorted(albums));
		if (playlists != null)
			browser.showPlaylists(Utils.asSorted(playlists));
		if (tracks != null)
			browser.showTracks(tracks);		
	}	/**
		 * @return Returns the albums.
		 */
	public Set getAlbums() {
		return albums;
	}
	/**
	 * @param albums
	 *        The albums to set.
	 */
	public void setAlbums(Set albums) {
		this.albums = albums;
	}
	/**
	 * @return Returns the artists.
	 */
	public Set getArtists() {
		return artists;
	}
	/**
	 * @param artists
	 *        The artists to set.
	 */
	public void setArtists(Set artists) {
		this.artists = artists;
	}
	/**
	 * @return Returns the genres.
	 */
	public Set getGenres() {
		return genres;
	}
	/**
	 * @param genres
	 *        The genres to set.
	 */
	public void setGenres(Set genres) {
		this.genres = genres;
	}
	/**
	 * @return Returns the playlists.
	 */
	public Set getPlaylists() {
		return playlists;
	}
	/**
	 * @param playlists
	 *        The playlists to set.
	 */
	public void setPlaylists(Set playlists) {
		this.playlists = playlists;
	}
	/**
	 * @return Returns the tracks.
	 */
	public List getTracks() {
		return tracks;
	}
	/**
	 * @param tracks
	 *        The tracks to set.
	 */
	public void setTracks(List tracks) {
		this.tracks = tracks;
	}
	public void emptyAlbums(){
		albums = new HashSet();
	}
}
