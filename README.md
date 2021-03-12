zookeeper {자신의 카프카 스토리지 경로[windows 경로까지 접근 기준]}.\zookeeper-server-start.bat ....\config\zookeeper.properties

kafka {자신의 카프카 스토리지 경로[windows 경로까지 접근 기준]}.\kafka-server-start.bat ....\config\server.properties

-MSA 실행 {자신의 카프카 스토리지 경로[windows 경로까지 접근 기준]}.\kafka-console-consumer --bootstrap-server localhost:9092 --topic shop --from-beginning



![SpringMSA1](https://user-images.githubusercontent.com/44662161/110877808-5cd9cd00-831d-11eb-97c2-ce43f3e211a3.png)
