/**
 * FileName: Job
 * Author:   郭经伟
 * Date:     2020/3/23 21:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spider;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要爬的实体对象
 * 爬工作
 */
public class Job {

    private String money;
    private List<String> companies=new ArrayList<String>();
    private Integer count;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<String> getCompanies() {
        return companies;
    }

    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "工资待遇为" + money + "的公司有" + count + "家，他们是：" + companies;
    }
}
