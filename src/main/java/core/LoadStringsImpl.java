package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoadStringsImpl implements LoadStrings {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadStringsImpl.class);
    public List<LoadUpdate> loadUpdateList = new ArrayList<>();
    public List<LoadData> loadDataList = new ArrayList<>();
//    private static final String INSERT_ORDER = "insert into orders(letter, price, size_order, type_order) values (?,?,?,?)";

    @Override
    public void updateData(String[] words) {
        LoadUpdate loadUpdate = new LoadUpdate(words[0], Integer.valueOf(words[1]), Integer.valueOf(words[2]), words[3]);
        LOGGER.info(loadUpdate.toString());
        loadUpdateList.add(loadUpdate);//ask - buy, bid - sell
    }

    @Override
    public void deleteData(String[] words) {
        LoadDelete loadDelete = new LoadDelete(words[0], words[1], Integer.valueOf(words[2]));//letter type size
        LOGGER.info(loadDelete.toString());
        LoadUpdate loadUpdate = new LoadUpdate();
        int newSize = 0;
        switch(loadDelete.getTypeDelete()){
            case "buy":
                loadUpdate = loadUpdateList.stream().filter((p) -> p.getTypeUpdate().equals("ask"))
                        .filter((p) -> p.getSizeUpdate() > 0)
                        .min(Comparator.comparing(LoadUpdate::getPriceUpdate)).get();
                break;
            case "sell":
                loadUpdate = loadUpdateList.stream().filter((p) -> p.getTypeUpdate().equals("bid"))
                        .filter((p) -> p.getSizeUpdate() > 0)
                        .max(Comparator.comparing(LoadUpdate::getPriceUpdate)).get();
                break;
            default:
                break;
        }
        newSize = loadUpdate.getSizeUpdate() - loadDelete.getSizeDelete();
        if (newSize < 0){
            LOGGER.info("Not enough size for bit, necessary " + loadDelete.getSizeDelete() + " we have only " + loadUpdate.getSizeUpdate());
            newSize = loadUpdate.getSizeUpdate();
        }
        loadUpdate.setSizeUpdate(newSize);

    }

    @Override
    public void queryData(String[] words) {
        LoadQuery loadQuery;
        LoadUpdate loadUpdate = new LoadUpdate();
        if(words.length > 2) {
            loadQuery = new LoadQuery(words[0], words[1], Integer.valueOf(words[2]));
            loadUpdate = loadUpdateList.stream().filter((p) -> p.getPriceUpdate().equals(loadQuery.getSizeQuery()))
                    .filter((p) -> p.getSizeUpdate() > 0)
                    .max(Comparator.comparing(LoadUpdate::getPriceUpdate)).get();
        } else {
            loadQuery = new LoadQuery(words[0], words[1]);
            switch (loadQuery.getTypeQuery()){
                case "best_bid":
                    loadUpdate = loadUpdateList.stream().filter((p) -> p.getTypeUpdate().equals(loadQuery.getTypeQuery().substring(5)))
                            .filter((p) -> p.getSizeUpdate() > 0)
                            .max(Comparator.comparing(LoadUpdate::getPriceUpdate)).get();
                    break;
                case "best_ask":
                    loadUpdate = loadUpdateList.stream().filter((p) -> p.getTypeUpdate().equals(loadQuery.getTypeQuery().substring(5)))
                            .filter((p) -> p.getSizeUpdate() > 0)
                            .min(Comparator.comparing(LoadUpdate::getPriceUpdate)).get();
                    break;
                default:
                    break;

            }

        }
        LOGGER.info(loadQuery.toString());
        loadData(loadUpdate, loadQuery.getTypeQuery());
    }

    @Override
    public void loadData(LoadUpdate loadUpdate, String typeQuery) {
        LOGGER.info("Analyze data");
        String stringToFile = "";
        switch (typeQuery){
            case "size":
                stringToFile = loadUpdate.getSizeUpdate().toString();
                break;
            default:
                stringToFile = loadUpdate.getPriceUpdate() + "," + loadUpdate.getSizeUpdate();
                break;
        }

        LoadData loadData = new LoadData(stringToFile);
        loadDataList.add(loadData);
    }

    public void writeFile(String fileWrite, List<LoadData> dataList){
        LOGGER.info("Write to file");

        try {
            if (!Files.isRegularFile(Paths.get(fileWrite))) {
                Files.createFile(Paths.get(fileWrite));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try (BufferedWriter writer= new BufferedWriter(new FileWriter(fileWrite));) {

            for(LoadData stringToFile: dataList) {
                writer.write(stringToFile.getStringForFile());
                writer.newLine();
            }
        } catch (IOException ex){
            ex.printStackTrace();

        }
    }

    public void readFile(String fileName, LoadStringsImpl loadStrings) {

        LOGGER.info("Read file");
        try {
            if (!Files.isRegularFile(Paths.get(fileName))) {
                throw new Exception("File is not exist!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try (BufferedReader reader= new BufferedReader(new FileReader(fileName));){
            while (reader.ready()) {
                String lineFile = reader.readLine();
                String[] words = lineFile.split(",");

                switch(words[0]){
                    case "u":
                        loadStrings.updateData(words);
                        break;
                    case "o":
                        loadStrings.deleteData(words);
                        break;
                    case "q":
                        loadStrings.queryData(words);
                        break;
                    default:
                        break;
                }

            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
