package com.todd.framework.quartz;
//package com.todd.formwork.quartz;
//
//import java.util.Date;
//import java.util.List;
//
//import org.slf4j.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.zp.zentao.core.ExecuteMethod;
//import com.zp.zentao.dao.impl.UsersDao;
//import com.zp.zentao.po.Users;
//
//@Transactional
//public class StartQuartz {
//	// 日志
//	public static Logger logger = LoggerFactory.getLogger(StartQuartz.class);
//
//	@Autowired
//	private UsersDao userdao;
//
//	public void startpatrol() {
//		System.out.println("开始检查任务");
//		List<Users> users = userdao.findByHQL("from Users where isUse=?", true);
//		ExecuteMethod em = new ExecuteMethod();
//		em.patrol(users);
//		System.out.println("检查完成。。。");
//	}
//
//	public void startDo() {
//		System.out.println("开始启动自动完成。。。");
//		List<Users> users = userdao.findByHQL("from Users where isUse=?", true);
//		ExecuteMethod em = new ExecuteMethod();
//		em.autoDoing(users);
//		System.out.println("检查完成。。。");
//	}
//}
