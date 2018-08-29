namespace cpp com.test
namespace java com.test

    struct User {
        1:i32 id,
        2:string name,
        3:i32 age,
        4:string home
    }



    service UserService {
        User getUserById(1:i32 id);
        User getUserByName(1:string name);
        list<User> getList();
        void addUser(1:User user);
        void updateUser(1:User user);
        void delUser(1:string name);
    }

    service LoginService {
        bool isLogin(1:string name);
        void test1();
    }


