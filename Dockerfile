FROM java:8

COPY target/OrderData.jar /app/

WORKDIR /app/

RUN chmod 777 OrderData.jar

CMD java -jar OrderData.jar

EXPOSE 8995
