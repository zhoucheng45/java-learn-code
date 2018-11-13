/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-9 下午8:49
 * LastModified: 18-11-9 下午8:48
 */

package cn.com.benny.learn;{package_name}.model;
import com.evada.inno.common.domain.BaseModel;
import com.evada.inno.common.listener.ICreateListenable;
import com.evada.inno.common.listener.IDeleteListenable;
import com.evada.inno.common.listener.IModifyListenable;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
* 描述：质量问题模型
* @author benny
* @date 2018/11/09
*/
@Entity
@Table(name="student")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Student extends BaseModel implements ICreateListenable,IModifyListenable,IDeleteListenable {

    /**
    *
    */
    /**
    *
    */
    /**
    *1男2女0未知
    */
    /**
    *
    */
    /**
    *
    */
    /**
    *
    */
    /**
    *
    */
    /**
    *
    */
    /**
    *1男2女0未知
    */
    /**
    *
    */
    /**
    *
    */
    /**
    *
    */


}
