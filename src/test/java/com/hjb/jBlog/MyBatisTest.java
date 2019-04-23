package com.hjb.jBlog;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hjb.jBlog.dao.impl.MemberDAOImpl;
import com.hjb.jBlog.dto.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
@WebAppConfiguration 
public class MyBatisTest 
{
    @Inject
    private SqlSessionFactory sqlFactory;
    
    @Autowired
	private MemberDAOImpl memberDAO;
    
    @Test
    public void testFactory(){
        System.out.println("\n >>>>>>>>>> sqlFactory 출력 : "+sqlFactory);
    }
    
    @Test
    public void testSession() throws Exception{
        
        try(SqlSession session = sqlFactory.openSession()){
            
            System.out.println(" >>>>>>>>>> session 출력 : "+session+"\n");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDao() throws Exception{
    	System.out.println(memberDAO.selectUserByUserid("sjung7674@naver.com").getUserid());
    	
    }
    
}
 


