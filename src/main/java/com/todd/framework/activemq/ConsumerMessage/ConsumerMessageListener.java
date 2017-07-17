package com.todd.framework.activemq.ConsumerMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class ConsumerMessageListener implements MessageListener {

	private Logger log = Logger.getLogger(ConsumerMessageListener.class);

	public void onMessage(Message message) {
		System.out.println("开始休眠");
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("休眠结束");
		// 监听发送到消息队列的文本消息，作强制转换。
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("接收到的消息内容是：" + textMessage.getText());
			// TODO: 你喜欢的任何事情...
		} catch (JMSException e) {
			log.error("", e);
		}

	}

}
