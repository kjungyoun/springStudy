package com.example.springStudy2;

import com.example.springStudy2.aop.TimeTraceAop;
import com.example.springStudy2.controller.Controller;
import com.example.springStudy2.repository.JdbcRepository;
import com.example.springStudy2.repository.JpaNewRepository;
import com.example.springStudy2.repository.MemoryRepository;
import com.example.springStudy2.repository.Repository;
import com.example.springStudy2.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //자동적으로 bean객체를 생성해서 스캔해준다.
public class SpringConfig {
    //    @Bean
//    public Controller controller(){
//        return new Controller(service());
//    }
    private EntityManager em;

    private DataSource dataSource;

    private  final Repository repository;

    @Autowired
    public SpringConfig(Repository repository){
        this.repository = repository;
    }

    @Bean // 새 Service로 bean객체를 만들고 리턴해라
    public Service service(){ //여기까지 하면 클래스 위에 애너테이션을 제거해도 됨
        return new Service(repository); //service는 repository를 인자로 받기 때문에 아래에서 repository를 만들어 Service의 인자값으로 넣는다. 여기까지 하면 @AutoWired를 제거해도 됨
    }


}
//여기서 bean 애너테이션으로 만든 클래스들은 클래스 위에 생성한 애너테이션을 삭제해줘야 한다.
//따라서 @Bean을 사용한 애들은 클래스 위에 애너테이션 선언과 @AutoWired를 제거해줘야 한다.