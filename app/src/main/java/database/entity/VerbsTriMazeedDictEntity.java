package database.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mazeeddictionary")
public class VerbsTriMazeedDictEntity {



    @NonNull  public String root;
    @NonNull  public String form;

    @NonNull  public String verbtype;


    @PrimaryKey
    @NonNull
    public int id;
    @NonNull
    public String babname;

    public VerbsTriMazeedDictEntity(@NonNull String root, @NonNull String form, @NonNull String verbtype, int id, @NonNull String babname) {
        this.root = root;
        this.form = form;
        this.verbtype = verbtype;
        this.id = id;
        this.babname = babname;
    }


    @NonNull
    public String  getRoots() {
        return root;
    }

    public void setRoot(@NonNull String root) {
        this.root = root;
    }

    @NonNull
    public String getForm() {
        return form;
    }

    public void setForm(@NonNull String form) {
        this.form = form;
    }

    @NonNull
    public String getVerbtype() {
        return verbtype;
    }

    public void setVerbtype(@NonNull String verbtype) {
        this.verbtype = verbtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getBabname() {
        return babname;
    }

    public void setBabname(@NonNull String babname) {
        this.babname = babname;
    }
}
