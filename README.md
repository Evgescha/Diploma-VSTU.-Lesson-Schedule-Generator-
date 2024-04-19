# Создание информационной системы генерации расписания уроков (2021)

В рассматриваемой предметной области можно выделить следующие сущности:
1.	Класс – содержит информацию о классе, с которым будет проводиться урок (ид, номер класса)
2.	Учитель – содержит информацию об учителе, который будет проводить урок (ид, ФИО учителя)
3.	Предмет - содержит информацию о предмете, который будет изучаться на уроке (ид, название предмета)
4.	Учительские предметы – содержит информацию о том, какие учителя какие предметы ведут (ид учителя, ид предмета)
5.	Нагрузка – содержит информацию о часах нагрузки для класса, которую необходимо учитывать при составлении расписания (ид, часы, ид класса, ид предмета)
6.	Урок (ид, день, время, ид класса, ид предмета, ид учителя)

Структура базы данных

![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/35409d4c-e5b7-4170-94bd-0ec369278f0d)

Диаграмма вариантов использования

![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/16e6610b-c8fe-43e9-a398-43d24c76d29f)

## Интерфейс программы

При переходе на сайт, если в базе данных еще нет информации о нагрузке, пользователь увидит лишь пустую таблицу с заголовками. В нее будет заносится информация о нагрузке.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/86e7ac3f-8425-470c-9860-1cab3516a2c4)

Рисунок 4.7 - Страница добавления нагрузки

На текущей страницу будет заноситься информация, какой предмет какому классу сколько часов в неделю необходимо преподавать. Для выбора предмета и класса используется выпадающий список с ранее занесенной информацией.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/fbb258ce-2aa8-4e31-9d97-9e7f85bb0794)

Рисунок 4.8 - Пример выпадающего списка при добавлении нагрузки

Первым делом необходимо занести классы в систему. На случай, если не используется sql код дампа базы, добавлена страница редактирования классов

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/13276240-faaa-42ed-bcee-1a3e2a637ef2)

Рисунок 4.9 - Страница работы с классами

После занесения классов следует добавить в систему предметы (уроки). Ограничений по добавлениям нет, главное чтоб имена не повтрялись.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/b6305c7f-e9ad-4f2d-99af-a31a4aa60531)

Рисунок 4.10 - Страница работы с предметами

После занесения предметов следует добавить в систему преподавателей (учителей). 

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/df76494f-b7d9-4415-927a-57bae4f7b3b3)

Рисунок 4.11 - Страница работы с учителями

Делается это после добавления предметов оттого, что при добавлении учителя выбирается список предметов, которые этот учитель может вести.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/4747f15d-10ac-4429-8904-a12dd8900377)

Рисунок 4.12 - Страница добавления учителя

После занесения всей информации в систему можно переходить на страницу генерации расписания. При переходе на нее сразу будет сгенерировано новое расписание. Для удобства, оно сразу разделено по дням от понедельника до пятницы.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/4cbdee84-b53d-4e85-8f79-d6cf71e550ad)

Рисунок 4.13 - Страница генерации расписания

При открытии любого дня по очереди будут показаны расписания уроков от 1 и до 11 класса.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/5096692d-5f4b-4356-b04a-73eeaca60aba)

Рисунок 4.14 - Подробное расписание второго дня

Также, при каждой генерации показывается дополнительная информация, а именно количество итераций для генерации и предметы, которые не удалось добавить в расписание. Если за 1 000 000 шагов не удастся составить расписание, пользователю будет показано текущий вариант с указанием предмета, который не удалось добавить. 

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/734471ff-7ad1-4855-a711-f59c5cb3a56d)

Рисунок 4.15 - Информация о не вошедших уроках в расписание

Если все предметы были добавлены, информация об этом также будет выведена на экран.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/817c3ea4-f47b-4548-8538-d909e1cf4781)

Рисунок 4.16 - Информация, что все предметы вошли в расписание

О том, что каждый раз генерируется новое расписание можно убедиться простой перезагрузкой страницы. Сравним два расписания 11 класса в понедельник.

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/4f6d4153-9076-4b8f-903d-f749d4993275)

Рисунок 4.17 - Первый вариант расписания

 ![image](https://github.com/Evgescha/Diploma-VSTU.-Lesson-Schedule-Generator-/assets/38140129/8a1f1fcd-9166-4f8c-afa4-a148cdb2707b)

Рисунок 4.18 - Второй вариант расписания

Таким образом, если расписание не будет нравится кому-то из преподавателей, достаточно будет перезагружать старницу и подобрать под себя лучшее

