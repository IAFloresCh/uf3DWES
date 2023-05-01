/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvm.daw.uf3.db.music;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mvm.daw.uf3.Music;
import mvm.daw.uf3.MusicDAO;
import mvm.daw.uf3.db.DBConnection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author daw
 */
public class MusicDAOTest {

    private DBConnection dBConnection;
    private String connectionProperties = "db_test.properties";

    MusicDAO musicDAO;

    @Before
    public void setUp() {
        dBConnection = new DBConnection(connectionProperties);
        musicDAO = new MusicDAO(dBConnection);
    }

    @After
    public void tearDown() throws IOException, SQLException {
        musicDAO.getConnection().close();
    }

    @Test
    public void testFindAllSongs() {
        Assert.assertEquals(3, musicDAO.findAllSongs().size());
    }

    @Test
    public void findSongById() throws Exception {

        musicDAO = new MusicDAO(dBConnection);
        Music createdSong = musicDAO.createSong(4, "new song", 10, "Author", "album", 5);
        Music music = musicDAO.findSongById(4);
        Assert.assertNotNull(music);
        Assert.assertEquals(4, createdSong.getId());
    }

    @Test
    public void findSongByName() throws Exception {
        String existingUsername = "American Pie";
        String unknownUsername = "user20";
        musicDAO = new MusicDAO(dBConnection);
        Music song = musicDAO.findSongByName(existingUsername);
        Assert.assertNotNull(song);
        song = musicDAO.findSongByName(unknownUsername);
        Assert.assertNull(song);
    }

    @Test
    public void deleteSong() throws Exception {
        Music createdSong = musicDAO.createSong(4, "Goodbye Yellow Brick Road", 10, "Elton John", "Goodbye Yellow Brick Road", 5);
        Assert.assertNotNull(createdSong);
        musicDAO.deleteSong(createdSong);
        Music deletedUser = musicDAO.findSongById(4);
        Assert.assertNull(deletedUser);
    }

    @Test
    public void testFilterSongs() throws Exception {
        musicDAO = new MusicDAO(dBConnection);
        // Create test songs
        Music song1 = musicDAO.createSong(4, "Goodbye Yellow Brick Road", 10, "Elton John", "Goodbye Yellow Brick Road", 5);
        Music song2 = musicDAO.createSong(5, "Stairway to Heaven", 10, "Led Zeppelin", "Led Zeppelin IV", 5);
        Music song3 = musicDAO.createSong(6, "Imagine", 10 ,"John Lennon", "Imagine", 4);

        // Filtrar musicas por nombre
        List<Music> filteredSongs = musicDAO.filterSongs("Yellow");
        Assert.assertEquals(1, filteredSongs.size());
        Assert.assertEquals(song1.getId(), filteredSongs.get(0).getId());

        // Filtrar musicas por autor
        filteredSongs = musicDAO.filterSongs("Led Zeppelin");
        Assert.assertEquals(1, filteredSongs.size());
        Assert.assertEquals(song2.getId(), filteredSongs.get(0).getId());

        // Filtrar musicas por album
        filteredSongs = musicDAO.filterSongs("Imagine");
        Assert.assertEquals(1, filteredSongs.size());
        Assert.assertEquals(song3.getId(), filteredSongs.get(0).getId());

        // Filtrar musicas por no existente
        filteredSongs = musicDAO.filterSongs("Query falso");
        Assert.assertEquals(0, filteredSongs.size());
    }

}
