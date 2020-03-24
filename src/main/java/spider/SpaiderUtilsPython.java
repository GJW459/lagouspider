/**
 * FileName: SpaiderUtilsPython
 * Author:   郭经伟
 * Date:     2020/3/24 18:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;

public class SpaiderUtilsPython {

    //设置筛选条件
    public static void choseOptions(WebDriver webDriver){

        //工作地点
        //选择城市
//        String city="成都";
//        WebElement cityElement = webDriver.findElement(By.xpath("//div[@class='other-hot-city']//a[contains(text(),'" + city + "')]"));
//        cityElement.click();
        //工作经验
        choseByTitle(webDriver,"应届毕业生","工作经验");
        //学历要求
        choseByTitle(webDriver,"本科","学历要求");
        //融资阶段
        choseByTitle(webDriver,"不限","融资阶段");
        //公司规模
        choseByTitle(webDriver,"不限","公司规模");
        //行业领域
        choseByTitle(webDriver,"不限","行业领域");

    }
    public static void choseByTitle(WebDriver webDriver,String content,String title){

        WebElement titleElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']/span[contains(text(),'" + title + "')]"));
        WebElement contentElement = titleElement.findElement(By.xpath("../a[contains(text(),'" + content + "')]"));
        contentElement.click();

    }
    //获取页面数据
    public static void getHtmlData(WebDriver webDriver){
        //获取当前页面的所有信息
        List<Job2> job2s=new ArrayList<Job2>();
        List<WebElement> jobMessages = webDriver.findElements(By.className("list_item_top"));
        for (WebElement jobMessage : jobMessages) {
            Job2 job2=new Job2();
            job2.setMoney(jobMessage.findElement(By.className("money")).getText());
            job2.setCompany(jobMessage.findElement(By.className("company_name")).getText());
            job2s.add(job2);
        }
        for (Job2 job2 : job2s) {
            System.out.println(job2.toString());
        }
        WebElement nextPageElement = webDriver.findElement(By.className("pager_next"));
        boolean canClickNextPageBtn = !nextPageElement.getAttribute("class").contains("pager_next_disabled");
        if (canClickNextPageBtn) {
            nextPageElement.click();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            getHtmlData(webDriver);
        }



    }
}
