/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-9 下午8:49
 * LastModified: 18-11-9 下午8:48
 */

package cn.com.benny.learn;
import com.evada.inno.core.service.IBaseService;
import com.evada.pm.process.manage.model.Student;
import com.evada.pm.process.manage.dto.StudentDTO;
/**
* 描述：质量问题 服务实现层接口
* @author benny
* @date 2018/11/09
*/
public interface IStudentService extends IBaseService<Student,String> {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
StudentDTO findDTOById(String id)throws Exception;

StudentDTO createStudent(StudentDTO studentDTO) throws Exception;

    void deleteStudent(String id) throws Exception;

StudentDTO updateStudent(StudentDTO studentDTO) throws Exception;

}
