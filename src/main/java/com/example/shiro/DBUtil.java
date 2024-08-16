package com.example.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据库查询
 * */

@Repository
public class DBUtil {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * getPasswordByName
     * 查询密码
     * */
    public String getPasswordByName(String name){
        String result = "";

        String sql ="select pswd from u_user where name =?";

        Map<String, Object> map = jdbcTemplate.queryForMap(sql, name);

        if (!map.isEmpty()) {
                result = map.get("pswd").toString();
        }

        return result;
    }

    /**
     * getRolesByName
     * 授权
     * */
    public List<String > getRolesByName(String name){

        String sql ="select c.name from u_user a inner join u_user_role b on a.id = b.uid\n" +
                                                "inner join u_role c on c.id = b.rid\t\n" +
                                                "where a.name = ?";
        List<String> list = jdbcTemplate.queryForList(sql, String.class, name);

        return  list;
    }

    /**
     * getPermsByName
     * 权限
     * */
    public List<String > getPermsByName (String name){
        String sql ="select d.url from u_user a inner join u_user_role b on a.id = b.uid\n" +
                        "inner join u_role_permission c on c.rid =  b.rid\n" +
                        "inner join u_permission d  on c.pid = d.id\n" +
                        "where a.name = ?";
        List<String > list = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        },name);

        return list;
    }
}
