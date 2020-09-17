FROM openjdk:8
EXPOSE 8088
ADD target/tutors-finder.jar tutors-finder.jar
ENTRYPOINT ["java","-jar","/tutors-finder.jar"]