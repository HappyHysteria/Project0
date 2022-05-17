package Jordan;

public class DaoFactory {
    public static Dao dao;

    private DaoFactory(){

    }
    public static Dao getDao(){
        if (dao == null){
            dao = new DaoImplement();
        }
        return dao;
    }
}
