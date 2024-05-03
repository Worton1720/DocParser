import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// (Устинов Родион РПО1)
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            // Получаем HTML-страницу с сайта
            Document doc = Jsoup.connect("https://www.hse.ru/education/magister/").get();

            // Находим элементы с классом edu-programm__magister
            Elements magisterPrograms = doc.select(".edu-programm__magister .b-row.edu-programm__item");

            // Создаем файл для записи названий специальностей
            FileWriter writer = new FileWriter("specialties.txt");

            // Записываем названия специальностей в файл
            for (Element program : magisterPrograms) {
                // Находим элемент с классом link и получаем его текст
                String specialtyName = program.select(".link").first().ownText();
                // Записываем название специальности в файл
                writer.write(specialtyName + "\n");
            }

            // Закрываем файл
            writer.close();

            System.out.println("Названия специальностей успешно сохранены в файл specialties.txt");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при загрузке страницы", e);
        }
    }
}
