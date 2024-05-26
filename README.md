# EducationStarter
EducationStarter - это учебный проект на Java, созданный для демонстрации работы кастомного Spring Boot Starter для логирования HTTP запросов. В репозитории находится проект самого стартера и REST-сервис для тестирования работы подключенного стартера.  

`Starter`предоставляет возможность логировать все входящие и исходящие HTTP запросы и ответы. Логирование включает в себя метод запроса, URL, заголовки запроса и ответа, код ответа, время обработки запроса и т.д.  
Механизм перехвата и логирования входящих HTTP запросов реализован с помощью фильтра `LoggingFilter`.

![rest](https://github.com/OksanaBuivarenko/EducationStarter/assets/144807983/24ffe82b-daa6-4d0e-ae7b-1ad7f26f1d56)

В случае возникновения ошибки, она будет обработана при помощи `GlobalExceptionHandler`.  

![exc](https://github.com/OksanaBuivarenko/EducationStarter/assets/144807983/9eea2b3e-cfb2-4e73-8f38-4cfff67fd260)

Механизм логирования исходящих HTTP запросов реализован с помощью `RequestResponseLoggingInterceptor`. Перехватчик добавлен к  bean-компоненту `StarterRestTemplate`. Для логирования исходящих HTTP запросов необходимо использовать `StarterRestTemplate` вместо обычного `RestTemplate`.

![template](https://github.com/OksanaBuivarenko/EducationStarter/assets/144807983/7ba94270-26bd-4af4-bb25-0ce73154d796)

`Journal` представляет собой REST-сервис для регистрации посещаемости занятий в танцевальной студии, а также рассчета стоимости занятий для конкретного ученика за указанный период. Таакже к сервису добавлен `TemplateController` для демонстрации работы логирования исходящих HTTP запросов.

![payment](https://github.com/OksanaBuivarenko/EducationStarter/assets/144807983/a064268b-0dcd-4b33-a3ef-39b246bf0a7b)

`Starter` подключен к сервису `Journal` при помощи добавления зависимости в `pom.xml`.

![dependency](https://github.com/OksanaBuivarenko/EducationStarter/assets/144807983/a3485370-1189-411b-a555-86a197817688)

Использование стартера можно отключить, поменяв значение `@ConditionalOnProperty(prefix = "starter", value = "enabled", havingValue = "true")` в конфигурационном файле на `false`.  
GlobalExceptionHandler можно отключить, поменяв значение `@ConditionalOnExpression("${starter.handlerEnabled:false}")` в конфигурационном файле на `false`.

## Начало работы
1. Установите на свой компьютер [JDK](https://www.oracle.com/cis/java/technologies/downloads/) и среду разработки [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/download/?section=windows), если они ещё не установлены.
2. Загрузите проект-заготовку из Git-репозитория.
3. Запустите базу данных Postgres, выполнив в терминале команду `docker compose up`.
4. Загрузите starter в локальный репозиторий при помощи команды `mvn install`.
5. Запустите сервис `Journal`.

После запуска всех сервисов документацию по API можно увидеть в [Swagger](http://localhost:8080/swagger-ui/index.html).  
Для тестирования REST-API можно использовать [Postman](https://www.postman.com/downloads/).
