package database__OLD;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import database__OLD.entity.Product;

/**
 * Created by fenim on 3/28/2018.
 */

@Database(entities = {Product.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}