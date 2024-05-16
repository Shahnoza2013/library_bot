package uz.pdp.frondend.bot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import uz.pdp.backend.model.book.Books;
import uz.pdp.backend.model.users.Users;
import uz.pdp.backend.service.BookService;
import uz.pdp.backend.service.UserService;
import uz.pdp.bean.BeanController;
import uz.pdp.MainBot;
import uz.pdp.frondend.bot.maker.MessageMaker;
import uz.pdp.frondend.bot.states.base.BaseState;
import uz.pdp.frondend.bot.states.child.MainStates;

public abstract class BaseHandler {
    protected TelegramBot bot;
    protected UserService userService;
    protected BookService bookService;
    public  Users curUser;
    protected Books curBook;
    protected Update update;
    protected MessageMaker messageMaker;

    public BaseHandler() {
        this.bot = new TelegramBot(MainBot.BOT_TOKEN);
        this.userService = BeanController.userServiceBythreadLocal.get();
        this.bookService = BeanController.bookServiceBythreadLocal.get();
        this.messageMaker = BeanController.messageMakerByThreadLocal.get();
    }

    public abstract void handle(Update update);

    public Users getOrCreateUser(User from) {
        Users user = userService.get(from.id());
        if (user == null) {
            user = Users.builder()
                    .id(from.id())
                    .userName(from.username())
                    .baseState(BaseState.MAIN_STATE.name())
                    .state(MainStates.REGISTER.name())
                    .firstName(from.firstName())
                    .build();
            userService.save(user);
        }
        return user;
    }
}
