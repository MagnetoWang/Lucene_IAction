package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class NetTools {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://stackoverflow.com");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
        }
    }


}
