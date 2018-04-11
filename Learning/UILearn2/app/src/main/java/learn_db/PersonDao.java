package learn_db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by fenim on 3/28/2018.
 */

@Dao
public interface PersonDao {

    // Adds a person to the database
    @Insert
    void insertAll(Person... people);

    // Removes a person from the database
    @Delete
    void delete(Person person);

    // Gets all people in the database
    @Query("SELECT * FROM person")
    List<Person> getAllPeople();

    // Gets all people in the database with a favorite color
    @Query("SELECT * FROM person WHERE favoriteColor LIKE :color")
    List<Person> getAllPeopleWithFavoriteColor(String color);

}
/*
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
    User findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
*/
/*
public class NameTuple {
    @ColumnInfo(name="first_name")
    public String firstName;

    @ColumnInfo(name="last_name")
    public String lastName;
}
*/