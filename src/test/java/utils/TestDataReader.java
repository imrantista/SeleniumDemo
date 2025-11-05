package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class TestDataReader {

    private static JsonNode rootNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File("src/test/resources/testData.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getProducts() {
        JsonNode productsNode = rootNode.get("products");
        String[] products = new String[productsNode.size()];
        for (int i = 0; i < productsNode.size(); i++) {
            products[i] = productsNode.get(i).asText();
        }
        return products;
    }

    public static String getFirstName() {
        return rootNode.get("checkoutInfo").get("firstName").asText();
    }

    public static String getLastName() {
        return rootNode.get("checkoutInfo").get("lastName").asText();
    }

    public static String getPostalCode() {
        return rootNode.get("checkoutInfo").get("postalCode").asText();
    }
}
