/**
 * FileName: LagouSpider
 * Author:   郭经伟
 * Date:     2020/3/23 21:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spider;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class LagouSpider {
    public static void main(String[] args) {
        //配置初始环境
        //设置webDriver路径
        SpaiderUtils.initProperties();
        //获取webDriver
        WebDriver webDriver=new ChromeDriver();
        //初始化一个Map 存放JOB信息
        Map<String,Job> jobMap=new HashMap();
        //跳转页面
       try {
           //进入拉勾页面
           webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");
           //设置筛选条件
           SpaiderUtils.choseOptions(webDriver);
           //开始解析页面，分页获取工资
           SpaiderUtils.findMoneyByPagination(webDriver, jobMap);

           //打印工资
           SpaiderUtils.printMoney(jobMap);
           webDriver.quit();
           System.out.println("程序执行完毕");

       }catch (Exception e){
           e.printStackTrace();
       }

    }
}
