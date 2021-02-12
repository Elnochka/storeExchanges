package console;

import core.LoadStringsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppAnalyzeOrders {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppAnalyzeOrders.class);

    public static void main(String[] args) {

        if (args.length == 2) {
            LoadStringsImpl loadStrings = new LoadStringsImpl();
            loadStrings.readFile(args[0], loadStrings);
            loadStrings.writeFile(args[1], loadStrings.loadDataList);
        } else {
            LOGGER.info("You should fill a command line!");
            LOGGER.info("inputData.txt outputData.txt");
            System.exit(0);
        }
    }
}
