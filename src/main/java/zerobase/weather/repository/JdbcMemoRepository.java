package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

//spring boot 객체와 db를 연결하는 작업

@Repository
public class JdbcMemoRepository {
    private final JdbcTemplate jdbcTemplate;
    //jdbcTemplate 객체 생성

    @Autowired
    public JdbcMemoRepository(DataSource dataSource) {
        //application.properties 에 입력한 정보들이 여기위의 datasource 라는 객체에 담기기됨
        jdbcTemplate = new JdbcTemplate(dataSource);
        //datasource 활용하여 JdbcTemplate 만들어서 10줄에 선언한 변수에 넣어준다.
    }

    public Memo save(Memo memo) {
        String sql = "insert into memo values(?,?)";
        jdbcTemplate.update(sql, memo.getId(), memo.getText());
        return memo;
    }

    public List<Memo> findAll() {
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());
    }

    public Optional<Memo> findById(int id) {
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
    }

    //RowMapper 를 활용하여 DB 에서 가져온 resultset 을 springboot 의 memo 클래스 형태로 반환해주는 함수
    private RowMapper<Memo> memoRowMapper() {

        return (rs, rowNum) -> new Memo(
                rs.getInt("id"),
                rs.getString("text")
        );
    }

}
