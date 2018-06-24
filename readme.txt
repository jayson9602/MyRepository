测试
v1.0
	1、将jsp页面拷贝到web项目中
	2、建立DbUtil
	3、将MySQL jdbc驱动拷贝到WEB-INF/lib下
	4、建立xml文件sys-config.xml
	5、将dom4j相关的jar拷贝到WEB-INF/lib下
		* dom4j-1.6.1.jar
		* jaxen-1.1-beta-6.jar
	6、采用dom4j完成sys-config.xml配置文件的读取
v1.1
	* 完成用户添加业务逻辑方法实现
v1.2
	* 采用javascript完成用户添加合法性检查
v1.3
	* 添加用户	
	* 设置请求字符集
v1.4
	* 作业：完成添加用户是否重复的判断
v1.5
	* 采用Ajax完成用户代码是否重复的判断		
v1.9
	* 完成分页业务逻辑部分实现
	* 采用泛型封装PageModel对象
	* 将每页的数据输出到jsp	
	* 完成整个分页查询
v2.0
	* 完成全部用户查询页面控制
	* 定义删除用户方法
	* 完成用户修改
	* 完成用户删除		
v2.3
	* 采用Filter设置字符集
v2.4
	* 完成用户登录	
v3.5
	* 建立物料实体模型
	* 建立持久层结构	
	* 建立ProductManager，将ProductDaoFactory配置到了XML文件中
	* 完成XML文件的读取	
		为什么要配置到xml中--避免修改代码，当更换数据库时只需修改xml配置文件	
v3.6
	* 完成物料添加
v3.8
	* 加入根据id查询物料
	* 分页查询	
	* Servlet传递初始化参数
	* <context-param>传递参数,设置page-size=5
	
v3.9
	* 完成物料修改
	* 完成物料删除
	* 完成物料类别增删改查
	* 完成供货厂商增删改查

v4.0
	* 上传物料图片	
	* 采用图片名称和物料代码名称一致
	* 自动创建上传目录和临时交换目录
v4.7
	* 将物料维护表示层部分修改为JSTL
	* 将JSTL相关jar包拷贝到WEB-INF/lib下
v4.8
	* 完成进货管理模块
	* 完成出货管理模块
	* 添加类别备注字段
	* 完成用户权限分配
v4.9
	* 将用户管理模块改成mvc架构
	
v5.8
	* 将公共的部分放到jsp中采用include进行包含
	* 使用${pageContext.request.contextPath}	
v5.9
	* 实现导出excel表格
v6.0
	* 采用Filter完成登录验证
	* 采用Filter完成缓存控制
v6.1
	* 部署到tomcat连接池上
v6.2
	* 设置0缓存
	* 配置缓存-WebCacheFIlter