# SPRING приложение, реализирующее REST API для книжного сервиса.

## Описание работы
Приложение в соответсвии со стандартом `REST API` может по HTTP-запросу создавать, изменять, удалять и предоставлять
данные о книгах, хранящиеся в базе данных `Postgres`. Возвращаемые данные при поиске по параметрам книги кэшируются
в базе данных `Redis`.

## Настройки

### Образы баз данных
В файле `docker/docker-compose.yml` указаны параметры образов баз данных `Postgres` и `Redis` для платформы `Docker`.
Для их создания необходимо в командной строке перейти в папку, где находится указанный выше файл, и ввести команду
`docker compose up`.

### Файл конфигурации
В файле конфигурации `src/resources/application.properties` указаны параметры для подключения к базам данных, они
полностью соответствуют параметрам, использумым при создании образов в предущем пункте.

## Управление
Для работы с приложением необходимо пользоваться любым инструментом для тестирования HTTP-запросов:
* расширение для браузера [Chrome Talend API](https://chrome.google.com/webstore/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm)
  или [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=ru);
* приложение [Postman](https://www.postman.com/downloads/) [(видео, как пользоваться приложением)](https://youtu.be/V1fRT3RVCRw).

Описание обрабатываемых HTTP-запросов доступно в [API Doc](http://localhost:8080/swagger-ui/index.html) после
запуска приложения.