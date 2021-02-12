package core;

public interface LoadStrings {
    void updateData(String[] words);
    void deleteData(String[] words);
    void queryData(String[] words);
    void loadData(LoadUpdate loadUpdate, String typeQuery);
}
