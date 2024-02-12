package kz.test.filesaver.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import kz.test.filesaver.exceptions.ParseException;
import kz.test.filesaver.facade.FileFacade;
import kz.test.filesaver.model.dtos.CommandRequestDTO;
import kz.test.filesaver.model.entities.FileEntity;
import kz.test.filesaver.utils.filecommond.FileCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class FileServiceTest {

  @Mock private FileFacade fileFacade;

  private FileCommand<Object> mockFileCommand = mock();
  private List<FileCommand<?>> fileCommands = List.of(mockFileCommand);

  @InjectMocks private FileService fileService;

  private CommandRequestDTO validCommandRequest;
  private CommandRequestDTO invalidCommandRequest;

  @BeforeEach
  void setUp() {
    validCommandRequest = new CommandRequestDTO("validCommand", "fileName");
    invalidCommandRequest = new CommandRequestDTO("invalidCommand", "fileName");

    when(mockFileCommand.checkCommand("validCommand")).thenReturn(true);
    when(mockFileCommand.checkCommand("invalidCommand")).thenReturn(false);

    fileService = new FileService(fileFacade, fileCommands);
  }

  @Test
  public void testStoreFile() {
    String fileName = "15062022_110228.txt";
    MultipartFile mockFile =
        new MockMultipartFile(fileName, fileName, "text/plain", "test data".getBytes());

    when(fileFacade.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

    String result = fileService.storeFile(mockFile);

    assertEquals("File uploaded successfully: " + fileName, result);
    verify(fileFacade, times(1)).save(any(FileEntity.class));
  }

  @Test
  public void testStoreFileParseException() {
    String fileName = "test.txt";
    MultipartFile mockFile =
        new MockMultipartFile(fileName, fileName, "text/plain", "test data".getBytes());

    when(fileFacade.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

    assertThrows(
        ParseException.class,
        () -> {
          fileService.storeFile(mockFile);
        });
  }

  @Test
  public void testExecuteCommandSupported() {
    Object expectedResult = new Object();
    when(mockFileCommand.execute("fileName")).thenReturn(expectedResult);

    Object actualResult = fileService.executeCommand(validCommandRequest);

    assertEquals(expectedResult, actualResult);
    verify(mockFileCommand, times(1)).execute("fileName");
  }

  @Test
  public void testExecuteCommandUnsupported() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> {
          fileService.executeCommand(invalidCommandRequest);
        });
  }
}
