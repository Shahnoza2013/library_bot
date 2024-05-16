package uz.pdp.backend.service;

import uz.pdp.backend.model.users.Users;
import uz.pdp.backend.repository.FileWriterAndLoader;
import uz.pdp.backend.statics.PathConstants;

import java.util.List;
import java.util.Objects;

public class UserService implements BaseService, PathConstants {
    FileWriterAndLoader<Users> writerAndLoader;

    public UserService() {
        this.writerAndLoader =new FileWriterAndLoader<>(USERS_PATH);
    }

    public void save(Users myUser ) {
        List<Users> users = writerAndLoader.load(Users.class);
        for (int i = 0; i < users.size(); i++) {
            Users cur = users.get(i);
            if(Objects.equals(cur.getId(),myUser.getId())){
                users.set(i, myUser);
                writerAndLoader.write(users);
                return;
            }
        }users.add(myUser);
        writerAndLoader.write(users);
        return;
    }

    public Users get(Long id ) {
        List<Users> users = writerAndLoader.load(Users.class);
        for (int i = 0; i < users.size(); i++) {
            Users cur = users.get(i);
            if(Objects.equals(cur.getId(),id)){
                return cur;
            }
        }return null;
    }
    }

