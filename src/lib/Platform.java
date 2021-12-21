package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform(){}

    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilites());
        }else if (this.isIOS()){
            return new IOSDriver(URL, this.getIOSDesiredCapabilites());
        }else{
            throw new Exception("Cannot detect type of the Driver. Platdorm value: "+ this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilites() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("AutomationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/skyeng/Desktop/JavaAppiumAutomation/Automation_mobile/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilites() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 11");
        capabilities.setCapability("platformVersion", "14.0");
        capabilities.setCapability("app", "/Users/skyeng/Desktop/JavaAppiumAutomation/Automation_mobile/apks/Wikipedia.app");
        return capabilities;
    }

    private boolean isPlatform(String my_plaform) {
        String platform = this.getPlatformVar();
        return my_plaform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
