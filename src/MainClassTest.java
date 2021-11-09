import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class MainClassTest extends MainClass{

    @Test
    public void testGetLocalNumber()
    {
        Assert.assertTrue( "local number не равен 14",this.getLocalNumber() ==14);
    }

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue( "class_number должен быть больше 45",this.getClassNumber() > 45);

    }

    @Test
    public void testGetClassString()
    {
        Assert.assertTrue( "В строке нет ни Hello, ни hello",
                this.getClassString().contains("hello") || this.getClassString().contains("Hello"));
    }
}
