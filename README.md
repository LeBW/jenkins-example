# Jenkins Example
本项目作为Jenkins Pipeline的示例项目。

项目所用工程：LeaveWord。一个简单的留言板项目。使用的开发框架为Spring Boot + JPA，数据库为MySQL，前端开发工具集为Bootstrap。

## 运行说明
### 本地运行
#### 创建数据库
首先，在本地安装MySQL数据库或者使用远程MySQL数据库，然后新建名为***leaveword***的数据库，字符编码设置为***utf-8***:

```shell
# 进入数据库
mysql -u root -p
# 创建名为leaveword的数据库并且设置字符编码为utf-8
create database leaveword default character set = 'utf8';
```
注意：数据库版本建议为5.6/5.7。


#### 代码修改和运行
接下来建议您使用Intellij IDEA或者类似工具打开和运行项目，当然，您也可以选择使用记事本等工具修改代码，然后按照下面说明在命令行编译运行项目。

##### 使用IDEA
修改项目LeaveWord/src/main/resources/application.yml文件：

```yml
server:
  port: 8081
  servlet:
    context-path: /LeaveWord
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    # 修改此处为你的mysql数据库
    url: jdbc:mysql://localhost:3306/leaveword?characterEncoding=utf8&useSSL=false
    username: root
    # 修改数据库链接密码
    password: 123456
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  thymeleaf:
    prefix: classpath:/static/
    suffix: .html
    cache: false
```

修改成功之后，运行LeaveWord/src/main/java/com/leaveword/DemoApplication.java即可启动项目。

在浏览器访问http://localhost:8081/LeaveWord，访问项目主页面。

##### 使用命令行
首先，你仍需要修改LeaveWord/src/main/resources/application.yml中的数据库链接地址和密码。  
如果没有安装Maven命令行工具，需要先安装Maven。
然后运行以下命令来运行项目：

```shell
mvn clean package -DskipTests
java -jar target/leaveword-0.0.1-SNAPSHOT.jar
```

### Docker容器运行

如果您准备将此项目放到容器中运行，那么需要先安装Docker和Docker-Compose。

同样，您需要先修改LeaveWord/src/main/resources/application.yml文件为以下内容：


```yaml
server:
  port: 8081
  servlet:
    context-path: /LeaveWord
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://leaveword-mysql:3306/leaveword?characterEncoding=utf8&useSSL=false
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  thymeleaf:
    prefix: classpath:/static/
    suffix: .html
    cache: false
```

修改成功之后，运行以下命令：

```shell
# 注意，每次修改代码后都要运行以下代码重新构建镜像，否则修改不会生效。
mvn clean package -DskipTests
docker-compose build
# 因为mysql第一次启动较慢，如果出现数据库链接失败情况属于正常现象，执行docker-compose down之后重新执行docker-compose up即可
docker-compose up
```

此时，使用docker images查看，可以看到名为demo/leaveword的镜像，请自行根据调研的方案选择镜像上传方案。
