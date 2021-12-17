package database.Dao;


import androidx.room.Dao;
import androidx.room.Query;

import database.entity.VerbsTriMazeedDictEntity;

import java.util.List;

@Dao
public interface VerbsTriMazeedDictDao {


    @Query(value = "SELECT * FROM mazeeddictionary where root=:root")
    List<VerbsTriMazeedDictEntity> getverbMazeedTri(String root);

    @Query(value = "SELECT * FROM mazeeddictionary ")
    List<VerbsTriMazeedDictEntity> getverbMazeedTriAll( );


    //   @Query(value = "SELECT * FROM mazeeddictionary order by root")
   // List<VerbsTriMazeedDict> getverbTriMazeedAll();

   // @Insert(onConflict = OnConflictStrategy.REPLACE)
   // long insert(VerbsTriMazeedDict entity);

}
