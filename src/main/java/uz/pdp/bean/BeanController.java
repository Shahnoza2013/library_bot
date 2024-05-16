package uz.pdp.bean;

import uz.pdp.backend.service.BookService;
import uz.pdp.backend.service.UserService;
import uz.pdp.frondend.bot.maker.MessageMaker;

public interface BeanController {
    ThreadLocal<UserService> userServiceBythreadLocal=ThreadLocal.withInitial(UserService::new);
    ThreadLocal<BookService> bookServiceBythreadLocal=ThreadLocal.withInitial(BookService::new);
    ThreadLocal<MessageMaker> messageMakerByThreadLocal = ThreadLocal.withInitial(MessageMaker::new);
}
