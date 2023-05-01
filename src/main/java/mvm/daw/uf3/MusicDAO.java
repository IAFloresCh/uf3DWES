package mvm.daw.uf3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mvm.daw.uf3.db.DBConnection;

/**
 *
 * @author daw
 */
public class MusicDAO {

    private DBConnection dBConnection;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public MusicDAO(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public DBConnection getDBConnection() {
        return this.dBConnection;
    }

    public List<Music> findAllSongs() {
        String qry = "select id, name, price, author, album, rating from songs";
        List<Music> songs = executeQuery(qry);
        return songs;
    }

    public Music findSongById(int id) throws Exception {
        String qry = "select * from songs where id = " + id + ";";
        return findUniqueResult(qry);
    }

    public Music findSongByName(String name) throws Exception {
        String qry = "select * from songs where name ='" + name + "'";
        return findUniqueResult(qry);
    }

    private Music buildSongFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        float price = rs.getFloat("price");
        String author = rs.getString("author");
        String album = rs.getString("album");
        int rating = rs.getInt("rating");
        Music song = new Music(id, name, price, author, album, rating);
        return song;
    }

    private Music findUniqueResult(String query) throws Exception {
        List<Music> songs = executeQuery(query);
        if (songs.isEmpty()) {
            return null;
        }
        if (songs.size() > 1) {
            throw new Exception("Only one result expected");
        }
        return songs.get(0);
    }

    public Music createSong(int id, String name, float price, String author, String album, int rating) throws Exception {
        String qry = "INSERT INTO songs (name, price, author, album, rating) VALUES ('"
                + name + "','"
                + price + "', '"
                + author + "', '"
                + album + "', '"
                + rating + "'"
                + ");";
        return createOrUpdateSong(id, qry);
    }

    private Music createOrUpdateSong(int id, String query) throws Exception {
        int result = executeUpdateQuery(query);
        if (result == 0) {
            throw new Exception("Error creating user");
        }
        return findSongById(id);
    }

    private List<Music> executeQuery(String query) {
        List<Music> songs = new ArrayList<>();

        if (getConnection() == null) {
            try {
                setConnection(dBConnection.getConnection());
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        try (
                 Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Music song = buildSongFromResultSet(rs);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    private int executeUpdateQuery(String query) {
        int result = 0;
        if (getConnection() == null) {
            try {
                setConnection(dBConnection.getConnection());
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        try (
                 Statement stmt = getConnection().createStatement()) {
            result = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Music updateSong(int id, String newName, float newPrice, String newAuthor, String newAlbum, int newRating) throws Exception {
        String qry = "UPDATE songs "
                + "SET name = '" + newName + "', "
                + "price = '" + newPrice + "', "
                + "author = '" + newAuthor + "', "
                + "album = '" + newAlbum + "', "
                + "rating = '" + newRating + "' "
                + "WHERE id = '" + id + "' "
                + ";";
        int result = executeUpdateQuery(qry);

        return findSongById(id);
    }

    public void deleteSong(Music song) throws Exception {
        String query = "DELETE FROM songs WHERE id = '" + song.getId() + "' ";
        createOrUpdateSong(song.getId(), query);
    }

    public List<Music> getSongsByRating(int rating) {
        List<Music> songs = findAllSongs();
        List<Music> filteredSongs = new ArrayList<Music>();
        for (Music song : songs) {
            if (song.getRating() == rating) {
                filteredSongs.add(song);
            }
        }
        return filteredSongs;
    }

    public List<Music> filterSongs(String query) {
        List<Music> songs = findAllSongs();
        List<Music> filteredSongs = new ArrayList<Music>();
        for (Music song : songs) {
            if (song.getName().toLowerCase().contains(query.toLowerCase())
                    || song.getAuthor().toLowerCase().contains(query.toLowerCase())
                    || song.getAlbum().toLowerCase().contains(query.toLowerCase())) {
                filteredSongs.add(song);
            }
        }
        return filteredSongs;
    }

}
