package name.nkid00.u00a7;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

// load and format options
public class Options {
    public static File file;

    public boolean allowFormattingCodes = true;
    public boolean allowFormattingCodesOnSigns = true;
    public boolean enableHotKey = true;

    public static void load() {
        U00A7.info("Loading options");
        try (var reader = new FileReader(file)) {
            U00A7.options = U00A7.GSON.fromJson(reader, Options.class);
        } catch (IOException | JsonSyntaxException | JsonIOException e) {
            U00A7.info("Generating default options");
            U00A7.options = new Options();
        }
        U00A7.info("Formatting options");
        try (var writer = new FileWriter(file)) {
            U00A7.GSON.toJson(U00A7.options, writer);
        } catch (IOException | JsonIOException e) {
            throw new CrashException(new CrashReport("Failed to write options", e));
        }
    }
}
