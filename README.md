# Проект: FileSaver

#### Автор: Андрей Стариненко

tg: https://t.me/Local_engineer

------

#### Описание

FileSaver — это демонстрационный проект на основе Spring Boot, предназначенный для работы с файлами. Он позволяет
сохранять файлы, загружать их, а также получать информацию через RESTful API и WebSocket.

#### Технологический стек

- **Java**: Версия 17.
- **Spring Boot**: Версия 3.2.2.
- **Maven**: Используется для управления зависимостями и сборки проекта.

#### Основные зависимости

- **Spring Boot Starter Data JPA**: Для работы с базами данных через JPA.
- **Spring Boot Starter Web**: Для поддержки веб-разработки, включая RESTful API.
- **Spring Boot Starter WebSocket**: Для реализации асинхронного общения через WebSocket.
- **PostgreSQL JDBC Driver**: Драйвер для подключения к PostgreSQL.
- **Spring Boot Starter Test**: Набор инструментов для тестирования.
- **Spring Boot Autoconfigure**: Автоматическая конфигурация Spring Boot.
- **Lombok**: Упрощение кода с помощью аннотаций.
- **Spring Boot Starter Mustache**: Шаблонизатор для серверного рендеринга HTML.
- **SLF4J & Logback**: Система логирования.

#### Сборка и запуск

- Сборка: `mvn spring-boot:run`.
- Требования: наличие запущенной базы данных PostgreSQL.
- **Опционально** Настройки: переменные среды или файл `.env` с использованием плагина "_EnvFile_". Пример
  содержимого `.env`:

    ```dotenv
    SERVER_PORT=8080
    FILE_PATH=/path/to/your/files
    POSTGRES_URL=localhost
    POSTGRES_PORT=5432
    POSTGRES_DB_NAME=filesaver
    POSTGRES_USER=your_postgres_username
    POSTGRES_PASSWORD=your_postgres_password
    DDL_AUTO=update
    ```

- Запуск базы данных через Docker:

    ```bash
    docker run --name filesaver-postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=filesaver -p 5432:5432 -d postgres
    ```

- Альтернатива: создать базу данных с параметрами `user: postgres`, `pass: postgres`, `db: filesaver`, `port: 5432`.

- Конфигурация приложения: Возможность изменения `application.yaml` для настройки параметров подключения к базе данных.

#### Тестирование

Можно запустить графический интерфейс для тестирования API и WebSocket через веб-браузер.
Для этого нужно перейти по адресу [http://localhost/app](http://localhost/app) .

#### Логирование

Логирование реализовано с сохранением в базу данных, что может не быть оптимальным выбором для продакшена из-за
потенциального замедления работы приложения и увеличения нагрузки на БД.

- **Рекомендация**: использовать ELK (Elasticsearch, Logstash, Kibana) стек для улучшения производительности, удобства
  поиска и хранения логов.

### Документация API Endpoints для FileSaver

#### Общая Информация

FileSaver предлагает RESTful API для работы с файлами, включая загрузку, скачивание и получение информации о файлах.
Ниже приведена документация для доступных endpoints.

#### Endpoints

1. **Загрузка файла**

- **URL**: `/file/upload`
- **Метод**: `POST`
- **Данные запроса**: Файл, отправляемый в формате `multipart/form-data`.
- **Параметры**:
    - `file` (MultipartFile): Файл для загрузки.
- **Ответ**: Строка, указывающая результат операции загрузки.
- **Пример запроса**:
  ```shell
  curl -X POST -F 'file=@path_to_your_file' http://localhost:8080/file/upload
  ```
- **Ответ**:
  ```
  "File uploaded successfully: имя_файла.ext"
  ```

2. **Выполнение команды**

- **URL**: `/file/execute-command`
- **Метод**: `POST`
- **Тело запроса**: JSON объект `CommandRequestDTO`.
- **Параметры тела запроса**:
    - `command`: Команда для выполнения (`fileDownload` или `fileInfo`).
    - `fileName`: Имя файла, с которым связана команда.
- **Ответ**: `ResponseEntity` с результатом выполнения команды.
- **Примеры запросов**:
    - Для скачивания файла:
      ```json
      {
        "command": "fileDownload",
        "fileName": "15062022_110228.pdf"
      }
      ```
    - Для получения информации о файле:
      ```json
      {
        "command": "fileInfo",
        "fileName": "15062022_110228.pdf"
      }
      ```
- **Ответы**:
    - При команде `fileDownload`: Файл будет скачан.
    - При команде `fileInfo`: JSON с информацией о файле, например:
      ```json
      {
        "id": 1,
        "fileName": "15062022_110228.pdf",
        "fileDate": "2022-06-15T11:02:28",
        "filePath": "src/main/resources/files/15062022_110228.pdf"
      }
      ```

#### Использование

Для взаимодействия с API можно использовать инструменты, такие как curl, Postman или любой другой HTTP клиент.

### WebSocket API Документация для FileSaver

#### Общая Информация

FileSaver предоставляет WebSocket интерфейс для асинхронного общения с сервером. Это позволяет клиентам получать
мгновенные обновления, такие как регулярные уведомления и ответы на определенные команды.

#### Endpoint

- **WebSocket Endpoint**: `/ws`
- **Метод**: WebSocket
- **Разрешенные источники**: Все (`"*"`)

#### Взаимодействие с WebSocket

1. **Установка Соединения**
    - Соединение с WebSocket устанавливается через URL `ws://[server-address]/ws`, где `[server-address]` - это адрес
      сервера.
    - Пример подключения в JavaScript:
      ```javascript
      const webSocket = new WebSocket("ws://localhost/ws");
      ```

2. **Обмен Сообщениями**
    - Клиенты могут отправлять и получать сообщения через установленное соединение WebSocket.

3. **Обработка Команды 'PING'**
    - При отправке сообщения `PING` сервер ответит сообщением `PONG`, включающим количество записей логов в базе данных.
    - Пример отправки `PING` и получения ответа в JavaScript:
      ```javascript
      webSocket.send("PING");
      webSocket.onmessage = function(event) {
        console.log("Received:", event.data);
      };
      ```

4. **Регулярные Обновления**
    - Сервер автоматически отправляет сообщение `PONG` с информацией о количестве логов каждые 10 секунд всем
      подключенным клиентам.


