<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.evada.pm.process.manage.repository.mybatis.StudentDAO">

    <resultMap id="StudentDTOResultMap" type="com.evada.pm.process.manage.dto.StudentDTO"></resultMap>

    <sql id="findDtoSql">
        select * from (
        select * from  student temp
        ) t
    </sql>

    <select id="findDTOById" parameterType="String" resultMap="StudentDTOResultMap">
        <include refid="findDtoSql"></include>
        <where>
            and t.id = #{id}
        </where>
    </select>

    <select id="findStudentPage" parameterType="com.evada.pm.process.manage.dto.StudentDTO" resultMap="StudentDTOResultMap">
        <include refid="findDtoSql" />
        <where>

        </where>
    </select>

</mapper>
