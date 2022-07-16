package name.nkid00.u00a7;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class U00A7 implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("u00a7");
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public static Options options;

    @Override
    public void onInitialize() {
        var loader = FabricLoader.getInstance();
        Options.file = loader.getConfigDir().resolve("u00a7.json").toFile();
        Options.load();
    }
}
