package kz.test.filesaver.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandRequestDTO {
  /**
   * The command to be executed.
   */
  private String command;

  /**
   * The name of the file on which the command will be executed.
   */
  private String fileName;
}
