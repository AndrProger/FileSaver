package kz.test.filesaver.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a FileEntity in the application.
 * It is a JPA entity class and is mapped to the "files" table in the database.
 */
@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

  /**
   * The unique identifier of the file entity.
   * It is generated automatically when a new file entity is created.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The name of the file.
   * It is unique across all file entities.
   */
  @Column(unique = true)
  private String fileName;

  /**
   * The date and time when the file was created or last modified.
   */
  private LocalDateTime fileDate;

  /**
   * The path where the file is stored.
   */
  private String filePath;
}
