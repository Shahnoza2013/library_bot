package uz.pdp;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import uz.pdp.frondend.bot.handlers.UpdateManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainBot {
    public static final String BOT_TOKEN = "6784127021:AAGRpp0mIExgBOZSJhIw0beEMdPIcPN2M4w";
    static final ExecutorService pool= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    static  ThreadLocal<UpdateManager> updateHandlerThreadLocal = ThreadLocal.withInitial(UpdateManager::new);
    public static void main(String[] args) {
                   TelegramBot bot = new TelegramBot(BOT_TOKEN);
            bot.setUpdatesListener((updates) -> {
                for (Update update : updates) {
                    CompletableFuture.runAsync( () -> {
                                try { updateHandlerThreadLocal.get().manage(update);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            ,pool);
                }

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            },Throwable::printStackTrace);
        }
    }

