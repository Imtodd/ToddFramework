# ToddFramework
java web框架集合

1.本框架集合了spring4.3.9,springmvc4.3.9,hibernate5.2.10,mybatis3.4.4,quartz2.3.0,ehcache2.10.4,activemq5.15.0,shiro1.4.0等,经过修改可以改变成ssh或ssm框架使用。

注意：本框架在web.xml中直接指向了spring.xml作为spring的主配置文件。其余的相应功能通过<import>标签导入。如不需要可直接注释掉，启动前一定注意文件的所有配置。如扫描包名的配置。另外如果需要使用activemq。需要下载相应的activemq消息队列服务器并运行启动后启动项目。
