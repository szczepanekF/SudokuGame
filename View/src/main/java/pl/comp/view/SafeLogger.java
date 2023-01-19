package pl.comp.view;


import java.io.FileInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SafeLogger {


    private final Logger log;


    public SafeLogger(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);

    }

    public void info(String var1, Integer var2, Integer var3,Integer var4) {
        try (FileInputStream fileInputStream = new FileInputStream("logs/testFile.log")) {
            log.info(var1,var2,var3,var4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void info(String var1) {
        try (FileInputStream fileInputStream = new FileInputStream("logs/testFile.log")) {
            log.info(var1);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void warn(String var1) {
        try (FileInputStream fileInputStream = new FileInputStream("logs/testFile.log")) {
            log.warn(var1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void error(String var1) {
        try (FileInputStream fileInputStream = new FileInputStream("logs/testFile.log")) {
            log.error(var1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
