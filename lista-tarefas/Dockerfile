FROM openjdk

WORKDIR /lista-tarefas-app

COPY target/lista-tarefas-0.0.1-SNAPSHOT.jar /lista-tarefas-app/lista-tarefas.jar

ENTRYPOINT ["java","-jar", "lista-tarefas.jar"]