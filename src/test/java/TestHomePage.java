import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestHomePage {
    private WebDriver driver;
    private WebDriverWait wait;


//    Необходимо сделать так чтобы браузер открывался один раз
    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        ChromeDriver driver = new ChromeDriver(options);
        //        options.add_argument(r"user-data-dir=C:\Users\Eugene\AppData\Local\Google\Chrome\User Data\Profile 4")
//        browser = webdriver.Chrome(executable_path=r'C:\Users\Eugene\Documents\Python\chromedriver.exe', chrome_options=options)
//
//        browser.get('https://www.google.com/')
////        Открытие браузера во весь экран
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 13);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(13, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("c:\\tmp\\screenshot.png"));
        driver.quit();
    }







//----------------------------------------------------------------------------------------------------------------------
//  ТЕСТ НА ПЕРЕХОД ПО ССЫЛКЕ http://intershop5.skillbox.ru/ И ПОПАДАНИЕ НА ГЛАВНУЮ СТРАНИЦУ САЙТА
    @Test
    public void TestHomePage_1()
    {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент раздела "Главная"
        var HomPageElement = By.className("apwidget_title");
//        Проверка переходим ли в раздел "Главная"
        Assert.assertTrue("Не отобразилась главная страница", driver.findElement(HomPageElement).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//  ТЕСТ НА ПЕРЕХОД ИЗ РАЗДЕЛА "Главная" В РАЗДЕЛ "Каталог" И ОБРАТНО НА ГЛАВНУЮ СТРАНИЦУ
    @Test
    public void TestHomePage_2() throws InterruptedException {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Использовал ожидание 1.5 секунды чтобы появились все элементы
        Thread.sleep(1500);
//        Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.cssSelector("li#menu-item-26");
//        Элемент для перехода в раздел "Каталог"
        var KatalogElementLocator = By.xpath("//a[text() = 'Каталог']") ;
//        Элемент заголовка раздела "Каталог"
        var TitleKatalogElementLocator = By.xpath("//h1[text() = 'Каталог']");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//        Текст раздела КАТАЛОГ
        var TextCatalog = driver.findElement(KatalogElementLocator).getText();
//        Нажатие в главном меню на раздел "Каталог"
        driver.findElement(KatalogElementLocator).click();
//        Текст заголовка раздела КАТАЛОГ
        var TextTitleCatalog = driver.findElement(TitleKatalogElementLocator).getText();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Каталог"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleKatalogElementLocator).isDisplayed());
//        Проверка на соответсвие текста названия раздела КАТАЛОГ с заголовком раздела КААТЛОГ
        Assert.assertEquals("Неверный текст !", TextCatalog, TextTitleCatalog);
//        Нажатие в главном меню на раздел "Главная"
        driver.findElement(HomeElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Главная"
        Assert.assertTrue("Элемент не найден", driver.findElement(PromoWrap1ElementLocator).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//  ТЕСТ НА ПЕРЕХОД ИЗ "Главная" В РАЗДЕЛ "Мой аккаунт" И НАЗАД НА ГЛАВНУЮ СТРАНИЦУ"
    @Test
    public void TestHomePage_3() throws InterruptedException {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Использовал ожидание 1.5 секунды чтобы появились все элементы
        Thread.sleep(1500);
//        Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.cssSelector("li#menu-item-26");
//        Элемент для перехода в раздел "Мой аккаунт"
        var AccauntElementLocator = By.xpath("(//a[text() = 'Мой аккаунт'])[1]") ;
//        Элемент заголовка заполнения формы раздела "Мой аккаунт"
        var TitleAccauntElementLocator = By.xpath("//h2[@class='entry-title']");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//        Текст названия раздела МОЙ АККАУНТ
        var TextMyAccount = driver.findElement(AccauntElementLocator).getText();
//        Нажатие в главном меню на раздел "Мой аккаунт"
        driver.findElement(AccauntElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Мой аккаунт"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleAccauntElementLocator).isDisplayed());
//        Текст заголовка раздела МОЙ АККАУНТ
        var TitleTextMyaccount = driver.findElement(TitleAccauntElementLocator).getText();
//        Проверка на совпадение текста названия раздела МОЙ АККАУНТ с заголовком раздела МОЙ АККАУНТ
        Assert.assertEquals("Неверный текст", TextMyAccount, TitleTextMyaccount);
//        Нажатие в главном меню на раздел "Главная"
        driver.findElement(HomeElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Главная"
        Assert.assertTrue("Элемент не найден", driver.findElement(PromoWrap1ElementLocator).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------



//----------------------------------------------------------------------------------------------------------------------
//  ТЕСТ НА ПЕРЕХОД ИЗ РАЗДЕЛА "Главная" В РАЗДЕЛ "Корзина" И ВОЗВРАЩЕНИЕ НА ГЛАВНУЮ СТРАНИЦУ САЙТА
    @Test
    public void TestHomePage_4()
    {
        //        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.xpath("(//a[@aria-current='page'])[1]");
//        Элемент для перехода в раздел "Корзина"
        var KorzinaElementLocator = By.xpath("(//a[text() = 'Корзина'])[1]");
//        Элемент заголовка раздела "Корзина"
        var TitleKorzinaElementLocator = By.xpath("//h2[@class = 'entry-title']");
//        Элемент подтверждающий нахождения в разделе "Главная"
        var HomeElement = By.className("apwidget_title");
//        Нажатие в главном меню на раздел "Корзина"
        driver.findElement(KorzinaElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Корзина"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleKorzinaElementLocator).isDisplayed());
//        Нажатие в главном меню на раздел "Главная"
        driver.findElement(HomeElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Главная"
        Assert.assertTrue("Элемент не найден", driver.findElement(HomeElement).isDisplayed());
    }

//----------------------------------------------------------------------------------------------------------------------



//----------------------------------------------------------------------------------------------------------------------
//  ПЕРЕХОД ИЗ РАЗДЕЛА "Главная" В РАЗДЕЛ "Оформление заказа"
//  Переделать
    @Test
    public void TestHomePage_5()
    {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.xpath("(//a[@aria-current='page'])[1]");
//        Элемент для перехода в раздел "Оформление заказа"
        var OformlenieElementLocator = By.cssSelector("#menu-item-31 > a");
//        Элемент заголовка раздела "Оформление заказа"
//        var TitleOformlenieElementLocator = By.xpath("//h2[@class = 'entry-title'][text() = 'Оформление заказа']");;
//        Элемент заголовка раздела "Корзина"
        var TitleKorzinaElementLocator = By.xpath("//h2[@class = 'entry-title']");
//        Элемент подтверждающий нахождения в разделе "Главная"
        var HomeElement = By.className("apwidget_title");
//        Нажатие в главном меню на раздел "Оформление заказа"
        driver.findElement(OformlenieElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Оформление заказа"
//        Assert.assertTrue("Элемент не найден", driver.findElement(TitleKorzinaElementLocator).isDisplayed());
//        Нажатие в главном меню на раздел "Главная"
//        driver.findElement(HomeElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Главная"
//        Assert.assertTrue("Элемент не найден", driver.findElement(HomeElement).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//   ПРОВЕРКА НА НАЛИЧИЕ ПОСЛЕ АВТОРИИЗАЦИИ ПОЛЬЗОВАТЕЛЯ ПРИВЕТСТВЕННОГО СООБЕЩЕНИЯ
//   В ВЕРХНЕМ ПРАВОМ УГЛУ"Привет Sacha !"
    @Test
    public void TestHomePage_6()
    {
//       Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент "Войти"
        var AccountButtonElement = By.className("account");
//        Элемент "Имя пользователя или почта"
        var InputUserNameElement = By.id("username");
//        Элемент "Пароль"
        var PasswordElement = By.id("password");
//        Элемент "Войти" формы "Мой аккаунт"
        var ButtonElement = By.name("login");
//        Элемент приветствия вправом верхнем углу "Привет <введённое имя пользователя или почта> !"
        var WelcomeUser = By.className("welcome-user");
//        Клик по кнопке "Войти" вправом верхнем углу
        driver.findElement(AccountButtonElement).click();
//        Ввод имени пользователя или почты
        driver.findElement(InputUserNameElement).sendKeys("Sacha");
//        Ввод пароля
        driver.findElement(PasswordElement).sendKeys("qwerty123");
//        Клик по кнопке "Войти" формы авторизации
        driver.findElement(ButtonElement).click();
//        Проверка наличия приветствия вправом верхнем углу "Привет <введённое имя пользователя или почта> !"
        Assert.assertTrue("Элемент не найден", driver.findElement(WelcomeUser).isDisplayed());
//        Вывод текста элемента приветствия "Привет <введённое имя пользователя или почта> !"
        Assert.assertEquals("Неверный текст приветствия !", "| Привет Sacha !", driver.findElement(WelcomeUser).getText());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//    ПЕРЕХОД В БЛОК КНИГИ ИЗ РАЗДЕЛА "Главная"
    @Test
    public void TestHomePage_7()
    {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//         Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.xpath("(//div[@class='store-menu']//a)[1]");
//        Элемент "Просмотреть" раздела "Книги"
        var SpanLinkBtnLocator = By.xpath("(//span[@class = 'btn promo-link-btn'])[1]");
//        Элемент заголовка раздела "Книги"
        var TitleKnigiLocator = By.xpath("(//h1)[2]");
//        Элемент подтверждающий нахождения в разделе "Главная"
        var HomeElement = By.className("apwidget_title");
//        Клик по кнопке "Просмотреть" раздела "Книги"
        driver.findElement(SpanLinkBtnLocator).click();
//        Проверка на наличие заголовка "книги"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleKnigiLocator).isDisplayed());
//        Проверка на совпадение текста названия блока "Книги" с названем заголовка раздела
        Assert.assertEquals("Неверный текст !", "КНИГИ", driver.findElement(TitleKnigiLocator).getText());
//        Переход на главную страницу
        driver.findElement(HomeElementLocator).click();
//        Проверка подтверждающая нахождение на главной странице
        Assert.assertTrue("Элемент не найден", driver.findElement(HomeElement).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------



//----------------------------------------------------------------------------------------------------------------------
//    ТЕСТ НА ПЕРЕХОД В РАЗДЕЛ ПЛАНШЕТЫ БЕЗ ВХОДА В ЛИЧНЫЙ КАБИНЕТ
    @Test
    public void TestHomePage_8() throws InterruptedException {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");

//        Использовал ожидание 1.5 секунды чтобы появились все элементы
//        Так как способ wait.until(ExpectedConditions.presenceOfElementLocated(TabletsElementLocator));
//        почему-то не сработал, я и время менял, но тест завершался до того как нужный элемент
//        (название блока ПЛАНШЕТЫ) появлялся
        Thread.sleep(1500);

//        Элемент блока ПЛАНШЕТЫ
        var TabletsElementLocator = By.cssSelector("aside#accesspress_storemo-3");
//        Элемент заголовка раздела ПЛАНШЕТЫ
        var TitleTabletsElementLocator = By.cssSelector("h1.entry-title.ak-container");
//        Элемент ГЛАВНАЯ в разделе ПЛАНШЕТЫ
        var MainElementlocator = By.xpath("//a[@href = 'http://intershop5.skillbox.ru']");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//          Элемент названия блока ПЛАНШЕТЫ
        var Planshet = By.xpath("(//h4)[2]");
//          Текст названия блока ПЛАНШЕТЫ на главной странице
        var TextBlockTablets = driver.findElement(Planshet).getText();
//          Клик по элементу блока ПЛАНШЕТЫ
        driver.findElement(TabletsElementLocator).click();
//        Проверка на наличие заголовка ПЛАНШЕТЫ в разделе ПЛАНШЕТЫ
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleTabletsElementLocator).isDisplayed());
//        Текст заголовка раздела ПЛАНШЕТЫ
        var TextTitleTablets = driver.findElement(TitleTabletsElementLocator).getText();
//        Проверка текста на совпадение названия блока ПЛАНШЕТЫ с текстом заголовка раздела ПЛАНШЕТЫ
        Assert.assertEquals("Неверный текст", TextBlockTablets, TextTitleTablets);
//        Клик по элементу ГЛАВНАЯ в разделе ПЛАНШЕТЫ
        driver.findElement(MainElementlocator).click();
//        Проверка на наличие элемента  блока главной страницы, подтверждающий нахождение пользователя на главной странице
        Assert.assertTrue("Элемент не найден", driver.findElement(PromoWrap1ElementLocator).isDisplayed());
    }

    @Test
    public void TEstProverka() throws InterruptedException {
//        driver.get("http://intershop5.skillbox.ru/");
        driver.navigate().to("http://intershop5.skillbox.ru/");
        Thread.sleep(5000);
        driver.manage().window().setSize(new Dimension(1296, 696));
        driver.findElement(By.cssSelector("#menu-item-31 > a")).click();
        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#menu-item-26 > a")).click();
    }
}
