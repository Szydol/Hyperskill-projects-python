package server;

public class Database {
    public Database(int capacity) {
        this.capacity = capacity;
        mainList = new String[capacity];
    }

    private final String[] mainList;
    private final int capacity;

    public String get(int index) {
        return mainList[index];
    }

    public boolean set(int index, String value) {
        if (index >= capacity) {
            return false;
        } else {
            mainList[index] = value;
            return true;
        }
    }
    public boolean delete(int index) {
        if (index >= capacity) {
            return false;
        } else {
            mainList[index] = null;
            return true;
        }
    }
}
