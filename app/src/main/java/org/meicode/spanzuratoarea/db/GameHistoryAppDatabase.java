package org.meicode.spanzuratoarea.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.meicode.spanzuratoarea.db.entity.GameHistory;

@Database(entities = {GameHistory.class}, version = 1)
public abstract class GameHistoryAppDatabase extends RoomDatabase {
    public abstract GameHistoryDAO getGameHistoryDAO();
}
