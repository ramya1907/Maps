import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";

    static {

        /**
         * create a new File object for FILE_LOGGER_NAME
         * if the file already exists, delete it first
         * use try/catch block
         */
        try {
            File studentFile = new File(FILE_LOGGER_NAME);
            if (studentFile.exists())
                studentFile.delete();
            studentFile.createNewFile();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void log(String message){
        /**
         * create a new FileWriter in append mode
         * write the message to file
         */
        try(FileWriter writer = new FileWriter(FILE_LOGGER_NAME, true))
        {
            writer.write(message + "\n");
        }

        catch(IOException error)
        {
            error.printStackTrace();
        }
    }
}
