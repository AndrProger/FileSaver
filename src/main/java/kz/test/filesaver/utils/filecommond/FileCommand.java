package kz.test.filesaver.utils.filecommond;


/**
 * This is a generic interface for file commands.
 * It provides a structure for executing commands on files and checking the validity of commands.
 *
 * @param <T> the type of the result returned by the execute method
 */
public interface FileCommand<T> {

     /**
      * Executes a command on a file.
      *
      * @param filename the name of the file on which the command is to be executed
      * @return the result of the command execution
      */
     T execute(String filename);

     /**
      * Checks if a command is valid.
      *
      * @param command the command to be checked
      * @return true if the command is valid, false otherwise
      */
     Boolean checkCommand(String command);
}
