package cn.tf.bos.cache;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.utils.MD5Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CacheTest {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void test1(){
		
		Statistics  statistics=sessionFactory.getStatistics();
		
		
		
		Session session1=sessionFactory.openSession();
		User user1 = (User) session1.getNamedQuery("User.login").setParameter(0, "b").setParameter(1, MD5Utils.md5("a")).uniqueResult();
		System.out.println(user1.getRole().getFunctions().size());
		session1.close();
		System.out.println("命中次数：" + statistics.getSecondLevelCacheHitCount() + ", 丢失次数：" + statistics.getSecondLevelCacheMissCount());
		System.out.println("****************************************");
		
		Session session2=sessionFactory.openSession();
		User user2 = (User) session2.getNamedQuery("User.login").setParameter(0, "c").setParameter(1, MD5Utils.md5("a")).uniqueResult();
		System.out.println(user2.getRole().getFunctions().size());
		session2.close();
		
		System.out.println("命中次数：" + statistics.getSecondLevelCacheHitCount() + ", 丢失次数：" + statistics.getSecondLevelCacheMissCount());
		System.out.println("****************************************");
		
		Session session3=sessionFactory.openSession();
		User user3 = (User) session3.getNamedQuery("User.login").setParameter(0, "d").setParameter(1, MD5Utils.md5("a")).uniqueResult();
		System.out.println(user3.getRole().getFunctions().size());
		session3.close();
		System.out.println("命中次数：" + statistics.getSecondLevelCacheHitCount() + ", 丢失次数：" + statistics.getSecondLevelCacheMissCount());
		System.out.println("****************************************");
		
	
	}

}
