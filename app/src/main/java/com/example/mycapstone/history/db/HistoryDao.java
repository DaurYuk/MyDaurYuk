package com.example.mycapstone.history.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insert(History history);

    @Query("SELECT * FROM history ORDER BY id DESC LIMIT :limit OFFSET :offset")
    List<History> getPagedHistory(int limit, int offset);

    @Delete
    void deleteHistory(History history);
}
