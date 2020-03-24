/**
 * FileName: SpaiderUtilsJAVA
 * Author:   郭经伟
 * Date:     2020/3/23 21:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spider;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.awt.OSInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

//工具类
public class SpaiderUtilsJAVA {

    //配置环境
    public static void initProperties() {

        //判断当前是什么操作系统
        switch (OSInfo.getOSType()) {
            //通过类加载器获取类路径下的chrome driver
            case WINDOWS:
                System.setProperty("webdriver.chrome.driver", SpaiderUtilsJAVA.class.getClassLoader().getResource("chromedriver_win32.exe").getPath());
                break;
            case LINUX:
                System.setProperty("webdriver.chrome.driver", SpaiderUtilsJAVA.class.getClassLoader().getResource("chromedriver_linux64").getPath());
                break;
            case MACOSX:
                System.setProperty("webdriver.chrome.driver", SpaiderUtilsJAVA.class.getClassLoader().getResource("chromedriver_mac64").getPath());
                break;
            default:
                throw new RuntimeException("不支持当前的操作系统类型");
        }
    }

    //设置筛选条件
    public static void choseOptions(WebDriver webDriver) {

        //选择城市
//        String cityName = "成都";
//        WebElement cityAuthorElement = webDriver.findElement(By.xpath("//div[@class='other-hot-city']//a[contains(text(),'" + cityName + "')]"));
//        cityAuthorElement.click();
        // 选择工作经验
        // 不限 应届毕业生 3年及以下 3-5年 5-10年 10年以上 不要求
        choseByTitle(webDriver, "5-10年", "工作经验");

        // 选择学历要求
        // 不限 大专 本科 硕士 博士 不要求
        choseByTitle(webDriver, "本科", "学历要求");

        // 选择公司规模
        List<String> companyMembers = new ArrayList<String>();
        companyMembers.add("50-150人");
        companyMembers.add("150-500人");
        for (String companyMember : companyMembers) {
            choseByTitle(webDriver, companyMember, "公司规模");
        }
        // 选择行业领域
        choseByTitle(webDriver, "移动互联网", "行业领域");

    }

    public static void choseByTitle(WebDriver driver, String companyMember, String companyMemberChosenTitle) {
        WebElement companyElement = driver.findElement(By.xpath("//li[@class='multi-chosen']/span[contains(text(),'" + companyMemberChosenTitle + "')]"));
        WebElement companyAuthorElement = companyElement.findElement(By.xpath("../a[contains(text(),'" + companyMember + "')]"));
        companyAuthorElement.click();
    }

    public static void printMoney(Map<String, Job> jobs) {
        ArrayList<Job> list = Lists.newArrayList(jobs.values());
        Collections.sort(list, new Comparator<Job>() {
            public int compare(Job o1, Job o2) {
                return o2.getCount() - o1.getCount();
            }
        });
        for (Job job : list) {
            System.out.println(job);
        }
    }

    public static void findMoneyByPagination(WebDriver driver, Map<String, Job> map) {
        List<WebElement> jobElements = driver.findElements(By.className("list_item_top"));
        for (WebElement jobElement : jobElements) {
            String money = jobElement.findElement(By.className("money")).getText();
            String companyName = jobElement.findElement(By.className("company_name")).getText();
            if (map.containsKey(money)) {
                Job job = map.get(money);
                job.getCompanies().add(companyName);
                job.setMoney(money);
                job.setCount(job.getCount() + 1);
                map.put(money, job);
            } else {
                Job job = new Job();
                job.getCompanies().add(companyName);
                job.setMoney(money);
                job.setCount(1);
                map.put(money, job);
            }
        }
        WebElement nextPageElement = driver.findElement(By.className("pager_next"));
        boolean canClickNextPageBtn = !nextPageElement.getAttribute("class").contains("pager_next_disabled");
        if (canClickNextPageBtn) {
            nextPageElement.click();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            findMoneyByPagination(driver, map);
        }
    }


}
