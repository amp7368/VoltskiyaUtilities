package voltskiya.apple.utilities.util.storage;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;

public abstract class MappedGsonFolder<Subtype extends GetNameable> {
    private final Gson gson;
    private final Map<String, Subtype> map = new HashMap<>();

    public MappedGsonFolder(@NotNull Class<Subtype> type) {
        gson = getGson();
        // get the playerTemperatures from our db
        File folder = getFolder();
        folder.mkdirs();
        final File[] files = folder.listFiles();
        if (files != null)
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    Subtype temp = gson.fromJson(reader, type);
                    map.put(temp.getName(), temp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * @return the gson used to save this
     */
    protected Gson getGson() {
        return new Gson();
    }

    /**
     * @return the folder to save the stuff
     */
    protected abstract File getFolder();

    /**
     * saves all the entries
     */
    public void saveAll() {
        for (Subtype entry : map.values()) {
            save(entry);
        }
    }

    /**
     * saves the subtype associated with the name
     *
     * @param name the name with an associated name
     */
    public void save(@NotNull String name) {
        Subtype byName = getByName(name);
        if (byName != null) save(byName);
    }

    /**
     * saves the subtype
     */
    public void save(@NotNull Subtype value) {
        map.put(value.getName(), value);
        File file = new File(getFolder(), value.getName() + ".json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(value, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return an immutable collection of the saved values
     */
    @NotNull
    public Collection<Subtype> getAll() {
        return Collections.unmodifiableCollection(map.values());
    }

    /**
     * @param name the name of the value
     * @return the value
     */
    @Nullable
    public Subtype getByName(String name) {
        return map.get(name);
    }

    /**
     * deletes the value associated with the name
     *
     * @param name the name with a value
     */
    public void delete(String name) {
        if (map.remove(name) != null)
            new File(getFolder(), name + ".json").delete();
    }

    /**
     * deletes the value associated with the named subtype
     *
     * @param value the value
     */
    public void delete(@NotNull Subtype value) {
        delete(value.getName());
    }

}
