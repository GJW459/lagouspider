/**
 * FileName: Job2
 * Author:   郭经伟
 * Date:     2020/3/24 18:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spider;

public class Job2 {

    private String money;
    private String company;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Job2{" +
                "money='" + money + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
