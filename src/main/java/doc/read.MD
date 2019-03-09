1.创建项目设置字符编码：File---Editor---File Encodings ---UTF-8 
2.创建spring boot 项目启动时，报错：No active profile set, falling back to default profiles: default
  解决方案： Edit Configurations ---》Configuration--->Environment--->Environment variables : spring.profiles.active=dev
  
3.启动项目时，报错：Started Java8Application in 0.947 seconds (JVM running for 1.924)
  解决方案：
    1.Edit Configurations ---》Configuration--->JRE ---》选择自己的jdk 版本 
    2.pom 文件引入：
        <dependency>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

4.项目引入lombok:
  <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.6</version>
      <scope>provided</scope>
  </dependency>
   
      