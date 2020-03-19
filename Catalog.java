package lab5;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    public List<CatalogItem> getItems() {
        return items;
    }


    private List<CatalogItem> items;
    private List<String> hackishSerializationList = new ArrayList<>();

    public static String serilizationPath = "/serialization/foo.bar";

    public Catalog() {
        items = new ArrayList<CatalogItem>();
    }

    public Catalog(List<CatalogItem> items) {
        this.items = items;
    }

    public void add(CatalogItem item) {
        items.add(item);
    }

    public void list() {
        for (CatalogItem item : items) {
            System.out.println(item);
        }
    }


    private boolean isValidPath(String path) {
        for (CatalogItem item : items) {
            if (item.getItemPath().equals(path)) {
                return true;
            }
        }

        return false;
    }

    public List<String> generateDataSource() {
        List<String> ds = new ArrayList<>();

        for (CatalogItem item : items) {
            ds.add(item.getItemName());
        }

        return ds;
    }

    public void play(String path) throws InvalidPathException {
        if (!isValidPath(path)) throw new InvalidPathException();

        File file = new File(path);
        Desktop desktop = Desktop.getDesktop();

        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(Catalog.serilizationPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.items);
            out.close();
            fileOut.close();

        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void load(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            this.items = (ArrayList<CatalogItem>) in.readObject();

            in.close();
            fileIn.close();
        } catch(IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

    }
}