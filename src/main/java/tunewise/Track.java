package tunewise;

import java.io.Serializable;
import java.util.Date;

import com.philemonworks.util.ToStringBuilder;

/**
 * Track is 
 * 
 * @author E.M.Micklei
 * 
 * 	<dict>
			<key>Track ID</key><integer>44</integer>
			<key>Name</key><string>My Lagan Love</string>
			<key>Artist</key><string>The Corrs</string>
			<key>Album</key><string>Home</string>
			<key>Genre</key><string>Andere</string>
			<key>Kind</key><string>MPEG-audiobestand</string>
			<key>Size</key><integer>10409363</integer>
			<key>Total Time</key><integer>260205</integer>
			<key>Track Number</key><integer>1</integer>
			<key>Track Count</key><integer>12</integer>
			<key>Year</key><integer>2005</integer>
			<key>Date Modified</key><date>2005-10-17T19:44:53Z</date>
			<key>Date Added</key><date>2005-10-12T21:01:48Z</date>
			<key>Bit Rate</key><integer>320</integer>
			<key>Sample Rate</key><integer>44100</integer>
			<key>Play Count</key><integer>1</integer>
			<key>Play Date</key><integer>-1082901433</integer>
			<key>Play Date UTC</key><date>2005-10-13T14:31:03Z</date>
			<key>Track Type</key><string>File</string>
			<key>Location</key><string>file://localhost/L:/Music/The%20Corrs/Home/01%20My%20Lagan%20Love.mp3</string>
			<key>File Folder Count</key><integer>4</integer>
			<key>Library Folder Count</key><integer>1</integer>
		</dict>
 */
public class Track implements Serializable {

	private String id = "";
	private String name = "?";
	private String artist = "?";
	private String album = "?";
	private String genre = "?";
	private String kind = "?";
	private int size = 0;
	private int totalTime = 0;
	private int trackNumber = 0;
	private int trackCount = 0;
	private int year = 0;
	private Date dateModified = null;
	private Date dateAdded = null;
	private int bitRate = 0;
	private int sampleRate = 0;
	private int playCount = 0;
	private Date playDate = null;
	private Date playDateUTC = null;
	private String trackType = "";
	private String location = "";
	private int fileFolderCount = 0;
	private int libraryFolderCount = 0;

	public Track(String newId) {
		super();
		id = newId;
	}

	/**
	 * @return
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @return
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setAlbum(String string) {
		album = string;
	}

	/**
	 * @param string
	 */
	public void setArtist(String string) {
		artist = string;
	}

	/**
	 * @param string
	 */
	public void setGenre(String string) {
		genre = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
	public void put(String key, String value) {
		if ("Name".equals(key)) {
			name = value;
			return;
		}
		if ("Artist".equals(key)) {
			artist = value;
			return;
		}
		if ("Album".equals(key)) {
			album = value;
			return;
		}
		if ("Genre".equals(key)) {
			genre = value;
			return;
		}
		if ("Kind".equals(key)) {
			kind = value;
			return;
		}
		if ("Size".equals(key)) {
			size = Integer.parseInt(value);
			return;
		}
		if ("Total Time".equals(key)) {
			totalTime = Integer.parseInt(value);
			return;
		}
		if ("Track Number".equals(key)) {
			trackNumber = Integer.parseInt(value);
			return;
		}
		if ("Track Count".equals(key)) {
			trackCount = Integer.parseInt(value);
			return;
		}
		if ("Year".equals(key)) {
			year = Integer.parseInt(value);
			return;
		}
		if ("Date Modified".equals(key)) {
			dateModified = null; // ???
			return;
		}
		if ("Date Added".equals(key)) {
			dateAdded = null; // ??
			return;
		}
		if ("Bit Rate".equals(key)) {
			bitRate = Integer.parseInt(value);
			return;
		}
		if ("Sample Rate".equals(key)) {
			sampleRate = Integer.parseInt(value);
			return;
		}
		if ("Play Count".equals(key)) {
			playCount = Integer.parseInt(value);
			return;
		}
		if ("Play Date".equals(key)) {
			playDate = null; // ??
			return;
		}
		if ("Play Date UTC".equals(key)) {
			playDate = null; // ??
			return;
		}
		if ("Track Type".equals(key)) {
			trackType = value;
			return;
		}
		if ("Location".equals(key)) {
			location = value;
			return;
		}
		if ("File Folder Count".equals(key)) {
			fileFolderCount = Integer.parseInt(value);
			return;
		}
		if ("Library Folder Count".equals(key)) {
			libraryFolderCount = Integer.parseInt(value);
			return;
		}
	}
	public String toString() {
		return ToStringBuilder.build(this);
	}
	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @return
	 */
	public int getBitRate() {
		return bitRate;
	}

	/**
	 * @return
	 */
	public Date getDateAdded() {
		return dateAdded;
	}

	/**
	 * @return
	 */
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @return
	 */
	public int getFileFolderCount() {
		return fileFolderCount;
	}

	/**
	 * @return
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @return
	 */
	public int getLibraryFolderCount() {
		return libraryFolderCount;
	}

	/**
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return
	 */
	public int getPlayCount() {
		return playCount;
	}

	/**
	 * @return
	 */
	public Date getPlayDate() {
		return playDate;
	}

	/**
	 * @return
	 */
	public Date getPlayDateUTC() {
		return playDateUTC;
	}

	/**
	 * @return
	 */
	public int getSampleRate() {
		return sampleRate;
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * @return
	 */
	public int getTrackCount() {
		return trackCount;
	}

	/**
	 * @return
	 */
	public int getTrackNumber() {
		return trackNumber;
	}

	/**
	 * @return
	 */
	public String getTrackType() {
		return trackType;
	}

	/**
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param i
	 */
	public void setBitRate(int i) {
		bitRate = i;
	}

	/**
	 * @param date
	 */
	public void setDateAdded(Date date) {
		dateAdded = date;
	}

	/**
	 * @param date
	 */
	public void setDateModified(Date date) {
		dateModified = date;
	}

	/**
	 * @param i
	 */
	public void setFileFolderCount(int i) {
		fileFolderCount = i;
	}

	/**
	 * @param string
	 */
	public void setKind(String string) {
		kind = string;
	}

	/**
	 * @param i
	 */
	public void setLibraryFolderCount(int i) {
		libraryFolderCount = i;
	}

	/**
	 * @param string
	 */
	public void setLocation(String string) {
		location = string;
	}

	/**
	 * @param i
	 */
	public void setPlayCount(int i) {
		playCount = i;
	}

	/**
	 * @param date
	 */
	public void setPlayDate(Date date) {
		playDate = date;
	}

	/**
	 * @param date
	 */
	public void setPlayDateUTC(Date date) {
		playDateUTC = date;
	}

	/**
	 * @param i
	 */
	public void setSampleRate(int i) {
		sampleRate = i;
	}

	/**
	 * @param i
	 */
	public void setSize(int i) {
		size = i;
	}

	/**
	 * @param i
	 */
	public void setTotalTime(int i) {
		totalTime = i;
	}

	/**
	 * @param i
	 */
	public void setTrackCount(int i) {
		trackCount = i;
	}

	/**
	 * @param i
	 */
	public void setTrackNumber(int i) {
		trackNumber = i;
	}

	/**
	 * @param string
	 */
	public void setTrackType(String string) {
		trackType = string;
	}

	/**
	 * @param i
	 */
	public void setYear(int i) {
		year = i;
	}

}
