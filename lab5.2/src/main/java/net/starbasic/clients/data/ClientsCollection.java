package net.starbasic.clients.data;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class ClientsCollection {
    private ArrayList<Client> clients;

    public ClientsCollection() {
        clients = new ArrayList<>();
    }

    public void append(Client client) {
        clients.add(client);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void saveToJson(String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(clients, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientsCollection fromJson(String path) {
        ClientsCollection collection = new ClientsCollection();
        File file = new File(path);
        if (!file.exists()) return collection;

        try (FileReader reader = new FileReader(path)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                String type = obj.get("type").getAsString();
                String name = obj.get("name").getAsString();
                double baseAmount = obj.get("baseAmount").getAsDouble();

                switch (type) {
                    case "Роздрібний":
                        collection.append(new RetailClient(name, baseAmount));
                        break;
                    case "Оптовий":
                        collection.append(new WholesaleClient(name, baseAmount));
                        break;
                    case "Партнер":
                        collection.append(new PartnerCompany(name, baseAmount, 50.0));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }
}