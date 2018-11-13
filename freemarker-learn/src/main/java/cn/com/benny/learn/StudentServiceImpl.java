/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-9 下午8:49
 * LastModified: 18-11-9 下午8:48
 */

package cn.com.benny.learn;
import com.evada.inno.core.service.impl.BaseServiceImpl;
import com.evada.pm.process.manage.model.Student;
import com.evada.pm.process.manage.repository.StudentRepository;
import com.evada.pm.process.manage.service.IStudentService;
import com.evada.pm.process.manage.repository.mybatis.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.evada.pm.process.manage.dto.StudentDTO;
import org.apache.commons.beanutils.BeanUtils;
import com.evada.inno.core.enums.StatusEnum;

/**
* 描述：质量问题 服务实现层
* @author benny
* @date 2018/11/09
*/
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, String> implements IStudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO findDTOById(String id) throws Exception {
StudentDTO studentDTO = studentDAO.findDTOById(id);
        return studentDTO;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) throws Exception {
Student student = new Student();
        BeanUtils.copyProperties(student,studentDTO);
student.setStatus(StatusEnum.ENABLE.toString());
student = studentRepository.saveAndFlush(student);
        return this.findDTOById(student.getId());
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO)throws Exception {
Student student = new Student();
        BeanUtils.copyProperties(student,studentDTO);
student = studentRepository.saveAndFlush(student);
        return this.findDTOById(student.getId());
    }
