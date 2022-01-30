public interface UserRepository {
//    записати юзера в базу даних і повернути його ід
    long create(User user);
//    Отримати Юзера по ід
    User read(long id);
//    редагувати юзера по ід та обєкту, повернути статус операції
    boolean update(long id,User user);
//    видалити юзера по ід і повернути статус операції
    boolean delete (long id);

}
