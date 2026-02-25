# BrowserFactory - Documentație

## 📋 Descriere

**BrowserFactory** este o clasă care implementează **Factory Pattern** pentru crearea și configurarea WebDriver-elor. Suportă mai multe tipuri de browsere și permite selectarea ușoară a browserului de testat.

## 🌐 Browsere Suportate

1. **CHROME** (default) - Google Chrome
2. **FIREFOX** - Mozilla Firefox
3. **EDGE** - Microsoft Edge
4. **SAFARI** - Apple Safari (macOS)

## 🎯 Utilizare

### 1. Utilizare Default (Chrome)

```java
// În StepDefinitions.java
@Before
public void setUp() {
    DriverManager.initializeDriver();  // Folosește Chrome ca default
}
```

### 2. Selectarea unui Browser Specific

```java
// Selectează Firefox
@Before
public void setUp() {
    DriverManager.initializeDriver(BrowserFactory.BrowserType.FIREFOX);
}

// Sau prin setare globală
DriverManager.setBrowserType(BrowserFactory.BrowserType.FIREFOX);
DriverManager.initializeDriver();
```

### 3. Din Variabila de Mediu (Maven)

```bash
# Ruleaza testele cu Firefox
mvn test -Dbrowser=FIREFOX

# Ruleaza testele cu Edge
mvn test -Dbrowser=EDGE

# Ruleaza testele cu Chrome (default)
mvn test -Dbrowser=CHROME

# Ruleaza testele cu Safari
mvn test -Dbrowser=SAFARI
```

### 4. Configurare Dinamic în Cod

```java
public class StepDefinitions extends BasePage {
    
    @Before
    public void setUp() {
        // Citire din environment variable și inițializare
        DriverManager.initializeDriverFromEnvironment();
    }
}
```

## 🔧 Configurări per Browser

### Chrome
- Headless mode ✅
- Disable extensions
- Disable GPU
- Disable dev-shm-usage
- Accept insecure certificates
- No sandbox
- Start maximized
- Disable popup blocking

### Firefox
- Headless mode ✅
- Accept insecure certificates
- Default resolution 1920x1080

### Edge
- Capability-based configuration

### Safari
- Configurare minimă (Safari pe macOS)

## 📊 Exemplu Complet

```java
// StepDefinitions.java
public class StepDefinitions extends BasePage {
    
    @Before
    public void setUp() {
        // Opțiunea 1: Default Chrome
        DriverManager.initializeDriver();
        
        // Opțiunea 2: Firefox specific
        // DriverManager.initializeDriver(BrowserFactory.BrowserType.FIREFOX);
        
        // Opțiunea 3: Din environment
        // DriverManager.initializeDriverFromEnvironment();
    }
    
    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
    
    @Given("I Open The Browser")
    public void initialize() {
        System.out.println("Browser type: " + DriverManager.getCurrentBrowserType());
    }
}
```

## 🎪 Switch Case în BrowserFactory

```java
public static WebDriver createBrowser(BrowserType browserType) {
    WebDriver driver = null;

    switch (browserType) {
        case CHROME:
            driver = createChromeDriver();
            System.out.println("Chrome Browser initialized");
            break;

        case FIREFOX:
            driver = createFirefoxDriver();
            System.out.println("Firefox Browser initialized");
            break;

        case EDGE:
            driver = createEdgeDriver();
            System.out.println("Edge Browser initialized");
            break;

        case SAFARI:
            driver = createSafariDriver();
            System.out.println("Safari Browser initialized");
            break;

        default:
            throw new RuntimeException("Unsupported browser type: " + browserType);
    }

    driver.manage().window().maximize();
    return driver;
}
```

## ✅ Avantaje

✅ **Flexibility** - Ușor de schimbat browserul
✅ **Maintainability** - Configurări centralizate
✅ **Scalability** - Ușor de adăugat noi browsere
✅ **Thread Safety** - Funcționează cu execuție paralelă
✅ **Reusability** - Se poate folosi în orice proiect

## 🚀 Utilizare în CI/CD

```yaml
# Exemplu GitHub Actions
- name: Run tests on Chrome
  run: mvn test -Dbrowser=CHROME

- name: Run tests on Firefox
  run: mvn test -Dbrowser=FIREFOX

- name: Run tests on Edge
  run: mvn test -Dbrowser=EDGE
```

---

**BrowserFactory simplifiază gestionarea multor browsere!** 🎉

