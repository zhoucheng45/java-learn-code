/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-9 下午8:49
 * LastModified: 18-11-9 下午8:48
 */

package cn.com.benny.learn;
import com.evada.inno.core.annotation.Rest;
import com.evada.pm.process.manage.service.IStudentService;
import com.evada.pm.process.manage.model.Student;
import com.evada.pm.process.manage.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import com.evada.inno.common.domain.ResultData;
import com.evada.inno.core.util.AssertUtils;

/**
* 描述：质量问题控制层
* @author benny
* @date 2018/11/09
*/
@Rest(Student.class)
public class StudentController {

    @Autowired
    private IStudentService studentService;

    /**
    * 描述：根据Id 查询
    * @param id  质量问题id
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@PathVariable("id") String id)throws Exception {
StudentDTO studentDTO = studentService.findDTOById(id);
        AssertUtils.checkResourceFound(studentDTO);
        return new ResultData(StudentDTO.class, studentDTO);
    }

    /**
    * 描述:创建质量问题
    * @param studentDTO  质量问题DTO
    */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody StudentDTO studentDTO) throws Exception {
        return new ResultData(Student.class,studentService.createStudent(studentDTO));
    }

    /**
    * 描述：删除质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/{id}/bulk", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") String id) throws Exception {
studentService.deleteById(id);
    }

    /**
    * 描述：更新质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateStudent(@PathVariable("id") String id,@RequestBody StudentDTO studentDTO) throws Exception {
studentDTO.setId(id);
        return new ResultData(Student.class,studentService.updateStudent(studentDTO));
    }

}
