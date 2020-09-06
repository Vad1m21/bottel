import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {
        String name = "York_photosbot";
        String token = "1184978943:AAEQxbjyypBkHmL1JFyU_fs6SKSgdeUtHoA";
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot(name,token));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
