namespace java com

    struct User {
        1:i32 id,
        2:string name,
        3:i32 age,
        4:string home
    }

    service UserService {
        User getUserById(1:i32 id);
        User getUserByName(2:string name);
        list<User> getList();
    }

    service User_2_service {
        User haha(2:string name);
    }
