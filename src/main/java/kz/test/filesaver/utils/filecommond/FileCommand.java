package kz.test.filesaver.utils.filecommond;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileCommand<T> {
    public T execute(String filename) throws IOException;

    public Boolean checkCommand(String command);
}
