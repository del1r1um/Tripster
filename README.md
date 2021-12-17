[![Build status](https://ci.appveyor.com/api/projects/status/9kf799f0x4yf3d8m?svg=true)](https://ci.appveyor.com/project/AleksandrSamsonov/tripster)

# О проекте
Автоматизация тестирования веб-сервиса покупки тура, взаимодействующего с СУБД и API Банка.

## Тестовая документация
1. [План по автоматизации тестирования](https://github.com/del1r1um/Tripster/blob/master/docs/Plan.md)
2. [Отчёт по итогам автоматизированного тестирования](https://github.com/del1r1um/Tripster/blob/master/docs/Report.md)
3. [Отчёт по итогам автоматизации](https://github.com/del1r1um/Tripster/blob/master/docs/Summary.md)

## Запуск приложения
### Подготовительный этап
1. Установить и запустить [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/);
1. Установить и запустить [Docker Desktop](https://www.docker.com/products/docker-desktop);
1. Клонировать репозиторий с проектом командой `git clone https://github.com/del1r1um/Tripster.git`
1. Открыть проект в IntelliJ IDEA.

### Запуск тестового приложения
1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой:
   ```
   docker-compose up -d
   ```
1. В новой вкладке терминала запустить тестируемое приложение:
    * Для MySQL:
   ```
   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
   ```
    * Для PostgreSQL:
   ```
   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
   ```
   
1. Приложение должно быть доступно по адресу:
```
http://localhost:8080/
```

### Запуск тестов
В новой вкладке терминала запустить тесты командой:
1. Для MySQL:
   ```
   ./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
   ```
1. Для PostgreSQL:
   ```
   ./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
   ```

## Формирование отчёта о тестировании
Для формирования отчёта о тестировании, необходимо ввести в терминале команду:
```
./gradlew allureServe
```

## Остановка контейнеров
Для остановки контейнеров необходимо ввести в терминал команду:
```
docker-compose down
```

## Перезапуск тестов и приложения
Для остановки приложения, в окне терминала необходимо нажать `Control+C` (для MacOS), либо `Ctrl+С` (для Windows) и повторить необходимые действия из предыдущих пунктов.