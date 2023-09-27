package org.meicode.spanzuratoarea.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.meicode.spanzuratoarea.db.entity.GameHistory;

import java.util.List;

@Dao
public interface GameHistoryDAO {
    @Insert
    long addGameHistory(GameHistory gameHistory);

    @Query("delete from `Game History`")
    void deleteAll();

    @Query("select * from `Game History` order by game_id desc")
    List<GameHistory> getAll();

    @Query("select * from `Game History` where game_id ==:gameId")
    GameHistory getGameHistory(long gameId);
}
