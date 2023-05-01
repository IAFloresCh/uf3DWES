package mvm.daw.uf3;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mvm.daw.uf3.db.DBConnection;

public class MusicService {

    private List<Music> songlistservice;
    private MusicDAO musicDAO;

    public MusicService() throws SQLException, IOException {
        DBConnection dbConnection = new DBConnection("db.properties");
        this.musicDAO = new MusicDAO(dbConnection);
    }

    public List<Music> getSongsList() throws SQLException {
        return musicDAO.findAllSongs();
    }

    public Music getSong(int id) throws SQLException, Exception {
        return musicDAO.findSongById(id);
    }

    public void createSong(int id, String name, float price, String author, String album, int rating) throws SQLException, Exception {
        musicDAO.createSong(id, name, price, author, album,rating);
    }

    public void updateSong(int id, String name, float price, String author, String album,int rating) throws SQLException, Exception {
        musicDAO.updateSong(id, name, price, author,album,rating);

    }

    public void removeSong(int id) throws SQLException, Exception {
        Music song = musicDAO.findSongById(id);
        if (song != null) {
            musicDAO.deleteSong(song);
        }
    }

    public List<Music> getSongsByRating(int rating) {
        return musicDAO.getSongsByRating(rating);
    }
    
    public List<Music> filterSongs(String query)  throws SQLException, Exception{
        return musicDAO.filterSongs(query);
    }

}
