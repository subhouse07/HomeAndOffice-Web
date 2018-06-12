
import java.io.Serializable;


public class Album implements Serializable {
    private String title, artist, albumPath, albumId;
    private String[] trackNames, trackPaths;

    public Album() {
       
    }
    
    public Album(String albumPath, String albumId, String[] trackPaths, 
            Object addtlInfos[]) {
        this.albumId = albumId;
        this.albumPath = albumPath;
        this.trackPaths = new String[trackPaths.length-1];
        this.trackNames = new String[trackPaths.length-1];
        System.arraycopy((String[])addtlInfos[2], 0,
                this.trackNames, 0, ((int)((String[]) addtlInfos[2]).length));
        System.arraycopy(trackPaths, 0, this.trackPaths, 0, trackPaths.length-1);
        this.title = (String) addtlInfos[1];
        this.artist = (String) addtlInfos[0];
        
    }
    
    public String getAlbumId() {
        return this.albumId;
    }
    
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumPath() {
        return albumPath;
    }

    public void setAlbumPath(String albumId) {
        this.albumPath = albumId;
    }

    public String[] getTrackNames() {
        return trackNames;
    }

    public void setTrackNames(String[] tracks) {
        this.trackNames = tracks;
    }

    public String[] getTrackPaths() {
        return trackPaths;
    }

    public void setTrackPaths(String[] paths) {
        this.trackPaths = paths;
    }
    

}
