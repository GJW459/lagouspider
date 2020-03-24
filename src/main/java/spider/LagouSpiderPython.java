/**
 * FileName: LagouSpiderPython
 * Author:   郭经伟
 * Date:     2020/3/24 18:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LagouSpiderPython {

    public static void main(String[] args) {

        //设置初始环境
        SpaiderUtilsJAVA.initProperties();
        //获得webDriver
        WebDriver webDriver=new ChromeDriver();
        //跳转
        try {
            webDriver.get("https://www.lagou.com/zhaopin/Python/?labelWords=label");
            //设置筛选条件
            SpaiderUtilsPython.choseOptions(webDriver);
            //获取数据
            SpaiderUtilsPython.getHtmlData(webDriver);
            webDriver.quit();
            System.out.println("程序执行完毕");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
