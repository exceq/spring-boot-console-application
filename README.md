# Spring Boot Console Application
## Задание:
Сделать модульное консольное приложение на Spring Boot, которое принимает в качестве аргумента командной строки имя файла, ищет среди подключенных модулей те, которые поддерживают формат заданного файла, и предлагает пользователю выбрать функцию для обработки файла.

Приложение должно использовать аннотации и Classpath scanning для определения доступных модулей.

Приложение не должно использовать xml для конфигурирования приложения.

Каждый модуль должен иметь методы:

Проверка, поддерживает ли модуль формат файла

Описание функции

Собственно, функция

Примеры модулей:
____
- текстовый файл — подсчет и вывод количества строк

- текстовый файл — вывод частоты вхождения каждого символа

- текстовый файл — придумайте собственную функцию
____
- изображение — вывод размера изображения

- изображение — вывод информации exif (можно воспользоваться библиотекой metadata-extractor)

- изображение — придумайте собственную функцию
____
- mp3 — вывод названия трека из тегов (можно воспользоваться утилитой ffmpeg)

- mp3 — вывод длительности в секундах

- mp3 — придумайте собственную функцию
____
- каталог — вывод списка файлов в каталоге

- каталог — подсчет размера всех файлов в каталоге

- каталог — придумайте собственную функцию
____
