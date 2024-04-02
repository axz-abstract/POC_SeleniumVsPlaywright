package data_providers;

import org.testng.annotations.DataProvider;
import utils.Utilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderDefault {

    @DataProvider(name = "Products")
    public static Object[][] DP_Products() {
        return new Object[][] {
                {"iMac"},
                {"Apple Cinema"},
                {"Samsung Galaxy"}
        };
    }

    @DataProvider(name = "Users")
    public static Iterator<Object[]> getCSV() {
        String csvFile = Utilities.getResourcePath("users.csv");
        List<Object []> testCases = new ArrayList<Object[]>();
        String[] data= null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // ; como separador
                data = line.split(cvsSplitBy);
                testCases.add(data);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return testCases.iterator();
    }
}
