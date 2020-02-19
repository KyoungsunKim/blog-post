# Executable JAR파일 안에 있는 logback.xml 파일 변경하기

Logging Framework로 **Logback**을 주로 사용합니다. Logback은 어플리케이션이 동작하고 있는 중간에 설정파일이 변경되는 것을 자동으로 감지하여 해당 내용을 반영하는 기능을 가지고 있습니다.

운영환경에서는 불필요한 log파일이 생성되는 것을 방지하기 위해 로그레벨을 나눠서 설정하고 필요에 따라서 로그레벨을 변경하면서 사용합니다. **Executable JAR** 형태로 배포되어 있는 환경에서 logback.xml 설정 파일을 변경하는 방법을 정리합니다.

[공식홈페이지](http://logback.qos.ch/manual/configuration.html)에 나와있는 sample logback.xml 파일입니다.

```xml
<configuration scan="true" scanPeriod="30 seconds">

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <!-- encoders are assigned the type
         ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```

 30초마다 설정파일이 변경되었는지 확인하여 변경사항을 적용하도록 설정되어 있습니다.

jar xvf - 압축해제
jar cvf - 파일압축
jar tvf - 내용확인
jar uvf - 내용변경

절대경로에 두고 해야함
com.itnomads A class의 경우 /com/itnomads/A.class 로 위치시켜야함
jar uvf ${jar파일} ${변경하려는 파일}

https://stackoverflow.com/questions/10730941/replace-a-xml-file-in-a-jar-file
https://sunijjang.tistory.com/51