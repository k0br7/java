-- Задание 2.2. Реализация списка книг по популярным
-- P = B + 0,7*C + 0,4*K,
-- где B — количество пользователей, купивших книгу
-- C — количество пользователей, у которых книга находится в корзине
-- K — количество пользователей, у которых книга отложена.


--количество пользователей, которые купили книги
select book_id, count(user_id)
from book2user
         join book2user_type
              on book2user_type.id = book2user.type_id
where book2user_type.code = 'PAID'
group by book2user.book_id;


--количество пользователей, у которых книги находится в корзине
select book_id, count(user_id)
from book2user
         join book2user_type
              on book2user_type.id = book2user.type_id
where book2user_type.code = 'CART'
group by book2user.book_id;

--количество пользователей, у которых книги отложены
select book_id, count(user_id)
from book2user
         join book2user_type
              on book2user_type.id = book2user.type_id
where book2user_type.code = 'ARCHIVED'
group by book2user.book_id;




select book.id,count(book2user.user_id) from book
                                                 left join book2user on book2user.book_id=book.id
                                                 left join book2user_type on book2user_type.id = book2user.type_id
where book2user_type.code='KEPT'
GROUP BY book.id


-- Задание 2.3. Реализация списка книг по тегам
-- Что нужно сделать
-- На главной странице в нижней части располагается облако тегов, размер шрифта которых зависит от количества книг: чем больше книг отмечено тегом, тем крупнее размер шрифта. Вы можете кликнуть на интересующий вас тег и посмотреть список всех книг, им отмеченных.

select genre_id, count(book_id)
from book2genre
group by genre_id;

