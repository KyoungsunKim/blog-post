# Install Tomcat Service on CentOS 7

CentOS 7 에서 Apache Tomcat을 Service로 등록하는 방법을 찾으면서 알게된 내용을 정리한 기록입니다.  

CentOS 7 부터 기본 ini 시스템으로 `systemd`가 채택되었습니다.  
PID 1로 실행되어 초기화 스크립트 관리자, 로그시스템 관리자, cgroup 관리 등 시스템 전반적인 부분에 관여하고 있는데, 이는 유닉스의 철학인 '한 가지만 잘하자'와 상반되기 때문에 많은 논란이 되고 있기도 합니다. `systemd`에 대한 자세한 내용은 공식 홈페이지를 참고하면 좋을 것 같습니다.

* [systemd 공식홈페이지](https://www.freedesktop.org/wiki/Software/systemd/)

톰캣과 자바를 설치하는 과정은 아래 블로그를 참고하여 진행하였습니다.  

* [How to Install Tomcat 8.5 on CentOS 7](https://linuxize.com/post/how-to-install-tomcat-8-5-on-centos-7/)

톰캣과 자바를 설치하는 방법은 CentOS 6 과 동일하지만 톰캣을 서비스로 등록하는 방법이 변경되었습니다. 위 블로그를 보면 `/etc/systemd/system` 디렉토리 밑에 `tomcat.service` 라는 **systemd unit file**을 생성하고 이를 시스템에 등록합니다.  
새로운 형태의 파일이라서 안에 기록되는 내용들이 어떤 내용들인지 찾아보았습니다. 아래 명령어를 이용하면 각 섹션에서 사용되는 옵션들에 대한 설명을 확인할 수 있습니다.

```bash
man systemd.unit
man systemd.service
```

unit file은 크게 **[Unit]**, **[Service]**, **[Install]** 3개의 영역으로 구분 됩니다. 공통 설정 항목은 **[Unit]**, **[Install]** 영역에 설정하고 서비스에 특화된 설정은 **[Service]** 영역에 설정합니다. Service 파일은 반드시 **[Service]** 영역을 포함해야 합니다.

```bash
# /etc/systemd/system/tomcat.service

[Unit]
Description=Tomcat 8.5 servlet container
After=network.target

[Service]
# 프로세스 시작 타입 설정으로 simple, forking, oneshot, dbus, notify, idle 중 하나로 설정
# default는 simple로 설정됨
# simple : the prompt does not return until you press Control-C or stop the service in some other way
# forking : the prompt returns but the service keeps running in the background
Type=forking

# Unix user 또는 group 설정, 만약 그룹 설정이 없으면 user의 기본 그룹이 설정됨
User=tomcat
Group=tomcat

# 환경변수
Environment="JAVA_HOME=/usr/lib/jvm/jre"
Environment="JAVA_OPTS=-Djava.security.egd=file:///dev/urandom"

Environment="CATALINA_BASE=/opt/tomcat/latest"
Environment="CATALINA_HOME=/opt/tomcat/latest"
Environment="CATALINA_PID=/opt/tomcat/latest/temp/tomcat.pid"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"

# 시작명렁어
# command는 반드시 절대 경로를 사용해야 한다.
ExecStart=/opt/tomcat/latest/bin/startup.sh
# ExecStart를 통해 실핼된 서비스를 종료할 때 사용하는 Stop Command
ExecStop=/opt/tomcat/latest/bin/shutdown.sh

[Install]
WantedBy=multi-user.target
```

## 참고자료

* [systemd 공식홈페이지](https://www.freedesktop.org/wiki/Software/systemd/)
* [superuser.com](https://superuser.com/questions/1274901/systemd-forking-vs-simple/1274913)
* [How to Install Tomcat 8.5 on CentOS 7](https://linuxize.com/post/how-to-install-tomcat-8-5-on-centos-7/)
